package it.contrader.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.contrader.dto.ChangesDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.ChangesService;

public class ChangesController implements Controller {

	private final String sub_package = "changes.";
	private ChangesService changesService;
	private Request request;
	
	public ChangesController() {
		// TODO Auto-generated constructor stub
		changesService = new ChangesService();
	}
	
	@Override
	public void doControl(Request request) {
		// TODO Auto-generated method stub
		this.request = new Request();
		
		switch(request.getString("choice").toString().toUpperCase()) {
			case "CREATE_CHANGES":
				String typeIn = request.get("sourceType").toString();
				String typeOut = request.get("outputType").toString();
				String source = request.get("source").toString();
				Map<String, String> map = new HashMap<String, String>();
				Matcher m;
				this.request.put("sourceType", typeIn);
				this.request.put("outputType", typeOut);
				switch(typeIn) {
					case "xml":
						m = Pattern.compile("\\</(.*?)\\>").matcher(source);
						while(m.find()) {
						    map.put(m.group(1), m.group(1));  
						}
						this.request.put("changes", map);
						MainDispatcher.getInstance().callView("Changes", this.request);
						break;
					case "json":
						 m = Pattern.compile("\\\"(.*?)\\\":").matcher(source);
						  while(m.find()) {
							  if(!m.group(1).contains("\",\"")) {
								   map.put(m.group(1), m.group(1));
							  }
						  }
						  this.request.put("changes", map);
						  MainDispatcher.getInstance().callView("Changes", this.request);
						break;
				}
				
			case "INSERT":
				ChangesDTO changesToInsert = new ChangesDTO(request.get("changesName").toString(), 
						request.get("changes").toString(),Integer.parseInt(request.get("idUser").toString()));
				this.request.put("insert", changesService.insert(changesToInsert));
				MainDispatcher.getInstance().callView(sub_package + "ChangesInsert", this.request);
				break;
				
			case "READ":
				MainDispatcher.getInstance().callView(sub_package + "ChangesRead", this.request);
				break;
			case "READ_ALL":
				this.request.put("read", changesService.getAll());
				MainDispatcher.getInstance().callView(sub_package + "ChangesRead", this.request);
				break;
			case "UPDATE":
				ChangesDTO changesToUpdate = new ChangesDTO(request.get("changesName").toString(), 
						request.get("changes").toString(),Integer.parseInt(request.get("idUser").toString()));
				this.request.put("update", changesService.update(changesToUpdate));
				MainDispatcher.getInstance().callView(sub_package + "ChangesUpdate", this.request);
				break;
			case "DELETE":
				this.request.put("delete", changesService.delete(Integer.parseInt(request.get("id").toString())));
				MainDispatcher.getInstance().callView(sub_package + "ChangesDelete", this.request);
				break;
			default:
				break;
		}
		
	}

}
