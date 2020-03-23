package it.contrader.view;

import java.util.Map;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;

public class ChangesView extends AbstractView {

	private Map<String, String> map;
	private Request request = new Request();
	private String choice;
	private String changesName;
	
	@SuppressWarnings("unchecked")
	@Override
	public void showResults(Request request) {
		// TODO Auto-generated method stub
		this.map = (Map<String, String>) request.get("changes");
	}

	@Override
	public void showOptions() {
		// TODO Auto-generated method stub
		for(Map.Entry<String, String> tagName : map.entrySet()) {
			System.out.println("Vuoi cambiare il tag di questo attributo " + tagName.getKey() + "?");
			System.out.println("[S]si\t[N]no");
			String input = getInput();
			if (input.equalsIgnoreCase("S")) {
				System.out.println("Inserisci il tag che vuoi mettere al posto di " + tagName.getKey()+ ":");
				map.put(tagName.getKey(), getInput());
			}
		
		}
		System.out.println("Vuoi salvare tutti i cambiamenti effettuati?");
		System.out.println("[S]si\t[N]no");
		choice = getInput().toUpperCase();
		if(choice == "S"){
			System.out.println("Inserisci il nome che vuoi dare al salvataggio delle modifiche : ");
			changesName = getInput();	
		}
		
	}

	@Override
	public void submit() {
		// TODO Auto-generated method stub
		switch(choice) {
		
		case "S":
			request.put("changesName", changesName);
			request.put("choice", "insert");
			request.put("changes", map );
			MainDispatcher.getInstance().callAction("Changes", "doControl", request);
			break;
			
		default:
			request.put("changes", map);
			MainDispatcher.getInstance().callAction("Conversion", "doControl", request);
		
			
			
		}
		
	}

}
