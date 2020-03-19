package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.ConversionDTO;

import it.contrader.model.Conversion;




public class ConversionConverter {
	
	public ConversionDTO toDTO(Conversion conversion) {
		ConversionDTO conversionDTO = new ConversionDTO(conversion.getIdConversion(), conversion.getIdUser(), conversion.getSource(), conversion.getSourceType(), conversion.getOutputType(), conversion.isChanges());
        return conversionDTO;
}

    public Conversion toEntity(ConversionDTO conversionDTO) {
		Conversion conversion = new Conversion(conversionDTO.getIdConversion(),conversionDTO.getIdUser(), conversionDTO.getSource(), conversionDTO.getSourceType(), conversionDTO.getOutputType(), conversionDTO.isChanges());
		return conversion;
		
}
    public List<ConversionDTO> toDTOList(List<Conversion> conversionList) {
		
		List<ConversionDTO> ConversionDTOList = new ArrayList<ConversionDTO>();
		
		for(Conversion conversion : conversionList) {
			
			ConversionDTOList.add(toDTO(conversion));
		}
		return ConversionDTOList;
	}

}
