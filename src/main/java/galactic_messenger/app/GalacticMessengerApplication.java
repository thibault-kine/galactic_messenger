package galactic_messenger.app;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GalacticMessengerApplication {

    public static void main(String[] args) {

        int port = 8080;
        port = Integer.parseInt(args[0]);
        
        // Lancement de l'app Spring Boot
        SpringApplication app = new SpringApplication(GalacticMessengerApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", port));
        ConfigurableApplicationContext context = app.run(args);

        // Récupère l'IP et le port
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            System.out.printf("\nServer available at %s:%d\n", ip, port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        // Démarre la console du serveur
        ArgumentHandler argumentHandler = new ArgumentHandler(port);
        argumentHandler.run();

        context.close();
    }
}
