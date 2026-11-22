import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.atomic.AtomicInteger;

@WebListener
public class ActiveSessionListener implements HttpSessionListener {

    private final AtomicInteger activeSessions = new AtomicInteger(0);

    @Override
    public void sessionCreated(HttpSessionEvent se) {

        int count = activeSessions.incrementAndGet();

        ServletContext context = se.getSession().getServletContext();

        context.setAttribute("activeSessions", count);

        System.out.println("New session created. Active sessions: " + count);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

        int count = activeSessions.decrementAndGet();

        ServletContext context = se.getSession().getServletContext();

        context.setAttribute("activeSessions", count);

        System.out.println("Session destroyed. Active sessions: " + count);
    }
}
