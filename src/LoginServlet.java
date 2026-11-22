
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        // Récupérer le username envoyé par le formulaire
        String username = request.getParameter("username");

        // Créer ou récupérer la session
        HttpSession session = request.getSession();

        // Créer un objet SessionUser
        SessionUser user = new SessionUser(username);

        // Ajouter l'objet à la session
        session.setAttribute("user", user);

        // Afficher le résultat
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>Connexion</title></head>");
        out.println("<body>");

        out.println("<h2>Bienvenue " + escapeHtml(username) + " !</h2>");

        out.println("<p>Vous êtes connecté avec succès.</p>");

        out.println("<p>L'objet SessionUser a été ajouté à la session.</p>");

        out.println("<a href='items.html'>Accéder à items.html</a>");

        out.println("<br><br>");

        out.println("<a href='ListItems'>Accéder à ListItems</a>");

        out.println("<br><br>");

        // Lien pour supprimer l'objet de la session
        out.println("<a href='LoginServlet'>Déconnexion (supprimer l'objet)</a>");

        out.println("</body>");
        out.println("</html>");
    }

    // Méthode GET pour supprimer l'objet
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        // Récupérer la session existante sans en créer une nouvelle
        HttpSession session = request.getSession(false);

        out.println("<html>");
        out.println("<head><title>Déconnexion</title></head>");
        out.println("<body>");

        if (session != null && session.getAttribute("user") != null) {

            // Supprimer l'objet de la session
            session.removeAttribute("user");

            out.println("<h2>Objet supprimé de la session !</h2>");

            out.println("<p>La méthode valueUnbound() a été appelée.</p>");

        } else {

            out.println("<h2>Aucun utilisateur dans la session.</h2>");
        }

        out.println("<a href='login.html'>Retour à la connexion</a>");

        out.println("</body>");
        out.println("</html>");
    }

    private String escapeHtml(String value) {
        if (value == null) {
            return "";
        }

        return value.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }
}
