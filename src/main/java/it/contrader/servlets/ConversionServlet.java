package it.contrader.servlets;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		final HttpSession session = request.getSession();
		ConversionService conversionService = new ConversionService();
		String choice = request.getParameter("mode");
		
		String source;
		String sourceType;
		String outputType;
		int changes;
		
		switch(choice.toLowerCase()) {
		
		case "a":
			source = session.getAttribute("source").toString();
			sourceType = session.getAttribute("sourceType").toString();
			outputType = session.getAttribute("outputType").toString();
			JSONObject obj;
			@SuppressWarnings("unchecked")	 ArrayDeque<Map.Entry<String, String>> newdeque =
					(ArrayDeque<Map.Entry<String, String>>) session.getAttribute("changes");
			//@SuppressWarnings("unchecked") Map<String,String> map = (Map<String, String>) session.getAttribute("changes");
			
			ConversionDTO conversiontoinsert = new ConversionDTO( Integer.parseInt(request.getParameter("idUser")), 
					source, sourceType, outputType, Integer.parseInt(request.getParameter("idChanges")));
			conversionService.insert(conversiontoinsert);
			
			switch (sourceType.toLowerCase()) {
				case "xml":
					
					if(newdeque!=null) {
						for(Map.Entry<String, String> tagName : newdeque) {
							source = source.replaceAll("<" +  tagName.getKey() + ">",  "<" + tagName.getValue() + ">");
							source = source.replaceAll("</" +  tagName.getKey() + ">",  "</" + tagName.getValue() + ">");
						}
					}
					obj = XML.toJSONObject(source);
					request.setAttribute("output", obj.toString());
					getServletContext().getRequestDispatcher("/conversion/conversionOutput.jsp").forward(request, response);
					break;
				case "json":
					if(newdeque!=null) {
						for(Map.Entry<String, String> tagName : newdeque) {
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
