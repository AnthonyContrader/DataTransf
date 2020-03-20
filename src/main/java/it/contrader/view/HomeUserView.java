package it.contrader.view;


import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;

public class HomeUserView extends AbstractView{

	String choice;
	private String username;
	private String password;
	private Request request;
	
	@Override
	public void showResults(Request request) {
		username = request.get("username").toString();
		password = request.get("password").toString();
	}
//modificare le opzioni dell'user
	@Override
	public void showOptions() {
		System.out.println("-------------MENU------------\n");
		System.out.println("OPZIONI DISPONIBILI");
		System.out.println("[c]conversione\t[e]esci");
		choice = this.getInput().toLowerCase();

	}

	@Override
	public void submit() {
		request = new Request();
		
		request.put("choice", choice);
		
		switch (choice) {

		case "e":
			MainDispatcher.getInstance().callAction("Login", "doControl", null);
			break;
		
		case "c":
			request.put("username", username);
			request.put("password", password);
			MainDispatcher.getInstance().callAction("Conversion", "doControl", request);
			break;

		default:
			MainDispatcher.getInstance().callAction("Login", "doControl", null);
		}
	}

}
