package it.contrader.service;

import java.util.List;
import org.springframework.stereotype.Service;

import it.contrader.dao.ConversionRepository;
import it.contrader.dto.ConversionDTO;
import it.contrader.model.Conversion;

@Service
public class ConvService extends AbstractService<Conversion, ConversionDTO> {
	
	public List<ConversionDTO> findAllByIdUser(Long idUser) {
		return converter.toDTOList(((ConversionRepository) repository).findAllByIdUser(idUser));
	}
	
}