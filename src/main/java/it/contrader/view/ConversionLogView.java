package it.contrader.view;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.UserDTO;

public class ConversionLogView extends AbstractView {

	@Override
	public void showResults(Request request) {
		
		if (request != null) {
			System.out.println("\n-------------------Conversion Log History----------------\n");
			System.out.println("IDUser\tIDConversion\tsourceTypeIn\toutputType");
			System.out.println("----------------------------------------------------\n");
			
			@SuppressWarnings("unchecked")
			//List<Conversion> users = (List<Conversion>) request.get("conversionLog");
			for (UserDTO u: users)
				System.out.println(u);
			System.out.println();
		}
		
	}

	@Override
	public void showOptions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void submit() {
		// TODO Auto-generated method stub
		
	}
	
	

}
