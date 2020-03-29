package it.contrader.servlets;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.json.XML;

import com.mysql.cj.util.StringUtils;

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
			@SuppressWarnings("unused") ArrayList<String> removeElements = 
					(ArrayList<String>) session.getAttribute("removeElements");
			
			ConversionDTO conversiontoinsert = new ConversionDTO( Integer.parseInt(request.getParameter("idUser")), 
					source, sourceType, outputType, Integer.parseInt(request.getParameter("idChanges")));
			conversionService.insert(conversiontoinsert);
			
			switch (sourceType.toLowerCase()) {
				case "xml":
					for(Map.Entry<String, String> tagName : newdeque) {
							if(removeElements!=null && !removeElements.isEmpty()) {
								for (String removeTag : removeElements) {
									Matcher tag = Pattern.compile("\\<" + removeTag + ">(.*?)\\" + "</" 
											+ removeTag + ">").matcher(source);
									while(tag.find()) {
										source = source.replace("<" + removeTag + ">" + tag.group(1) + "</" + removeTag + ">" , "");
									}
								}
							}
					if(newdeque!=null) {
						
							if(!tagName.getKey().equals(tagName.getValue())) {
								source = source.replaceAll("<" +  tagName.getKey() + ">",  "<" + tagName.getValue() + ">");
								source = source.replaceAll("</" +  tagName.getKey() + ">",  "</" + tagName.getValue() + ">");
							}
							
						}
					}
					obj = XML.toJSONObject(source);
					request.setAttribute("output", obj.toString());
					getServletContext().getRequestDispatcher("/conversion/conversionOutput.jsp").forward(request, response);
					break;
				case "json":
					obj = new JSONObject(source);
					String xml_output = XML.toString(obj);
					if(!removeElements.isEmpty()) {
						for (String removeTag : removeElements) {
							Matcher tag = Pattern.compile("\\<" + removeTag + ">(.*?)\\" + "</" 
									+ removeTag + ">").matcher(xml_output);
							while(tag.find()) {
								xml_output = xml_output.replace("<" + removeTag + ">" + tag.group(1) + "</" + removeTag + ">" , "");
							}
						}
					}
					if(newdeque!=null) {
						for(Map.Entry<String, String> tagName : newdeque) {
							if(!tagName.getKey().equals(tagName.getValue())) {
								xml_output = xml_output.replaceAll("<" +  tagName.getKey() + ">",  "<" + tagName.getValue() + ">");
								xml_output = xml_output.replaceAll("</" +  tagName.getKey() + ">",  "</" + tagName.getValue() + ">");
							}
						}
					}
				
					
					request.setAttribute("output", xml_output.replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
					getServletContext().getRequestDispatcher("/conversion/conversionOutput.jsp").forward(request, response);
					break;
				
				default:
					request.setAttribute("output", "incorrect input type");
					break;
					
			}

		}		
	}


}
