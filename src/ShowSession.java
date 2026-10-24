import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.temporal.ChronoUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowSession extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        String fullName = (String) session.getAttribute("fullName");
        String birthDate = (String) session.getAttribute("birthDate");

        out.println("<html><head><meta charset='UTF-8'>");
        out.println("<title>Ma session</title></head><body>");

        // Première visite : demander les informations
        if (fullName == null || birthDate == null) {

            out.println("<h1>Welcome on my site!</h1>");
            out.println("<p>Veuillez saisir vos informations.</p>");

            out.println("<form action='ShowSession' method='post'>");

            out.println("Nom complet : ");
            out.println("<input type='text' name='fullName' required>");
            out.println("<br><br>");

            out.println("Date de naissance : ");
            out.println("<input type='date' name='birthDate' required>");
            out.println("<br><br>");

            out.println("<input type='submit' value='Envoyer'>");
            out.println("</form>");

        } else {

            // Visites après l'enregistrement du profil
            Integer visits = (Integer) session.getAttribute("visits");

            if (visits == null) {
                visits = 1;
            } else {
                visits++;
            }

            session.setAttribute("visits", visits);

            LocalDate birth = LocalDate.parse(birthDate);
            LocalDate today = LocalDate.now();

            // Prochain anniversaire
            LocalDate nextBirthday = birth.withYear(today.getYear());

            if (nextBirthday.isBefore(today)) {
                nextBirthday = birth.withYear(today.getYear() + 1);
            }

            long days = ChronoUnit.DAYS.between(today, nextBirthday);

            out.println("<h1>Welcome back!</h1>");
            out.println("<p>Hi, " + fullName + ".</p>");
            out.println("<p>There are " + days
                        + " days to your birthday.</p>");
            out.println("<p>Nombre de visites : " + visits + "</p>");
        }

        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String fullName = request.getParameter("fullName");
        String birthDate = request.getParameter("birthDate");

        try {
            // Vérifier la date reçue
            LocalDate.parse(birthDate);

            HttpSession session = request.getSession();

            session.setAttribute("fullName", fullName);
            session.setAttribute("birthDate", birthDate);

            // Afficher la page après l'enregistrement
            response.sendRedirect(
                request.getContextPath() + "/ShowSession"
            );

        } catch (RuntimeException e) {

            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println(
                "<p>Date invalide. Veuillez recommencer.</p>"
                + "<a href='ShowSession'>Retour</a>"
            );
        }
    }
}
