package it.contrader.controller;

import it.contrader.main.MainDispatcher;
import it.contrader.service.ConversionLogService;

public class ConversionLogController implements Controller{
	
	private ConversionLogService conversionLogService;
	

	@Override
	public void doControl(Request request) {
		
					
					String choice = request.get("choice").toString().toUpperCase();
		
					// Reindirizza alla giusta view in base alla choice
					switch(choice) {
					
					case "AA":
						MainDispatcher.getInstance().callView("ConversionLog", request);
						break;
						
					case "AU": 
						MainDispatcher.getInstance().callView("ConversionLog", request);
						break;
						
					case "US": 
						MainDispatcher.getInstance().callView("ConversionLog", request);
						break;
					
					default:
						 MainDispatcher.getInstance().callView("Login", null);
						 break;
					}
				
		
			}
			

}
