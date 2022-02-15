package pl.edu.wszib.kotarba.garbage.management.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.edu.wszib.kotarba.garbage.management.model.Dumpster;
import pl.edu.wszib.kotarba.garbage.management.model.User;

@Component
@SessionScope
public class SessionObject {
    private User user = null;
    final Dumpster dumpster = new Dumpster();

    public boolean isLogged() {
        return this.user != null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Dumpster getDumpster() {
        return dumpster;
    }
}
