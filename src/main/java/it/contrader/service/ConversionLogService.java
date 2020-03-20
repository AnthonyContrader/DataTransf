package it.contrader.service;

import java.util.List;

import it.contrader.dao.ConversionLogDao;
import it.contrader.model.Conversion;

public class ConversionLogService {

	private ConversionLogDao conversionLogDao;

	
public ConversionLogService() {
	
	 this.conversionLogDao = new ConversionLogDao();
}

public List<Conversion> getAll(){
	
	return this.conversionLogDao.getAll();
}

public List<Conversion> getAllLogUser(int idUser){
	
	return this.conversionLogDao.getAllLogUser(idUser);
}


}
