package it.contrader.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.contrader.converter.ConversionConverter;
import it.contrader.dao.ConversionLogRepository;
import it.contrader.dto.ConversionDTO;
import it.contrader.model.Conversion;

@Service
public class ConversionLogService extends AbstractService<Conversion,ConversionDTO> {
	
	@Autowired
	private ConversionConverter converter;

	@Autowired
	private ConversionLogRepository repository;
	
	public List<ConversionDTO> findAllByIdUser(Long idUser) {
	return converter.toDTOList(repository.findAllByIdUser(idUser));
	}
}
