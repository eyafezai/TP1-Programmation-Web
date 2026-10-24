
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VisitCounter extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int visits = 0;

        Cookie[] cookies = request.getCookies();

        // Rechercher le cookie visits
        if (cookies != null) {
            for (Cookie cookie : cookies) {

                if (cookie.getName().equals("visits")) {

                    try {
                        visits = Integer.parseInt(cookie.getValue());
                    } catch (NumberFormatException e) {
                        visits = 0;
                    }

                    break;
                }
            }
        }

        // Incrémenter le nombre de visites
        visits++;

        // Créer le cookie avec la nouvelle valeur
        Cookie newCookie = new Cookie(
            "visits",
            String.valueOf(visits)
        );

        newCookie.setMaxAge(60 * 60 * 24);
        newCookie.setPath(request.getContextPath());

        response.addCookie(newCookie);

        // Afficher le résultat
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='fr'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Compteur de visites</title>");
        out.println("</head>");
        out.println("<body>");

        out.println("<h1>Compteur de visites</h1>");
        out.println("<p>Nombre de visites : " + visits + "</p>");
        out.println("<p><a href='VisitCounter'>Visiter à nouveau</a></p>");
        out.println("<p><a href='index.html'>Retour au formulaire</a></p>");

        out.println("</body>");
        out.println("</html>");
    }
}
