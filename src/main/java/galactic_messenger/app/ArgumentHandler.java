package galactic_messenger.app;

import java.util.Scanner;

public class ArgumentHandler {

    final public String LOGIN_CMD = "/login";
    final private String[] LOGIN_ARGS = { "<username>", "<password>" };

    final public String REGISTER_CMD = "/register";
    final private String[] REGISTER_ARGS = { "<username>", "<password>" };

    final public String HELP_CMD = "/help";

    final public String QUIT_CMD = "/quit";


    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\n");
        boolean running = true;

        while (running) {
            String cmd = sc.nextLine();
            
            // HELP
            if (cmd.equals(HELP_CMD)) {
                handleHelp();
            }
            // REGISTER
            else if (cmd.equals(REGISTER_CMD)) {
                String[] args = cmd.split(" ");
                if(args.length != REGISTER_ARGS.length + 1) {
                    System.out.printf("Pour s'inscrire : \n\t %s %s %s\n", REGISTER_CMD, args[0], args[1]);
                }
                else {
                    handleRegister(args[1], args[2]);
                }
            } 
            // LOGIN
            else if (cmd.equals(LOGIN_CMD)) {
                String[] args = cmd.split(" ");
                if(args.length != REGISTER_ARGS.length + 1) {
                    System.out.printf("Pour se connecter : \n\t %s %s %s\n", LOGIN_CMD, args[0], args[1]);
                }
                else {
                    handleLogin(args[1], args[2]);
                }
            }
            // QUIT
            else if (cmd.equals(QUIT_CMD)) {
                running = false;
            }
            else {
                System.out.printf("Commande inconnue: '%s'\nVeuillez vous référer à '%s'.\n", cmd, HELP_CMD);
            }
        }

        sc.close();
    }

    private void handleLogin(String username, String password) {

    }

    private void handleRegister(String username, String password) {

    }

    private void handleHelp() {
        System.out.println("\n");
        System.out.println("=== MENU D'AIDE ===\n");
        System.out.printf("Pour afficher toutes les commandes : \n\t %s\n", HELP_CMD);
        System.out.printf("Pour quitter le serveur : \n\t %s\n", QUIT_CMD);
        System.out.printf("Pour se connecter : \n\t %s %s %s (EN COURS ! NE PAS UTILISER)\n", LOGIN_CMD, LOGIN_ARGS[0], LOGIN_ARGS[1]);
        System.out.printf("Pour s'inscrire : \n\t %s %s %s (EN COURS ! NE PAS UTILISER)\n", REGISTER_CMD, REGISTER_ARGS[0], REGISTER_ARGS[1]);
    }
}
