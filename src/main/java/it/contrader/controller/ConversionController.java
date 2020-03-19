package it.contrader.controller;


import it.contrader.dto.ConversionDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.ConversionService;

//da modificare
public class ConversionController implements Controller {



	
	private ConversionService conversionService;
	
	public ConversionController() {
		this.conversionService = new ConversionService();
	}
	@Override
	public void doControl(Request request) {

		
		String choice = (String) request.get("choice");

		//Definisce i campi della classe (serviranno sempre, tanto vale definirli una sola volta)
		int idUser;
		String source;
		String sourceType;
		String outputType;
		boolean changes;

		switch(choice) {
		
		case "a": //xmltojson
			
			
		}
	
		
		idUser = Integer.parseInt(request.get("idUser").toString());
		source = request.get("source").toString();
		sourceType = request.get("sourceType").toString();
		outputType = request.get("outputType").toString();
		changes =Boolean.parseBoolean(request.get("changes").toString());
		
		//costruisce l'oggetto user da inserire
		ConversionDTO conversiontoinsert = new ConversionDTO(idUser, source, sourceType, outputType, changes);
		//invoca il service
		conversionService.insert(conversiontoinsert);
		request = new Request();
		request.put("mode", "mode");
		//Rimanda alla view con la risposta
		MainDispatcher.getInstance().callView( "ConversionInsert", request);
		}
	
}
