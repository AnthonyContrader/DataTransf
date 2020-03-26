package it.contrader.service;

import it.contrader.converter.ChangesConverter;
import it.contrader.dao.ChangesDAO;
import it.contrader.dto.ChangesDTO;
import it.contrader.model.Changes;

public class ChangesService extends AbstractService<Changes, ChangesDTO>{

	public ChangesService(){
		this.dao = new ChangesDAO();
		this.converter = new ChangesConverter();
	}
	
}
