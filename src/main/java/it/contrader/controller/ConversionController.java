package it.contrader.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.ConversionDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Conversion.SourceType;
import it.contrader.service.ConvService;

@Controller
@RequestMapping("/conversion")
public class ConversionController {
	
	@Autowired
	private ConvService service;
	
	@GetMapping("/newconversion")
	public String newconversion(HttpServletRequest request) {

		HttpSession session = request.getSession();

		UserDTO user = (UserDTO) request.getSession().getAttribute("user");
				
		SourceType sourceType = getSourceType(session.getAttribute("sourceType").toString());
		SourceType outputType = getSourceType(session.getAttribute("outputType").toString());
		
		String source = session.getAttribute("source").toString();
		
		ConversionDTO dto = new ConversionDTO();
		
		dto.setIdUser(user.getId());
		dto.setChanges(Long.parseLong(session.getAttribute("lastChangesId").toString()));
		dto.setSourceType(sourceType);
		dto.setOutputType(outputType);
		dto.setSource(source);
		
		@SuppressWarnings("unchecked")
		ArrayDeque<Map.Entry<String, String>> changes = (ArrayDeque<Map.Entry<String, String>>) session.getAttribute("changes"); 
		
		@SuppressWarnings("unchecked")
		ArrayList<String> removeElements = (ArrayList<String>) session.getAttribute("removeElements");
		
		@SuppressWarnings("unchecked")
		ArrayDeque<Map.Entry<String, ArrayDeque<String>>> tagPosition = 
				(ArrayDeque<Map.Entry<String, ArrayDeque<String>>>) session.getAttribute("tagPosition");
		
		switch (sourceType) {
		case XML:
			session.setAttribute("output", xml2Json(tagPositionChange(session, source, tagPosition), changes, removeElements));
			break;

		case JSON:
			session.setAttribute("output", json2xml(source, changes, removeElements, session, tagPosition));
			break;
		}
		
		service.insert(dto);
		
		return "conversionOutput";
		
	}
	
	@GetMapping("/findAll")
	public String findAll(HttpServletRequest request) {
		request.getSession().setAttribute("Log", service.getAll());
		request.getSession().setAttribute("active", "findAll");
		return "conversionlog";

}
	
	@GetMapping("/findAllByIdUser")
	public String findAllByIdUser(HttpServletRequest request, @RequestParam(value = "idUser", required = true) Long idUser){
		request.getSession().setAttribute("Log", service.findAllByIdUser(idUser));
		request.getSession().setAttribute("active", "myConversion");
		return "conversionlog";
		
	}
	
	public SourceType getSourceType(String type) {
		
		SourceType sourceType = null;
		
		switch (type.toUpperCase()) {
		case "XML":
			sourceType = SourceType.XML;
			break;
		case "JSON":
			sourceType = SourceType.JSON;
		}
		
		return sourceType;
		
	}
	
	
	public String xml2Json(String source, ArrayDeque<Map.Entry<String, String>> changes, ArrayList<String> removeElements) {

		JSONObject obj;

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
		obj = XML.toJSONObject(source);
		
		return obj.toString();
	
	}
	
	public String json2xml(String source, ArrayDeque<Map.Entry<String, String>> changes, ArrayList<String> removeElements, 
			HttpSession session, ArrayDeque<Map.Entry<String, ArrayDeque<String>>> tagPosition) {
		
		JSONObject obj = new JSONObject(source);
		String xml_output = XML.toString(obj);
		
		xml_output = tagPositionChange(session, xml_output, tagPosition);
		
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
	
	
	public String tagPositionChange(HttpSession session, String source, 
			ArrayDeque<Map.Entry<String, ArrayDeque<String>>> tagPosition) {
		
		for (Map.Entry<String, ArrayDeque<String>> entry : tagPosition) {
			if(session.getAttribute("tag position " + entry.getKey()) != null) {
				
				
				String tmpTagPosition = session.getAttribute("tag position " + entry.getKey()).toString();
				
				Matcher m = Pattern.compile("\\<"+ entry.getKey() + ">" +"(.*?)\\</" + entry.getKey() + ">")
						.matcher(source);
				
				while(m.find()) {
					String tmp = "";
					
					
					for(String tag : tmpTagPosition.replace("[", "").replace("]", "").split(",")) {
						
						Matcher tmpMatcher = Pattern.compile("\\<"+ tag + ">" +"(.*?)\\</" + tag + ">")
								.matcher(m.group(1));
						while(tmpMatcher.find()) {
							tmp = tmp.concat("<" + tag + ">" + tmpMatcher.group(1) + "</" + tag + ">");
						}
					}
					source = source.replace(m.group(1), tmp);
				}
			}
		}
		
		return source;
		
	}
	
}

