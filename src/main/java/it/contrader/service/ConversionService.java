package it.contrader.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import it.contrader.converter.ConversionConverter;
import it.contrader.dao.ConversionRepository;
import it.contrader.dto.ConversionDTO;
import it.contrader.model.Conversion;

@Service
public class ConversionService extends AbstractService<Conversion, ConversionDTO> {
	
	@Autowired
	private ConversionService converter;
	@Autowired
	private ConversionRepository repository;
	
}
	
