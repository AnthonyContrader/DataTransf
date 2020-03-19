package it.contrader.view;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;

public class ConversionView extends AbstractView {
	String source;
	String sourceType;
	String outputType;
	
	String choice;
	private Request request;
	
	@Override
	public void showResults(Request request) {
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
		MainDispatcher.getInstance().callAction("Conversion", "doControl", request);
		break;
		
		case "e":
			MainDispatcher.getInstance().callAction("HomeUser", "doControl", null);
			break;
		default:
			MainDispatcher.getInstance().callAction("HomeUser", "doControl", null);
		}	}

}
