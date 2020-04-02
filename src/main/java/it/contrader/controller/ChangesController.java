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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.ChangesDTO;
import it.contrader.dto.UserDTO;
import it.contrader.service.ChangesService;

@Controller
@RequestMapping("/changes")
public class ChangesController {

	@Autowired
	private ChangesService service;
	
	@PostMapping("/newchanges")
	public String newChanges(HttpServletRequest request, @RequestParam(value = "sourceType", required = true) String typeIn, 
			@RequestParam(value = "outputType", required = true) String outputType, 
			@RequestParam(value = "source", required = true) String source) {
		
		System.out.println("test");
		
		//Recupero la sessione corrente
		final HttpSession session = request.getSession();
		
		/*
		 * Riposto all'interno della sessione tutto quello che mi sara utile per la conversione anche in un secondo momento 
		 * in modo da poterli usare liberamente nei vari passaggi senza preoccuparsi di perderli tra le varie request
		 */
		session.setAttribute("source", source);
		session.setAttribute("outputType", outputType);
		session.setAttribute("sourceType", typeIn);
		
		/*
		 * Instanzio un array deque contenente elementi di tipo key value, mi servira per salvar i tag del sorgente 
		 * e ad attribuire il nuovo valore per ognuno di essi in futuro
		 */
		ArrayDeque<Map.Entry<String, String>> deque = new ArrayDeque<Map.Entry<String,String>>();
		Matcher m;
		
		switch (typeIn.toUpperCase()) {
		case "XML":
			
			//Creo la rejex per estrapolare i nomi dei tag e l'applico al source
			m = Pattern.compile("\\<(.*?)\\>").matcher(source);
			
			//scorro il risultato della rejex in modo da salvare i key value originali del source all'interno del deque
			  while(m.find()) {	
                      Map.Entry<String,String> entry =
						    new AbstractMap.SimpleEntry<String, String>(m.group(1), m.group(1));
				    if(!deque.contains(entry) && !m.group(1).startsWith("/")) {
				    	deque.addLast(entry);
				    }
			  }
	
			//Salvo nella sessione l'array deque key value in modo da utiizzarlo liberamente durante le varie fasi di conversione
			session.setAttribute("changes", deque);
			break;
			
		case "JSON":
			String xml = XML.toString(new JSONObject(source));
			
			m = Pattern.compile("\\<(.*?)\\>").matcher(xml);
			  while(m.find()) {	
                      Map.Entry<String,String> entry =
						    new AbstractMap.SimpleEntry<String, String>(m.group(1), m.group(1));
				    if(!deque.contains(entry) && !m.group(1).startsWith("/")) {
				    	deque.addLast(entry);
				    	}
			  }
			session.setAttribute("changes", deque);
			break;
		}
		
		//eseguo il redirect alla pagina newchanges
		return "newchanges";
	}
	
	@PostMapping("/save")
	public String saveChanges(HttpServletRequest request, 
			@RequestParam(value = "changesName", required = false) String changesName) {
		
		System.out.println("save");
		
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
		@SuppressWarnings("unchecked") ArrayDeque<Map.Entry<String, String>> newdeque = (ArrayDeque<Map.Entry<String, String>>) session.getAttribute("changes");
		
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
		
		return "redirect:/conversion/newconversion";
	}
	
}
