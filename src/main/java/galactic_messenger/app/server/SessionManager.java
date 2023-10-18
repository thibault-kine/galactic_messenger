package galactic_messenger.app.server;

import jakarta.servlet.http.HttpSession;

public class SessionManager {
    
    public static HttpSession session;

    public static void setSession(HttpSession session) {
        SessionManager.session = session;
    }

    public static HttpSession getSession() {
        return session;
    }
}
