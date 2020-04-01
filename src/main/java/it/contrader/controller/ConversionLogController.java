package it.contrader.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import it.contrader.dto.ConversionDTO;
import it.contrader.service.ConversionLogService;

@Controller
@RequestMapping("/ConversionLog")
public class ConversionLogController {
	
	@Autowired
	private ConversionLogService service;
	
	@GetMapping("/findAll")
	public String findAll(HttpServletRequest request) {
		request.getSession().setAttribute("Log", service.getAll());
		return "conversionlog";

}
	@GetMapping("/findAllByIdUser")
	public String findAllByIdUser(HttpServletRequest request, @RequestParam(value = "idUser", required = true) Long idUser){
		request.getSession().setAttribute("Log", service.findAllByIdUser(idUser));
		return "conversionlog";
		
	}
	
	
}