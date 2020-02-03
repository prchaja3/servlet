package servlet1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/sayhello")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public HelloServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head><title>Hello, world</title></head>");
		out.println("<body>");
		out.println("<h1>Hello, world!</h1>");
		
		out.println("<p>Request URI: " + request.getRequestURI() + "</p>");
		out.println("<p>Protocol: " + request.getProtocol() + "</p>");
		out.println("<p>PathInfo: " + request.getPathInfo()+ "</p>");
		out.println("<p>Remote Adress: " + request.getRemoteAddr()+ "</p>");
		
		out.println("<p>A Random Number: <strong>" + Math.random() + "</strong></p>");
		out.println("</body></html>");

		out.close();
	}

}
