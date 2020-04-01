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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.ConversionDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Conversion.*;
import it.contrader.service.ConversionService;

@Controller
@RequestMapping("/conversion")
public class ConversionController {
	
	@Autowired
	private ConversionService service;
	
	


	@PostMapping("/newconversion")
	public String insert(HttpServletRequest request,@RequestParam(value = "mode", required = true) String choice) { 
		final HttpSession session = request.getSession();
		UserDTO user =(UserDTO)session.getAttribute("user");
		String source = session.getAttribute("source").toString();
		
		ConversionDTO dto = new ConversionDTO();
		dto.setIdUser(user.getId());
		dto.setSource((String) session.getAttribute("source"));
		dto.setSourceType((SourceType) session.getAttribute("sourceType"));
		dto.setOutputType((OutputType) session.getAttribute("outputType"));
		dto.setChanges((Long) session.getAttribute("idChanges"));
		service.insert(dto);

		switch(choice.toLowerCase()) {
	
	case "a":
		JSONObject obj;
		@SuppressWarnings("unchecked")	ArrayDeque<Map.Entry<String, String>> newdeque =
				(ArrayDeque<Map.Entry<String, String>>) session.getAttribute("changes");
		@SuppressWarnings("unused") ArrayList<String> removeElements = 
				(ArrayList<String>) session.getAttribute("removeElements");
		
		
		switch (dto.getSourceType()) {
		
			case XML:
				for(Map.Entry<String, String> tagName : newdeque) {
						if(removeElements!=null && !removeElements.isEmpty()) {
							for (String removeTag : removeElements) {
								Matcher tag = Pattern.compile("\\<" + removeTag + ">(.*?)\\" + "</" 
										+ removeTag + ">").matcher(source);
								while(tag.find()) {
									source = source.replace("<" + removeTag + ">" + tag.group(1) + "</" + removeTag + ">" , "");
								}
							}
						}
				if(newdeque!=null) {
					
						if(!tagName.getKey().equals(tagName.getValue())) {
							source = source.replaceAll("<" +  tagName.getKey() + ">",  "<" + tagName.getValue() + ">");
							source = source.replaceAll("</" +  tagName.getKey() + ">",  "</" + tagName.getValue() + ">");
						}
						
					}
				}
				obj = XML.toJSONObject(source);
				request.setAttribute("output", obj.toString());
				break;
			case JSON:
				obj = new JSONObject(source);
				String xml_output = XML.toString(obj);
				if(!removeElements.isEmpty()) {
					for (String removeTag : removeElements) {
						Matcher tag = Pattern.compile("\\<" + removeTag + ">(.*?)\\" + "</" 
								+ removeTag + ">").matcher(xml_output);
						while(tag.find()) {
							xml_output = xml_output.replace("<" + removeTag + ">" + tag.group(1) + "</" + removeTag + ">" , "");
						}
					}
				}
				if(newdeque!=null) {
					for(Map.Entry<String, String> tagName : newdeque) {
						if(!tagName.getKey().equals(tagName.getValue())) {
							xml_output = xml_output.replaceAll("<" +  tagName.getKey() + ">",  "<" + tagName.getValue() + ">");
							xml_output = xml_output.replaceAll("</" +  tagName.getKey() + ">",  "</" + tagName.getValue() + ">");
						}
					}
				}
			
				
				session.setAttribute("output", xml_output.replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
				break;
			
			default:
				session.setAttribute("output", "incorrect input type");
				break;
				}
		}
		return "conversionOutput";
		
	}
}

