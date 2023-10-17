package galactic_messenger.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GalacticMessengerApplication {

    public static void main(String[] args) {
        ArgumentHandler argHandler = new ArgumentHandler();

        SpringApplication.run(GalacticMessengerApplication.class, args);

        if(args[0].equals(argHandler.HELP_CMD)) {
            argHandler.handleHelp();
        }
    }
}
