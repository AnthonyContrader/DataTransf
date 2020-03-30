package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.ChangesDTO;
import it.contrader.model.Changes;

public class ChangesConverter implements Converter<Changes, ChangesDTO> {

	@Override
	public ChangesDTO toDTO(Changes entity) {
		// TODO Auto-generated method stub
		return new ChangesDTO(entity.getId(), entity.getChangesName(), entity.getChanges(), entity.getIdUser(), entity.getRemovedTag());
	}

	@Override
	public Changes toEntity(ChangesDTO dto) {
		// TODO Auto-generated method stub
		return new Changes(dto.getId(), dto.getChangesName(),dto.getChanges(),dto.getIdUser(), dto.getRemovedTag());
	}

	@Override
	public List<ChangesDTO> toDTOList(List<Changes> entityList) {
		// TODO Auto-generated method stub
		List<ChangesDTO> dtoList = new ArrayList<ChangesDTO>();
		
		for(Changes changes : entityList) {
			dtoList.add(toDTO(changes));
		}
		
		return dtoList;
	}

}
