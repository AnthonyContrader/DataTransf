package it.contrader.service;

import java.util.List;

import it.contrader.converter.ChangesConverter;
import it.contrader.dao.ChangesDAO;
import it.contrader.dto.ChangesDTO;

public class ChangesService {

	private ChangesDAO changesDAO;
	private ChangesConverter changesConverter;
	
	public ChangesService() {
		// TODO Auto-generated constructor stub
		changesDAO = new ChangesDAO();
		changesConverter = new ChangesConverter();
	}
	
	public List<ChangesDTO> getAll(){
		return changesConverter.toDTOList(changesDAO.getAll());
	}
	
	public ChangesDTO read(int idUser) {
		return changesConverter.toDTO(changesDAO.read(idUser));
	}
	
	public boolean insert(ChangesDTO changes) {
		return changesDAO.insert(changesConverter.toEntity(changes));
	}
	
	public boolean delete(int changesId) {
		return changesDAO.delete(changesId);
	}
	
	public boolean update(ChangesDTO changesToUpdate) {
		return changesDAO.update(changesConverter.toEntity(changesToUpdate));
	}
	
}
