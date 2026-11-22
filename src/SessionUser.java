
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class SessionUser implements HttpSessionBindingListener {

    private String username;

    public SessionUser(String username) {
        this.username = username;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println(
            "Utilisateur " + username + " ajouté à la session."
        );
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println(
            "Utilisateur " + username + " retiré de la session."
        );
    }
}
