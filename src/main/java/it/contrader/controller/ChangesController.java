package it.contrader.controller;

import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
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
	
			session.setAttribute("tagPosition", TagPosition(deque, source));
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
			  
			session.setAttribute("tagPosition", TagPosition(deque, xml));
			  
			session.setAttribute("changes", deque);
			break;
		}
		
		//eseguo il redirect alla pagina newchanges
		return "newchanges";
	}
	
	
	@PostMapping("/save")
	public String saveChanges(HttpServletRequest request, 
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
		
		
		for (Map.Entry<String, ArrayDeque<String>> entry : tagPosition) {
			if(request.getParameter("tag position " + entry.getKey()) != "") {
				
				
				String tmpTagPosition = request.getParameter("tag position " + entry.getKey());
				
				session.setAttribute("tag position " + entry.getKey(), tmpTagPosition);
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
		
		session.setAttribute("lastChangesId",service.getLastId(user.getId()));
		
		return "redirect:/conversion/newconversion";
	}
	
	public static ArrayDeque<String> mapXml(String soString, String tag) {
		ArrayDeque<String> array = new ArrayDeque<String>();
		Matcher m = Pattern.compile("\\<"+ tag + ">" +"(.*?)\\</" + tag + ">").matcher(soString);
		
		while(m.find()) {
			if(m.group(1).contains("</")) {
				Matcher nestedTag = Pattern.compile("\\<(.*?)\\>").matcher(m.group(1));
				while(nestedTag.find()) {
					if(!nestedTag.group(1).startsWith("/")) {
						if(!array.contains(nestedTag.group(1))) {
							array.addLast(nestedTag.group(1));
						}
					}
				}
			}
		}
		
		return array;
		
	}

	public  ArrayDeque<Map.Entry<String, ArrayDeque<String>>> clearMap(ArrayDeque<Map.Entry<String, 
			ArrayDeque<String>>> tagMapTmp){
		 
		ArrayDeque<Map.Entry<String, ArrayDeque<String>>> clearMap = new ArrayDeque<Map.Entry<String,ArrayDeque<String>>>();
		HashMap<String, ArrayDeque<String>> tmpList = new HashMap<String, ArrayDeque<String>>();
		 
		 for (Map.Entry<String, ArrayDeque<String>> entity : tagMapTmp) {
			if(!entity.getValue().isEmpty()) {
				tmpList.put(entity.getKey(), entity.getValue());
			}
		 }

		for(Map.Entry<String, ArrayDeque<String>> entry : tmpList.entrySet()) {
			 ArrayDeque<String> tmp = entry.getValue().clone();   
			 for(String s : entry.getValue()) {
			    	if(tmpList.containsKey(s)) {
			    		tmp.removeAll(tmpList.get(s));
			    	}
			    }
			 entry.setValue(tmp);
		}
		 
		 for (Map.Entry<String, ArrayDeque<String>> entity : tagMapTmp) {
				if(!entity.getValue().isEmpty()) {
					new AbstractMap.SimpleEntry<String, ArrayDeque<String>>(entity.getKey(), tmpList.get(entity.getKey()));
					clearMap.addLast(new AbstractMap.SimpleEntry<String, ArrayDeque<String>>(entity.getKey(), 
							tmpList.get(entity.getKey())));
				}
		}

		 return clearMap;
	}

	public ArrayDeque<Map.Entry<String, ArrayDeque<String>>> TagPosition(ArrayDeque<Map.Entry<String, String>> tagList, 
			String xml) {
		
		 ArrayDeque<Map.Entry<String, ArrayDeque<String>>> tagPosition = new ArrayDeque<Map.Entry<String, ArrayDeque<String>>>();
		
		 ArrayDeque<Map.Entry<String, ArrayDeque<String>>> tagMapTmp = new ArrayDeque<Map.Entry<String,ArrayDeque<String>>>();
		  
		 for (Map.Entry<String, String> string : tagList) {
			  tagMapTmp.addLast(new AbstractMap.SimpleEntry<String, ArrayDeque<String>>(string.getKey(), 
					  mapXml(xml, string.getKey())));
		 }
		
		tagPosition = clearMap(tagMapTmp);
		  
		return tagPosition;	
	}
	
}
