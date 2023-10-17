package galactic_messenger.app;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GalacticMessengerApplication {

    public static void main(String[] args) {

        Options options = new Options();
        options.addOption("p", "port", true, "Numéro de port");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        }
        catch(ParseException e) {
            System.err.println("Erreur de parsing des arguments de la ligne de commande: " + e.getMessage());
            System.exit(1);
            return;
        }

        int port = 8080;
        if(cmd.hasOption("p")) {
            try {
                port = Integer.parseInt(cmd.getOptionValue("p"));
            }
            catch(NumberFormatException e) {
                System.err.println("Invalid port number: " + args[0]);
                System.exit(1);
                return;
            }
        }
        
        SpringApplication app = new SpringApplication(GalacticMessengerApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", port));
        ConfigurableApplicationContext context = app.run(args);

        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            System.out.printf("\nServer available at %s:%d\n", ip, port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
