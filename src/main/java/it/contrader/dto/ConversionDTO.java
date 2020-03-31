package it.contrader.dto;

import it.contrader.model.Conversion.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ConversionDTO {
	
	private Long idConversion;
	private int idUser;
	private String source;
	private SourceType sourceType;
	private OutputType outputType;
	private int changes;

}
