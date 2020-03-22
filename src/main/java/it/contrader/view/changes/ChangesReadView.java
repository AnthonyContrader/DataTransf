package it.contrader.view.changes;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.ChangesDTO;
import it.contrader.view.AbstractView;

public class ChangesReadView extends AbstractView {

	@Override
	public void showResults(Request request) {
		// TODO Auto-generated method stub
		if(request != null) {
			System.out.println("\n------------------- Gestione utenti ----------------\n");
			System.out.println("ID\tchanges name");
			System.out.println("----------------------------------------------------\n");
			
			@SuppressWarnings("unchecked")
			List<ChangesDTO> changesList = (List<ChangesDTO>) request.get("read");
			for(ChangesDTO changes : changesList) {
				System.out.println(changes.getId() + "\t" + changes.getChangesName());
			}
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
