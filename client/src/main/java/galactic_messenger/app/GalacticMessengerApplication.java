package app;

import java.net.*;
import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import galactic_messenger.app.server.ClientConsoleHandler;

@SpringBootApplication
public class GalacticMessengerApplication {

    public static void main(String[] args) throws SocketException {
        int port = 8080;
        String serverIp = "";

        serverIp = args[0];
        port = Integer.parseInt(args[1]);
        String ip = get_ip();
        
        // Lancement de l'app Spring Boot
        SpringApplication app = new SpringApplication(GalacticMessengerApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.address", ip));
        app.setDefaultProperties(Collections.singletonMap("server.port", port));
        ConfigurableApplicationContext context = app.run(args);

        // Récupère l'ip et le port
        // String ip = InetAddress.getLocalHost().getHostAddress(); This method isn't reliable, frequently returns localhost

        // System.out.printf("\nServer available at %s:%d\n", ip, port);
        System.out.println("\n");

        // Démarre la console du serveur
        ClientConsoleHandler argumentHandler = new ClientConsoleHandler(serverIp, port);
        argumentHandler.run();

        context.close();
    }
    
    public static String get_ip() throws SocketException {
        String ip = null;

        for (NetworkInterface ni :
                Collections.list(NetworkInterface.getNetworkInterfaces())) {
            for (InetAddress address : Collections.list(ni.getInetAddresses())) {
                if (address instanceof Inet4Address && address.isLoopbackAddress()) { // Discard any loopback ip
                    continue;
                }
                if (address instanceof Inet4Address){ // Takes ipv4 address and remove useless char
                    String temp = address.toString();
                    ip = temp.replace("/","");
                    // System.out.println(ip);
                }
            }
        }
        return ip;
    }
}
