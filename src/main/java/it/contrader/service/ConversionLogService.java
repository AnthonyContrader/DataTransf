package it.contrader.service;

import java.util.List;

import it.contrader.dto.ConversionDTO;
import it.contrader.dao.ConversionLogDAO;

public class ConversionLogService {

	private ConversionLogDAO ConversionLogDAO;

	
	public ConversionLogService() {
		 this.ConversionLogDAO = new ConversionLogDAO();
	}

	public List<ConversionDTO> getAll(){
		return this.ConversionLogDAO.getAll();
	}

	public List<ConversionDTO> getAllLogUser(int idUser){
		return this.ConversionLogDAO.getAllLogUser(idUser);
	}
	
}
