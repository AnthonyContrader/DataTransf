package it.contrader.service;

import java.util.List;

import it.contrader.converter.ChangesConverter;
import it.contrader.converter.Converter;
import it.contrader.dao.ChangesDAO;
import it.contrader.dto.ChangesDTO;
import it.contrader.model.Changes;

public class ChangesService implements Service<ChangesDTO>{
	
	protected ChangesDAO dao;
	protected Converter<Changes,ChangesDTO> converter;
	
	
	public ChangesService() {
		
		this.dao = new ChangesDAO();
		this.converter = new ChangesConverter();
		
	}


	public List<ChangesDTO> getAll() {
		// Ottiene una lista di entità e le restituisce convertendole in DTO
		return converter.toDTOList(dao.getAll());
	}

	
	public ChangesDTO read(int id) {
		// Ottiene un'entità e la restituisce convertendola in DTO
		return converter.toDTO(dao.read(id));
	}

	
	public boolean insert(ChangesDTO dto) {
		// Converte un DTO in entità e lo passa al DAO per l'inserimento
		return dao.insert(converter.toEntity(dto));
	}

	
	public boolean update(ChangesDTO dto) {
		// Converte un DTO in entità e lo passa al DAO per la modifica
		return dao.update(converter.toEntity(dto));
	}


	public boolean delete(int id) {
		// Questo metodo chiama direttamente il DAO
		return dao.delete(id);
	}

	public int lastId(int idUser) {
		// Questo metodo chiama direttamente il DAO per ottenere tutte le modifiche di quell'utente
		return dao.lastId(idUser);
	}

}
