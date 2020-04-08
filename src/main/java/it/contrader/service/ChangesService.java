package it.contrader.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.contrader.dao.ChangesRepository;
import it.contrader.dto.ChangesDTO;
import it.contrader.model.Changes;

@Service
public class ChangesService extends AbstractService<Changes, ChangesDTO> {
	
	@Autowired
	private ChangesRepository repository;
	
	public Long getLastId(Long userId) {
		return repository.getLastId(userId);
	}
}
