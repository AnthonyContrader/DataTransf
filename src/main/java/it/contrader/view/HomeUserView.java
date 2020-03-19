package it.contrader.view;


import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;

public class HomeUserView extends AbstractView{

	String choice;

	@Override
	public void showResults(Request request) {
		System.out.println("\n-----Purtroppo in questo sample l'utente non puà fare nulla, ci scusiamo per il disagio.-----");

	}
//modificare le opzioni dell'user
	@Override
	public void showOptions() {
		System.out.println("-------------MENU------------\n");
		System.out.println("OPZIONI DISPONIBILI");
		System.out.println("[c]conversione\t[e]uscita");
		choice = this.getInput().toLowerCase();

	}

	@Override
	public void submit() {

		switch (choice) {

		case "e":
			MainDispatcher.getInstance().callAction("Login", "doControl", null);
			break;
		
		case "c":
			MainDispatcher.getInstance().callAction("Conversion", "doControl", null);
			break;

		default:
			MainDispatcher.getInstance().callAction("Login", "doControl", null);
		}
	}

}
