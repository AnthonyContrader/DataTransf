package it.contrader.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data        // Crea Getter e Setter e l'override equal e toString
@Entity
public class Conversion {
	
	public enum SourceType{
		
		XML, JSON
	}

	public enum OutputType{
		
		XML, JSON, CSV
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idConversion;
	
	@Column(unique = false)
	private int idUser;
	private String source;
	private SourceType sourceType;
	private OutputType outputType;
	private int changes;
	
}
