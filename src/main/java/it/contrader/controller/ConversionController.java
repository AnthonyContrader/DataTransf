package it.contrader.controller;


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
			
			switch (sourceType.toLowerCase()) {
				case "xml":
					obj = XML.toJSONObject(source);
					request.put("output", obj.toString());
					break;

				case "json":
					obj = new JSONObject(source);
					request.put("output", obj.toString());
					break;
				
				default:
					request.put("output", "incorrect input type");
					break;
			}
			MainDispatcher.getInstance().callView("ConversionOutput", request);
			break;
		
		case "c": //xmltojson
			MainDispatcher.getInstance().callView("Conversion", request);
			break;
		
		default:
			MainDispatcher.getInstance().callView("Conversion", request);
			break;
			
		}	
		}
	
}
