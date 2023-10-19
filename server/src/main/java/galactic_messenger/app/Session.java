package main.java.galactic_messenger.app;

import java.util.Dictionary;
import java.util.Hashtable;

public class Session {
    
    private static Dictionary<String, Object> session = new Hashtable<>();

    public static void set(String key, Object value) {
        Session.session.put(key, value);
    }

    public static void remove(String key) {
        Session.session.remove(key);
    }

    public static Object get(String key) {
        return Session.session.get(key);
    }
}
