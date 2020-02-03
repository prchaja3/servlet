

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/insert-servlet")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>Vložení nové knihy</title></head>");
		
		out.println("<body>");
		out.println("<h2>Vložení nové knihy</h2>");
		
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ebookshop", "postgres", "Psql.3");
				Statement statement = connection.createStatement()){
					StringBuilder sb = new StringBuilder();
					sb.append("insert into books (title, author, price, qty) values ('");
					sb.append(request.getParameter("title"));
					sb.append("','");
					sb.append(request.getParameter("author"));
					sb.append("','");
					sb.append(request.getParameter("price"));
					sb.append("','");
					sb.append(request.getParameter("qty"));
					sb.append("')");
					
					int insertCount = statement.executeUpdate(sb.toString());
					
					out.println("<p>Kniha byla úspěšně vložena.</p>");
					out.println("<p>Počet nových záznamů: " + insertCount + "</p>");
					
					Cookie c = new Cookie("title", request.getParameter("title"));
					response.addCookie(c);
				}
				catch (SQLException ex) {
					out.println("<p>Nepodařilo se vložit knihu.</p>");
					out.println("<p>Popis chyby: " + ex + "</p>");
				}
		
		out.println("<a href=\"http://localhost:9999/Servlet1/index.html\">Zpět na úvodní stranu</a>");
		
		out.println("</body></html>");
		out.close();
	}

}
