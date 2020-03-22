package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.ChangesDTO;
import it.contrader.model.Changes;

public class ChangesConverter {

	public ChangesDTO toDTO(Changes changes) {
		return new ChangesDTO(changes.getId(), changes.getChangesName(), 
				changes.getChanges(), changes.getIdUser());
	}
	
	public Changes toEntity(ChangesDTO changesDTO) {
		return new Changes(changesDTO.getId(), changesDTO.getChangesName(), 
				changesDTO.getChanges(), changesDTO.getIdUser());
	}
	
	public List<ChangesDTO> toDTOList(List<Changes> changesList){
		
		List<ChangesDTO> changesDTOList = new ArrayList<ChangesDTO>();
		
		for(Changes changes : changesList) {
			changesDTOList.add(new ChangesDTO(changes.getId(), changes.getChangesName(), 
					changes.getChanges(), changes.getIdUser()));
		}
		
		return changesDTOList;
		
	}
	
}
