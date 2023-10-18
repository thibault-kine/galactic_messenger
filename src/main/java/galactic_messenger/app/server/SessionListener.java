package galactic_messenger.app.server;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@Component
public class SessionListener implements HttpSessionListener {
    
    @Override
    public void sessionCreated(HttpSessionEvent e) {
        SessionManager.setSession(e.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent e) {
        // do nothing
    }
}
