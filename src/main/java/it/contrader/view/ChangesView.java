package it.contrader.view;

import java.util.Map;

import it.contrader.controller.Request;

public class ChangesView extends AbstractView {

	private Map<String, String> map;
	private Request request = new Request();
	
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
			System.out.println("How to change tag name");
			map.put(tagName.getKey(), getInput());
		}
	}

	@Override
	public void submit() {
		// TODO Auto-generated method stub
		request.put("choice", "insert");
	}

}
