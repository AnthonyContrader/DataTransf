package it.contrader.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangesDTO {

	private Long id;
	private String name;
	private String changes;
	private String removed;
	private int user;
	
}
