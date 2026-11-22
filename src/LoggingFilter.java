import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LoggingFilter implements Filter {

    private String logPrefix;

    @Override
    public void init(FilterConfig config) throws ServletException {
        logPrefix = config.getInitParameter("logPrefix");

        System.out.println(logPrefix + " LoggingFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String url = req.getRequestURL().toString();

        System.out.println(logPrefix + " Before servlet: " + url);

        // Début du calcul du temps
        long startTime = System.nanoTime();

        // Continuer vers le filtre suivant ou la servlet
        chain.doFilter(request, response);

        // Fin du calcul du temps
        long endTime = System.nanoTime();

        long duration = endTime - startTime;

        double durationMs = duration / 1_000_000.0;

        System.out.println(logPrefix + " After servlet: " + url);

        System.out.println(logPrefix +
                " Execution time: " + durationMs + " ms");
    }

    @Override
    public void destroy() {
        System.out.println(logPrefix + " LoggingFilter destroyed");
    }
}
