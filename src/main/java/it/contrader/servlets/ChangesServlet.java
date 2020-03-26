package it.contrader.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		switch(request.getParameter("mode").toUpperCase()) {
			case "CREATE_CHANGES":
				String typeIn = request.getParameter("sourceType");
				String typeOut = request.getParameter("outputType");
				String source = request.getParameter("source");
				Map<String, String> map = new HashMap<String, String>();
				Matcher m;
				session.setAttribute("source", source);
				session.setAttribute("outputType", typeOut);
				session.setAttribute("sourceType", typeIn);
				switch(typeIn) {
					case "xml":
						m = Pattern.compile("\\</(.*?)\\>").matcher(source);
						while(m.find()) {
						    map.put(m.group(1), m.group(1));  
						}
						//request.setAttribute("changes", map);
						session.setAttribute("changes", map);
						getServletContext().getRequestDispatcher("/conversion/newchanges.jsp").forward(request, response);
						break;
					case "json":
						 m = Pattern.compile("\\\"(.*?)\\\":").matcher(source);
						  while(m.find()) {
							  if(!m.group(1).contains("\",\"")) {
								   map.put(m.group(1), m.group(1));
							  }
						  }
						request.setAttribute("changes", map);
						getServletContext().getRequestDispatcher("/conversion/newchanges.jsp").forward(request, response);
						break;
				}
				
			
			default:
				break;
		}
	}
	
	

}
