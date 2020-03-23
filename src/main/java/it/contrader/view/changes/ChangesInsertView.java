package it.contrader.view.changes;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class ChangesInsertView extends AbstractView {
	
	private Request request = new Request();

	@Override
	public void showResults(Request request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	@Override
	public void showOptions() {
		System.out.println("Inserisci un nome per il preset:");
		request.put("changesName", getInput());
	}

	@Override
	public void submit() {
		MainDispatcher.getInstance().callAction("Changes", "doControl", request);

	}

}
