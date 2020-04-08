package it.contrader.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.contrader.dto.ChangesDTO;

@RestController
@RequestMapping("/changes")
@CrossOrigin(origins = "http://localhost:4200")
public class ChangesController extends AbstractController<ChangesDTO> {

}
