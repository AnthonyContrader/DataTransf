package it.contrader.view;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;

public class ConversionView extends AbstractView {
	private String source;
	private String sourceType;
	private String outputType;
	private String username;
	
	private String choice;
	private Request request;
	
	@Override
	public void showResults(Request request) {
		//this.username = request.get("username").toString();
	}

	@Override
	public void showOptions() {
	System.out.println("inserire il messaggio da convertire");
		source = this.getInput();	
		
	System.out.println("inserisci il formato di input"); 
		sourceType = this.getInput();
		
	System.out.println("inserisci il formato di output");
		outputType = this.getInput();
		
	System.out.println("Continua [a] exit [e]");
		choice = this.getInput().toLowerCase();
		
	}

	@Override
	public void submit() {
		request = new Request();
		switch (choice) {
		
		case "a":
			request.put("choice", choice);
			request.put("source", source);
			request.put("sourceType", sourceType);
			request.put("outputType", outputType);
			MainDispatcher.getInstance().callAction("Conversion", "doControl", request);
		break;
		
		case "e":
			MainDispatcher.getInstance().callAction("HomeUser", "doControl", null);
			break;
		default:
			MainDispatcher.getInstance().callAction("HomeUser", "doControl", null);
		}	}

}
