import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        String firstname = request.getParameter("firstName");
        String lastname = request.getParameter("lastName");
        String birthdate = request.getParameter("birth");
        String sex = request.getParameter("sex");
        response.setContentType("text/html");
        Cookie c1 = new Cookie("firstName", firstname);
        Cookie c2 = new Cookie("LastName", lastname);
        Cookie c3 = new Cookie("birthDay", birthdate);
        Cookie c4 = new Cookie("Sex", sex);

        c1.setMaxAge(60 * 60 * 24);
        c2.setMaxAge(60 * 60 * 24);
        c3.setMaxAge(60 * 60 * 24);
        c4.setMaxAge(60 * 60 * 24);

        response.addCookie(c1);
        response.addCookie(c2);
        response.addCookie(c3);
        response.addCookie(c4);
        
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>Résultat</title></head>");
        out.println("<body>");

        out.println("<h1>Informations reçues</h1>");

        out.println("<table border='1'>");

        out.println("<tr>");
        out.println("<th>Prénom</th>");
        out.println("<th>Nom</th>");
        out.println("<th>Date de naissance</th>");
        out.println("<th>Sexe</th>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>" + firstname + "</td>");
        out.println("<td>" + lastname + "</td>");
        out.println("<td>" + birthdate + "</td>");
        out.println("<td>" + sex + "</td>");
        out.println("</tr>");

        out.println("</table>");

        out.println("</body>");
        out.println("</html>");
    }
}