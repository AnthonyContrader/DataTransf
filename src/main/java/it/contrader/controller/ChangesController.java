package it.contrader.controller;

import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.contrader.dto.ChangesDTO;
import it.contrader.dto.ConversionDTO;
import it.contrader.dto.UserDTO;
import it.contrader.service.ChangesService;

@RestController
@RequestMapping("/changes")
@CrossOrigin(origins = "http://localhost:4200")
public class ChangesController extends AbstractController<ChangesDTO> {
	
	@Autowired
	private ChangesService service;
	
	@PostMapping("/newchanges")
	public ArrayDeque<Map.Entry<String, String>> newChanges(@RequestBody ConversionDTO dto) {
		
	
		ArrayDeque<Map.Entry<String, String>> deque = new ArrayDeque<Map.Entry<String,String>>();
		Matcher m;
		
		switch (dto.getSourceType()) {
		case XML:
			
			//Creo la rejex per estrapolare i nomi dei tag e l'applico al source
			m = Pattern.compile("\\<(.*?)\\>").matcher(dto.getSource());
			
			//scorro il risultato della rejex in modo da salvare i key value originali del source all'interno del deque
			  while(m.find()) {	
                      Map.Entry<String,String> entry =
						    new AbstractMap.SimpleEntry<String, String>(m.group(1), m.group(1));
				    if(!deque.contains(entry) && !m.group(1).startsWith("/")) {
				    	deque.addLast(entry);
				    }
			  }
	        // Vedere come passare i valori di tagPosition
			//session.setAttribute("tagPosition", TagPosition(deque, source));
			//Salvo nella sessione l'array deque key value in modo da utiizzarlo liberamente durante le varie fasi di conversione
			//session.setAttribute("changes", deque);
			break;
		

			
		case JSON:
			String xml = XML.toString(new JSONObject(dto.getSource()));
			
			m = Pattern.compile("\\<(.*?)\\>").matcher(xml);
			  while(m.find()) {	
                      Map.Entry<String,String> entry =
						    new AbstractMap.SimpleEntry<String, String>(m.group(1), m.group(1));
				    if(!deque.contains(entry) && !m.group(1).startsWith("/")) {
				    	deque.addLast(entry);
				    }
			  }
			  
			//session.setAttribute("tagPosition", TagPosition(deque, xml));  
			//session.setAttribute("changes", deque);
			break;
		}
			return deque;
	}
	
	@PostMapping("/save")
	public String saveChanges( 
			@RequestParam(value = "changesName", required = false) String changesName) {
		
		//Recupero la sessione corrente
		final HttpSession session = request.getSession();
		
		//Recupero user dalla sessione in modo da poterlo utilizzare più avanti
		UserDTO user = (UserDTO) session.getAttribute("user");
		
		//Creo il changes DTO che mi servirà per poi salvare i dati sul database
		ChangesDTO dto = new ChangesDTO();
		
		//Inseristo l'id dell'utente corrente all'interno del DTO creato in precedenza
		dto.setUser(user.getId());
		
		/*Controllo che il nome delle changes sia presente e sia diverso da una stringa vuota, 
		 * se presente lo salvo all'interno del DTO
		 */
		if(changesName != null && !changesName.equals("")) {
			dto.setName(changesName);
		}
		
	     //Recupero dalla sessione l'array deque contenente tutti i nomi dei tag all'interno del source
		@SuppressWarnings("unchecked") ArrayDeque<Map.Entry<String, String>> newdeque = 
				(ArrayDeque<Map.Entry<String, String>>) session.getAttribute("changes");
		
		//Creo un array list che usero per inserire all'interno i tag che dovro rimuovere dalla conversione
		ArrayList<String> removeElements = new ArrayList<String>();
		
		/*
		 * Itero l'array deque contenente i tag in modo da controllare quali sono i tag da rimuovere,
		 * e nel frattempo, quando incontro tag che vanno rimossi li aggiungo all'array list dei removeElements
		 */
		for(Map.Entry<String, String> tagName : newdeque) {
			if(request.getParameter(tagName.getKey()) == null) {
				removeElements.add(tagName.getKey());
			}else {
				tagName.setValue(request.getParameter(tagName.getKey()));
			}
		}
		
		@SuppressWarnings("unchecked")
		ArrayDeque<Map.Entry<String, ArrayDeque<String>>> tagPosition = 
				(ArrayDeque<Map.Entry<String, ArrayDeque<String>>>) session.getAttribute("tagPosition");
		
		ArrayDeque<Map.Entry<String, ArrayDeque<String>>> newTagPosition = new ArrayDeque<Map.Entry<String,ArrayDeque<String>>>();
		
		
		for (Map.Entry<String, ArrayDeque<String>> entry : tagPosition) {
			if(request.getParameter("tag position " + entry.getKey()) != "") {
				
				
				String tmpTagPosition = request.getParameter("tag position " + entry.getKey());
				
				ArrayDeque<String> tmpArray = new ArrayDeque<String>();
				
				for(String tag : tmpTagPosition.replace("[", "").replace("]", "").split(",")) {
					tmpArray.addLast(tag);
				}
				
				newTagPosition.add(new AbstractMap.SimpleEntry<String, ArrayDeque<String>>(entry.getKey(), tmpArray));
				
				session.setAttribute("tag position " + entry.getKey(), tmpTagPosition);
			}
			
		}
		
		session.setAttribute("tagPosition", newTagPosition);
		
		//salvo nella sessione il nuovo array deque contenente i nuovi nomi dei tag 
		session.setAttribute("changes", newdeque);
		
		//Aggiungo nel changes DTO il nuovo array deque
		dto.setChanges(newdeque.toString());
		
		/*
		 * Controllo che l'array degli elementi da rimuovere sia popolato e non vuoto.
		 * Nel caso sia popolato lo aggiungo al changes DTO
		 */
		if(!removeElements.isEmpty()) {
			session.setAttribute("removeElements", removeElements);
			dto.setRemoved(removeElements.toString());
		}
		
		
		
		//Salvo il changes DTO all'interno del database mediante la chiamata insert del ChangesService
		service.insert(dto);
		
		session.setAttribute("lastChangesId",service.getLastId(user.getId()));
		
		return "redirect:/conversion/newconversion";
	}


	
	
	
	
}
