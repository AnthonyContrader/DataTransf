package it.contrader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.contrader.dto.ChangesDTO;
import it.contrader.service.ChangesService;

@RestController
@RequestMapping("/changes")
@CrossOrigin(origins = "http://localhost:4200")
public class ChangesController extends AbstractController<ChangesDTO> {

	@Autowired ChangesService service;
	
	@GetMapping("/getLastId")
	public Long getLastId(Long id) {
		return service.getLastId(id);
	}
	
	@GetMapping("/getAllByUser")
	public Iterable<ChangesDTO> getAllByUser(Long user) {
		return service.getAllByUser(user);
	}
	
}
