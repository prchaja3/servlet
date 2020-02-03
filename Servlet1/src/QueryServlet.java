

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QueryServlet
 */
@WebServlet("/query-servlet")
public class QueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryServlet() {
        super();
    }


    @Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>Seznam dostupných knih</title></head>");
		
		out.println("<body>");
		out.println("<h2>Seznam dostupných knih</h2>");
		
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ebookshop", "postgres", "Psql.3");
				Statement statement = connection.createStatement()){
			StringBuilder sb = new StringBuilder();
			sb.append("select * from books order by author limit 100");
			
			out.println("<p>Zobrazeno prvních 100 záznamů</p>");
			
			out.println("<table>");
			out.println("<tr>");
			out.println("<th>Název knihy</th>");
			out.println("<th>Autor</th>");
			out.println("<th>Cena</th>");
			out.println("<th>Množství</th>");
			out.println("</tr>");
			
			ResultSet results = statement.executeQuery(sb.toString());
			while(results.next()) {				
				out.println("<tr>");
				out.println("<td>" + results.getString("title") + "</td>");
				out.println("<td>" + results.getString("author") + "</td>");
				out.println("<td>" + results.getBigDecimal("price").toPlainString() + " Kč</td>");
				out.println("<td>" + results.getInt("qty") + "</td>");
				out.println("</tr>");
			}
			out.println("</table>");
			results.close();
			
			out.println("====================");
			
			results = statement.executeQuery("select count(*) as pocet from books");
			results.next();
			StringBuilder sb2 = new StringBuilder();
			sb2.append("<p>Celkový počet nalezených záznamů: ");
			sb2.append(results.getInt("pocet"));
			sb2.append("</p>");
			out.println(sb2.toString());
			
			out.println("<p>Vaše poslední vložená kniha:</p>");
			out.println("<p>");
			Cookie[] cookies = request.getCookies();
			if (cookies.length == 0) {
				out.println("Nebyl nalezen žádný záznam.");
			}
			else {
				for (int i = 0; i < cookies.length; i++) {
					if (cookies[i].getName().equals("title"))
						out.println(cookies[i].getValue() + "<br>");
				}
			}
			out.println("</p>");
			
		}
		catch (SQLException ex){
			out.println("<p>Chyba spojení s databází</p>");
			out.println("<p>Popis chyby: " + ex.getMessage() + "</p>");
			
			ex.printStackTrace();
		}
		
		out.println("</body></html>");
		out.close();
	}

}
