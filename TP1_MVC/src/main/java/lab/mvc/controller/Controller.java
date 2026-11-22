
package lab.mvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.mvc.model.Person;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Recuperer les donnees du formulaire
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String birthDay = request.getParameter("birthDay");
        String sex = request.getParameter("sex");

        // 2. Creer le Model
        Person person = new Person(
            firstName,
            lastName,
            birthDay,
            sex
        );

        // 3. Ajouter le bean dans la requete
        request.setAttribute("person", person);

	     // Extraire l'annee de la date
	     int year = Integer.parseInt(birthDay.substring(0, 4));
	
	     // Choisir la JSP
	     String page;
	
	     if (year < 1990) {
	         page = "/old.jsp";
	     } else {
	         page = "/young.jsp";
	     }

        // 5. Transmettre la requete a la JSP
        RequestDispatcher dispatcher =
            getServletContext().getRequestDispatcher(page);

        dispatcher.forward(request, response);
    }
}
