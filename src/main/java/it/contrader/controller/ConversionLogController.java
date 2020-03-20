package it.contrader.controller;

import it.contrader.main.MainDispatcher;
import it.contrader.service.ConversionLogService;

public class ConversionLogController implements Controller{
	
	private ConversionLogService conversionLogService = new ConversionLogService();
	
	

	@Override
	public void doControl(Request request) {
		
					
					String choice = request.get("choice").toString().toUpperCase();
		
					// Reindirizza alla giusta view in base alla choice
					switch(choice) {
					
					case "AA":
						request.put("Log", this.conversionLogService.getAll());
						MainDispatcher.getInstance().callView("ConversionLog", request);
						break;
						
					case "AU": 
						request.put("Log", this.conversionLogService.getAllLogUser(0));
						MainDispatcher.getInstance().callView("ConversionLog", request);
						break;
						
					case "US": 
						request.put("Log", this.conversionLogService.getAllLogUser(0));
						MainDispatcher.getInstance().callView("ConversionLog", request);
						break;
					
					default:
						 MainDispatcher.getInstance().callView("Login", null);
						 break;
					}
				
		
			}
			

}
