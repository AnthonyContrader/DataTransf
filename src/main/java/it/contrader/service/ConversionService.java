package it.contrader.service;

import it.contrader.converter.ConversionConverter;
import it.contrader.dao.ConversionDAO;
import it.contrader.dto.ConversionDTO;



public class ConversionService {
		
		private ConversionDAO conversionDAO;
		private ConversionConverter conversionConverter;
		
	
		public ConversionService(){
			this.conversionDAO = new ConversionDAO();
			this.conversionConverter = new ConversionConverter();
		}
		public boolean insert(ConversionDTO dto) {
			
			return conversionDAO.insert(conversionConverter.toEntity(dto));
		}

}
