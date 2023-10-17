package galactic_messenger.app;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Scanner;

import org.springframework.web.client.RestTemplate;

public class ArgumentHandler {

    final public String HELP_CMD = "/help";

    final public String QUIT_CMD = "/quit";

    final public String REGISTER_CMD = "/register";
    final private String[] REGISTER_ARGS = { "<username>", "<password>" };

    final public String LOGIN_CMD = "/login";
    final private String[] LOGIN_ARGS = { "<username>", "<password>" };

    public ArgumentHandler() {
        
    }

    /**
     * Démarre la console du serveur
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\n");
        boolean running = true;

        while (running) {
            String cmd = sc.nextLine();
            String[] args = cmd.split(" ");

            switch (args[0]) {
                // HELP
                case HELP_CMD:
                    handleHelp();
                    break;

                // REGISTER
                case REGISTER_CMD:
                    if (args.length != REGISTER_ARGS.length + 1) {
                        System.out.printf("Pour s'inscrire : \n\t %s %s %s\n", REGISTER_CMD, args[0], args[1]);
                    } else {
                        handleRegister(args[1], args[2]);
                    }
                    break;
                
                // LOGIN_CMD
                case LOGIN_CMD:
                    if (args.length != REGISTER_ARGS.length + 1) {
                        System.out.printf("Pour se connecter : \n\t %s %s %s\n", LOGIN_CMD, args[0], args[1]);
                    } else {
                        handleLogin(args[1], args[2]);
                    }
                    break;
                
                // QUIT_CMD
                case QUIT_CMD:
                    running = false;
                    break;
                
                default:
                    System.out.printf("Commande inconnue: '%s'\nVeuillez vous référer à '%s'.\n", cmd, HELP_CMD);
                    break;
            }
        }

        sc.close();
    }

    private void handleLogin(String username, String password) {

    }

    private void handleRegister(String username, String password) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", username);
        map.add("password", password);

        HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(map, httpHeaders);
        restTemplate.postForObject("http://localhost:1234/user/register", req, Void.class, map);
    }

    private void handleHelp() {
        System.out.println("\n");

        System.out.println("=== MENU D'AIDE ===\n");
        System.out.printf("Pour afficher toutes les commandes : \n\t %s\n", HELP_CMD);
        System.out.printf("Pour quitter le serveur : \n\t %s\n", QUIT_CMD);
        System.out.printf("Pour se connecter : \n\t %s %s %s (EN COURS ! NE PAS UTILISER)\n", LOGIN_CMD, LOGIN_ARGS[0],
                LOGIN_ARGS[1]);
        System.out.printf("Pour s'inscrire : \n\t %s %s %s (EN COURS ! NE PAS UTILISER)\n", REGISTER_CMD,
                REGISTER_ARGS[0], REGISTER_ARGS[1]);

        System.out.println("\n");
    }
}
