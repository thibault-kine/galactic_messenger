package main.java.galactic_messenger.app.services;

import java.io.DataOutputStream;
import java.net.*;
import java.util.*;

import main.java.galactic_messenger.app.ParameterStringBuilder;

public class UserService {
    
    public void register(String url, String username, String password) {
        try {
            URL _url = new URI(url).toURL();
            HttpURLConnection connection = (HttpURLConnection)_url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            Map<String, String> parameters = new HashMap<>();
            parameters.put("param1", "val");

            connection.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
            out.flush();
            out.close();
            connection.disconnect();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
