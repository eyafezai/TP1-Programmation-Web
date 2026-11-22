
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
        System.out.println("SecurityFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        System.out.println("SecurityFilter: Before chain.doFilter()");

        // Vérifier si une session existe déjà
        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("user") != null) {

            System.out.println("SecurityFilter: User authorized");

            // L'utilisateur est connecté
            chain.doFilter(request, response);

            System.out.println("SecurityFilter: After chain.doFilter()");

        } else {

            System.out.println("SecurityFilter: Access refused");

            // L'utilisateur n'est pas connecté
            resp.sendRedirect(req.getContextPath() + "/login.html");
        }
    }

    @Override
    public void destroy() {
        System.out.println("SecurityFilter destroyed");
    }
}
