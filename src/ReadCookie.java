
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReadCookie extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<html><head><meta charset='UTF-8'>");
        out.println("<title>Lecture des cookies</title></head><body>");
        out.println("<h1>Valeurs des cookies</h1>");

        out.println("<table border='1' cellpadding='8'>");
        out.println("<tr><th>Cookie</th><th>Valeur</th></tr>");

        String[] names = {
            "firstName", "LastName", "birthDay", "Sex"
        };

        Cookie[] cookies = request.getCookies();

        for (String name : names) {

            String value = "Cookie introuvable";

            try {
                if (cookies != null) {
                    for (Cookie cookie : cookies) {

                        if (cookie.getName().equals(name)) {
                            value = URLDecoder.decode(
                                cookie.getValue(),
                                StandardCharsets.UTF_8
                            );
                            break;
                        }
                    }
                }

            } catch (IllegalArgumentException e) {
                value = "Erreur lors du décodage du cookie";
            }

            out.println("<tr><td>" + name + "</td><td>"
                        + value + "</td></tr>");
        }

        out.println("</table>");
        out.println("<p><a href='index.html'>Retour au formulaire</a></p>");
        out.println("</body></html>");
    }
}
