package it.contrader.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.contrader.service.ConversionLogService;

public class ConversionLogServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ConversionLogServlet() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mode = request.getParameter("mode");
		ConversionLogService service = new ConversionLogService();
		switch (mode) {
		case "readAll":
			request.setAttribute("usertype", request.getParameter("usertype"));
			request.setAttribute("log", service.getAll());
			getServletContext().getRequestDispatcher("/conversion/conversionlog.jsp").forward(request, response);
			break;

		case "read":
			request.setAttribute("log", service.getAllLogUser(Integer.parseInt(request.getParameter("userId"))));
			getServletContext().getRequestDispatcher("/conversion/conversionlog.jsp").forward(request, response);
			break;
		default:
			break;
		}
	}
	
}
