package it.contrader.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.json.XML;

import it.contrader.dto.ConversionDTO;
import it.contrader.service.ConversionService;


public class ConversionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ConversionServlet() {
		
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ConversionService conversionService = new ConversionService();
		String choice = request.getParameter("mode");
		
		String source;
		String sourceType;
		String outputType;
		int changes;
		
		switch(choice.toUpperCase()) {
		
		case "a":
			source = request.getParameter("source").toString();
			sourceType = request.getParameter("sourceType").toString();
			outputType = request.getParameter("outputType").toString();
			JSONObject obj;
			
			@SuppressWarnings("unchecked") Map<String,String> map = (Map<String, String>) request.getAttribute("changes");
			request.setAttribute("source", source);
			request.setAttribute("sourceType", sourceType);
			request.setAttribute("outputType", outputType);
			ConversionDTO conversiontoinsert = new ConversionDTO( source, sourceType, outputType);
			conversionService.insert(conversiontoinsert);
			
			switch (sourceType.toLowerCase()) {
				case "xml":
					if(map!=null) {
						for(Map.Entry<String, String> tagName : map.entrySet()) {
							source = source.replaceAll("<" +  tagName.getKey() + ">",  "<" + tagName.getValue() + ">");
							source = source.replaceAll("</" +  tagName.getKey() + ">",  "</" + tagName.getValue() + ">");	
						}
					}
					obj = XML.toJSONObject(source);
					request.setAttribute("output", obj.toString());
					break;
				case "json":
					if(map!=null) {
						for(Map.Entry<String, String> tagName : map.entrySet()) {
							source = source.replaceAll("\"" +  tagName.getKey() + "\":",  "\"" + tagName.getValue() + "\":");						}
					}
					obj = new JSONObject(source);
					request.setAttribute("output", obj.toString());
					break;
				
				default:
					request.setAttribute("output", "incorrect input type");
					break;
					
			}
		//getServletContext().getRequestDispatcher("/user/conversionmanager.jsp").forward(request, response);
		}
		//super.service(request, response);
		
	}


}
