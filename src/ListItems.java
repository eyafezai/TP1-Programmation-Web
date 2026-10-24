import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ListItems extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String newItem = request.getParameter("newItem");

        HttpSession session = request.getSession();

        // Récupérer ou créer la liste des articles
        ArrayList<String> items =
                (ArrayList<String>) session.getAttribute("items");

        if (items == null) {
            items = new ArrayList<String>();
            session.setAttribute("items", items);
        }

        // Récupérer les anciens compteurs, quel que soit le type de Map
        Map<String, Integer> oldCounts =
                (Map<String, Integer>) session.getAttribute("counts");

        // Créer une LinkedHashMap pour conserver l'ordre d'insertion
        LinkedHashMap<String, Integer> counts =
                new LinkedHashMap<String, Integer>();

        if (oldCounts != null) {
            counts.putAll(oldCounts);
        }

        // Enregistrer la nouvelle Map dans la session
        session.setAttribute("counts", counts);

        // Ajouter l'article et incrémenter son compteur
        if (newItem != null && !newItem.trim().isEmpty()) {

            newItem = newItem.trim();

            items.add(newItem);

            int count = counts.getOrDefault(newItem, 0);
            counts.put(newItem, count + 1);
        }

        displayItems(response, counts);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // Récupérer les anciens compteurs
        Map<String, Integer> oldCounts =
                (Map<String, Integer>) session.getAttribute("counts");

        // Créer une nouvelle LinkedHashMap
        LinkedHashMap<String, Integer> counts =
                new LinkedHashMap<String, Integer>();

        if (oldCounts != null) {
            counts.putAll(oldCounts);
        }

        // Enregistrer la nouvelle Map dans la session
        session.setAttribute("counts", counts);

        displayItems(response, counts);
    }

    private void displayItems(HttpServletResponse response,
                              Map<String, Integer> counts)
            throws IOException {

        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='fr'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Ma liste</title>");
        out.println("</head>");
        out.println("<body>");

        out.println("<h1>Liste des articles</h1>");

        if (counts.isEmpty()) {

            out.println("<p>Aucun article pour le moment.</p>");

        } else {

            out.println("<table border='1' cellpadding='8'>");
            out.println("<tr>");
            out.println("<th>Article</th>");
            out.println("<th>Nombre de saisies</th>");
            out.println("</tr>");

            for (Map.Entry<String, Integer> entry : counts.entrySet()) {

                out.println("<tr>");
                out.println("<td>" + escapeHtml(entry.getKey()) + "</td>");
                out.println("<td>" + entry.getValue() + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
        }

        out.println("<br><a href='items.html'>Ajouter un article</a>");
        out.println("</body>");
        out.println("</html>");
    }

    private String escapeHtml(String value) {

        return value.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }
}
