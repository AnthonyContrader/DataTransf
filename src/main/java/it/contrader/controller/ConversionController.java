package it.contrader.controller;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.contrader.dto.ChangesDTO;
import it.contrader.dto.ConversionDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Conversion.SourceType;
import it.contrader.service.ChangesService;
import it.contrader.service.ConvService;


@RestController
@RequestMapping("/conversion")
@CrossOrigin(origins = "http://localhost:4200")
public class ConversionController extends AbstractController<ConversionDTO> {
	
	@Autowired
	private ConvService service;
	
	
	@GetMapping(value = "/getAllByIdUser")
	public Iterable<ConversionDTO> getAllByIdUser(Long id){
		return service.findAllByIdUser(id);
	}
	
	@GetMapping(value = "/getLastIdByUser")
	public Long getLastIdByUser(Long id) {
		return service.getLastId(id);
	}

}
