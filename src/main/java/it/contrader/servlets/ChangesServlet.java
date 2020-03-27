package it.contrader.servlets;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayDeque;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.contrader.dto.ChangesDTO;
import it.contrader.service.ChangesService;

public class ChangesServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ChangesServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		final HttpSession session = request.getSession();
		int idUser = Integer.parseInt(request.getParameter("userId"));
		switch(request.getParameter("mode").toUpperCase()) {
			case "CREATE_CHANGES":
				String typeIn = request.getParameter("sourceType");
				String typeOut = request.getParameter("outputType");
				String source = request.getParameter("source");
				
				
				ArrayDeque<Map.Entry<String, String>> deque = new ArrayDeque<Map.Entry<String,String>>();
				Matcher m;			
				
				session.setAttribute("source", source);
				session.setAttribute("outputType", typeOut);
				session.setAttribute("sourceType", typeIn);
				switch(typeIn) {
					case "xml":
						m = Pattern.compile("\\<(.*?)\\>").matcher(source);
						  while(m.find()) {	
                                    Map.Entry<String,String> entry =
									    new AbstractMap.SimpleEntry<String, String>(m.group(1), m.group(1));
							    if(!deque.contains(entry) && !m.group(1).startsWith("/")) {
							    	deque.addLast(entry);
							    }
							    }
				
						session.setAttribute("changes", deque);
						getServletContext().getRequestDispatcher("/conversion/newchanges.jsp").forward(request, response);
						break;
					case "json":
						 m = Pattern.compile("\\\"(.*?)\\\":").matcher(source);
						  while(m.find()) {
							  Map.Entry<String,String> entry =
									    new AbstractMap.SimpleEntry<String, String>(m.group(1), m.group(1));
							    if(!deque.contains(entry) && !m.group(1).startsWith("/")) {
							    	deque.addLast(entry);
							    }
						  }
						session.setAttribute("changes", deque);
						getServletContext().getRequestDispatcher("/conversion/newchanges.jsp").forward(request, response);
						break;
				}
				break;
			
			case "INSERT_CHANGES":
			
				@SuppressWarnings("unchecked") ArrayDeque<Map.Entry<String, String>> newdeque = (ArrayDeque<Map.Entry<String, String>>) session.getAttribute("changes");
				for(Map.Entry<String, String> tagName : newdeque) {
					tagName.setValue(request.getParameter(tagName.getKey()));
				}
				ChangesService service = new ChangesService();
				session.setAttribute("changes", newdeque);
				service.insert(new ChangesDTO(request.getParameter("changesName"), newdeque.toString(), idUser));
				getServletContext().getRequestDispatcher("/ConversionServlet?mode=a&idChanges="+ 
						service.lastId(idUser) + "&idUser=" + idUser).forward(request, response);
			default:
				break;
		}
	}
	
	

}
