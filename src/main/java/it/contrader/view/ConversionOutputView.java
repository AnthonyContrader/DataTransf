package it.contrader.view;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;

public class ConversionOutputView extends AbstractView {

	private String choice;
	
	private Request request;
	
	public ConversionOutputView() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void showResults(Request request) {
		// TODO Auto-generated method stub
		if(request.get("outputType")!=null) {
			System.out.println("your " + request.get("outputType").toString() + " is:");
		}
		System.out.println("---------------------------------------------------------");
		System.out.println("\n"+ request.get("output"));
		System.out.println("---------------------------------------------------------");

		
	}

	@Override
	public void showOptions() {
		// TODO Auto-generated method stub
		System.out.println("[a]Continue\t[b] Go Back");
		this.choice = getInput().toLowerCase();
		
	}

	@Override
	public void submit() {
		// TODO Auto-generated method stub
		
		switch(choice) {
		
		case "a":
			
			break;
		
		case "b":
			MainDispatcher.getInstance().callAction("Login", "doControl", null);
			break;
		
		}
		
	}

}
