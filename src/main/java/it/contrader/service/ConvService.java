package it.contrader.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.contrader.dao.ConversionRepository;
import it.contrader.dto.ConversionDTO;
import it.contrader.model.Conversion;

@Service
public class ConvService extends AbstractService<Conversion, ConversionDTO> {
	
	@Autowired
	private ConversionRepository repository;
	
	public List<ConversionDTO> findAllByIdUser(Long idUser) {
		return converter.toDTOList(((ConversionRepository) repository).findAllByIdUser(idUser));
	}

	public Long getLastId(Long userId) {
		return repository.getLastId(userId);
	}
	
}