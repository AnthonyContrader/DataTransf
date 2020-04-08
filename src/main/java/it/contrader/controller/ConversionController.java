package it.contrader.controller;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.contrader.dto.ChangesDTO;
import it.contrader.dto.ConversionDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Conversion.SourceType;
import it.contrader.service.ChangesService;
import it.contrader.service.ConvService;


@RestController
@RequestMapping("/conversion")
@CrossOrigin(origins = "http://localhost:4200")
public class ConversionController extends AbstractController<ConversionDTO> {
	
	@Autowired
	private ConvService service;
	@Autowired
	private ChangesService changesService;
	
	
	@GetMapping("/newconversion")
	public ConversionDTO newconversion (@RequestBody UserDTO u, ConversionDTO dto, ChangesDTO changes) {
		
		
		dto.setIdUser(u.getId());
		dto.setChanges(changesService.getLastId(u.getId()));
		@SuppressWarnings("unchecked")
		// fare il casting tra la stringa che prendiamo da changes ad arraylist?
		ArrayList<String> removeElements = (ArrayList<String>) session.getAttribute("changes");
		
		@SuppressWarnings("unchecked")
		// passare l'array deque da controller changes (forse angular) al controller conversion
		ArrayDeque<Map.Entry<String, ArrayDeque<String>>> tagPosition = 
				(ArrayDeque<Map.Entry<String, ArrayDeque<String>>>) session.getAttribute("tagPosition");
		service.insert(dto);
		switch (dto.getSourceType()) {
		case XML:
			return xml2Json(tagPositionChange(dto.getSource(), tagPosition), changes, removeElements);
			break;
		case JSON:
			return json2xml(dto.getSource(), changes, removeElements, tagPosition);
			break;
		}
	}
	
	@GetMapping("/findAllByIdUser")
	public List<ConversionDTO> findAllByIdUser(Long idUser){
	 return (List<ConversionDTO>) service.findAllByIdUser(idUser);	
	}
	
public String xml2Json(String source, ArrayDeque<Map.Entry<String, String>> changes, ArrayList<String> removeElements) {
		
		for(Map.Entry<String, String> tagName : changes) {
			if(removeElements!=null && !removeElements.isEmpty()) {
				for (String removeTag : removeElements) {
					Matcher tag = Pattern.compile("\\<" + removeTag + ">(.*?)\\" + "</" 
							+ removeTag + ">").matcher(source);
					while(tag.find()) {
						source = source.replace("<" + removeTag + ">" + tag.group(1) + "</" + removeTag + ">" , "");
					}
				}
			}
			if(changes!=null) {
		
				if(!tagName.getKey().equals(tagName.getValue())) {
					source = source.replaceAll("<" +  tagName.getKey() + ">",  "<" + tagName.getValue() + ">");
					source = source.replaceAll("</" +  tagName.getKey() + ">",  "</" + tagName.getValue() + ">");
				}
			
			}
		}
		
		return XML.toJSONObject(source).toString();
	
	}	

public String json2xml(String source, ArrayDeque<Map.Entry<String, String>> changes, ArrayList<String> removeElements, 
		ArrayDeque<Map.Entry<String, ArrayDeque<String>>> tagPosition) {
	
	JSONObject obj = new JSONObject(source);
	String xml_output = XML.toString(obj);
	
	xml_output = tagPositionChange( xml_output, tagPosition);
	
	if(removeElements != null && !removeElements.isEmpty()) {
		for (String removeTag : removeElements) {
			Matcher tag = Pattern.compile("\\<" + removeTag + ">(.*?)\\" + "</" 
					+ removeTag + ">").matcher(xml_output);
			while(tag.find()) {
				xml_output = xml_output.replace("<" + removeTag + ">" + tag.group(1) + "</" + removeTag + ">" , "");
			}
		}
	}
	if(changes!=null) {
		for(Map.Entry<String, String> tagName : changes) {
			if(!tagName.getKey().equals(tagName.getValue())) {
				xml_output = xml_output.replaceAll("<" +  tagName.getKey() + ">",  "<" + tagName.getValue() + ">");
				xml_output = xml_output.replaceAll("</" +  tagName.getKey() + ">",  "</" + tagName.getValue() + ">");
			}
		}
	}
	
	return xml_output.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
}


public String tagPositionChange(String source, ArrayDeque<Map.Entry<String, ArrayDeque<String>>> tagPosition) {
	
	for (Map.Entry<String, ArrayDeque<String>> entry : tagPosition) {
		
		Matcher m = Pattern.compile("\\<"+ entry.getKey() + ">" +"(.*?)\\</" + entry.getKey() + ">")
				.matcher(source);
		
		while(m.find()) {
			String tmp = "";
			
			
			for(String tag : entry.getValue()) {
				
				Matcher tmpMatcher = Pattern.compile("\\<"+ tag + ">" +"(.*?)\\</" + tag + ">")
						.matcher(m.group(1));
				while(tmpMatcher.find()) {
					tmp = tmp.concat("<" + tag + ">" + tmpMatcher.group(1) + "</" + tag + ">");
				}
			}
			source = source.replace(m.group(1), tmp);
		}
		
	}
	return source;
	
}


}
