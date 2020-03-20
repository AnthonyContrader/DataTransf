package it.contrader.view;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;

public class ConversionLogAdminView extends AbstractView {
	private String idUser;
	Request request;
	
	@Override
	public void showResults(Request request) {
		// TODO Auto-generated method stub
	}

	@Override
	public void showOptions() {
		// TODO Auto-generated method stub
		System.out.println("Insert userId's Logs or insert [e] to exit:");
		this.idUser = getInput();
	}

	@Override
	public void submit() {
		// TODO Auto-generated method stub
		
		switch(idUser) {
		case "e":
			MainDispatcher.getInstance().callAction("Login", "doControl", null);
			break;
		default:
			request = new Request();
			request.put("choice", "US");
			request.put("idUser", idUser);
			MainDispatcher.getInstance().callAction("ConversionLog", "doControl", request);
			break;
		}
		
		
		
		
	}

}
