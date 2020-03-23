package it.contrader.controller;


import java.util.Map;

import org.json.JSONObject;
import org.json.XML;

import it.contrader.dto.ConversionDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.ConversionService;

//da modificare
public class ConversionController implements Controller {
	
	private ConversionService conversionService;
	
	public ConversionController() {
		this.conversionService = new ConversionService();
	}
	@Override
	public void doControl(Request request) {

		
		
		String choice = request.get("choice").toString();
		
		//Definisce i campi della classe (serviranno sempre, tanto vale definirli una sola volta)
		String source;
		String sourceType;
		String outputType;
		boolean changes;
		
		switch(choice) {
		
		case "a":
			source = request.get("source").toString();
			sourceType = request.get("sourceType").toString();
			outputType = request.get("outputType").toString();
			JSONObject obj;
			@SuppressWarnings("unchecked") Map<String,String> map = (Map<String, String>) request.get("changes");
			request.put("source", source);
			request.put("sourceType", sourceType);
			request.put("outputType", outputType);
			ConversionDTO conversiontoinsert = new ConversionDTO( source, sourceType, outputType);
			conversionService.insert(conversiontoinsert);
			
			switch (sourceType.toLowerCase()) {
				case "xml":
					if(map!=null) {
						for(Map.Entry<String, String> tagName : map.entrySet()) {
							source = source.replaceAll("<" +  tagName.getKey() + ">",  "<" + tagName.getValue() + ">");
							source = source.replaceAll("</" +  tagName.getKey() + ">",  "</" + tagName.getValue() + ">");	
						}
					}
					obj = XML.toJSONObject(source);
					request.put("output", obj.toString());
					
					
					break;

				case "json":
					if(map!=null) {
						for(Map.Entry<String, String> tagName : map.entrySet()) {
							source = source.replaceAll("\"" +  tagName.getKey() + "\":",  "\"" + tagName.getValue() + "\":");						}
					}
					obj = new JSONObject(source);
					request.put("output", obj.toString());
					break;
				
				default:
					request.put("output", "incorrect input type");
					break;
			}
			MainDispatcher.getInstance().callView("ConversionOutput", request);
			break;
		
		case "c": 
			MainDispatcher.getInstance().callView("Conversion", request);
			break;
			
		case "d": 
			MainDispatcher.getInstance().callView("Changes", request);
			break;
		
		default:
			MainDispatcher.getInstance().callView("Conversion", request);
			break;
			
		}	
		}
	
}
