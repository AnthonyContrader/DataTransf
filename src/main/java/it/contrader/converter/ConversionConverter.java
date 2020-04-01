package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.contrader.dto.ConversionDTO;
import it.contrader.model.Conversion;


@Component
public class ConversionConverter implements Converter<Conversion, ConversionDTO>{

	@Override
	public Conversion toEntity(ConversionDTO dto) {
		return new Conversion(dto.getIdConversion(), dto.getIdUser(), dto.getSource(), dto.getSourceType(), dto.getOutputType(), dto.getChanges());
	}

	@Override
	public ConversionDTO toDTO(Conversion entity) {
		return new ConversionDTO(entity.getIdConversion(), entity.getIdUser(), entity.getSource(), entity.getSourceType(), entity.getOutputType(), entity.getChanges());
	}

	@Override
	public List<ConversionDTO> toDTOList(Iterable<Conversion> entityList) {
		List<ConversionDTO> ConversionDTOList = new ArrayList<ConversionDTO>();
		for (Conversion conversion : entityList) {
			ConversionDTOList.add(toDTO(conversion));
		}
		return ConversionDTOList;
		
	}

}
