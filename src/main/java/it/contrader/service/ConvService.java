package it.contrader.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import it.contrader.converter.ConversionConverter;
import it.contrader.dao.ConversionRepository;
import it.contrader.dto.ConversionDTO;
import it.contrader.model.Conversion;

@Service
public class ConvService extends AbstractService<Conversion, ConversionDTO> {
	
	@Autowired   //serve per istanziare automaticamente la classe al posto di chiamare il costruttore.
	private ConversionConverter converter;
	@Autowired
	private ConversionRepository repository;
	
	public List<ConversionDTO> findAllByIdUser(Long idUser) {
		return converter.toDTOList(repository.findAllByIdUser(idUser));
}}
	
