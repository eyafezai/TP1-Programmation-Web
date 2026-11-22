import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.annotation.WebListener;

@WebListener
public class SessionAttributeListener
        implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {

        System.out.println("Session attribute added: "
                + event.getName());

        System.out.println("Value: "
                + event.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {

        System.out.println("Session attribute modified: "
                + event.getName());

        System.out.println("Old value: "
                + event.getValue());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {

        System.out.println("Session attribute removed: "
                + event.getName());

        System.out.println("Removed value: "
                + event.getValue());
    }
}
