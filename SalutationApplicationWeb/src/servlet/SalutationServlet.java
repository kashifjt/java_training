package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import packt.Salutation;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/SalutationServlet" })
public class SalutationServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Context context = null;
	
	private Object salutation;


	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try
		{
			context = new InitialContext();
			salutation = (Salutation) context.lookup("java:global/SalutationApplicationEJB/Salutation");
		}catch(
		Exception e)
		{
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		try {
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet SalutationServlet</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>" + /*salutation.getFormalSalutation*/("Sherlock Holmes") + "</h1>");
			out.println("</body>");
			out.println("</html>");
		} finally {
			out.flush();
			out.close();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Hello Hello3");
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Hello Hello3");
		processRequest(request, response);
	}
}
