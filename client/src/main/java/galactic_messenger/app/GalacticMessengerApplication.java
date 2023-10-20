package main.java.galactic_messenger.app;

import java.net.*;
import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GalacticMessengerApplication {

    public static void main(String[] args) {
        int port = 8080;
        String serverIp = "";

        serverIp = args[0];
        port = Integer.parseInt(args[1]);
        String ip = "";
        try {
            ip = get_ip();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        
        // Lancement de l'app Spring Boot
        SpringApplication app = new SpringApplication(GalacticMessengerApplication.class);
        ConfigurableApplicationContext context = app.run(args);

        // Récupère l'ip et le port
        // String ip = InetAddress.getLocalHost().getHostAddress(); This method isn't reliable, frequently returns localhost

        System.out.println("\n[ GALACTIC MESSENGER CLIENT ]");
        System.out.printf("Server available at %s:%d\n", ip, port);

        // Démarre la console du serveur
        ConsoleHandler console = new ConsoleHandler(serverIp, port);
        console.run();

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
