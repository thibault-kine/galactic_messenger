package galactic_messenger.app;

public class ArgumentHandler {
    
    final public String LOGIN_CMD = "/login";
    final private String[] LOGIN_ARGS = { "<username>", "<password>" };

    final public String REGISTER_CMD = "/register";
    final private String[] REGISTER_ARGS = { "<username>", "<password>" };

    final public String HELP_CMD = "/help";

    
    public void handleLogin(String[] args) {
        
    }

    public void handleRegister(String[] args) {

    }

    public void handleHelp() {
        System.out.println("\n\n");
        System.out.println("=== MENU D'AIDE ===\n");
        System.out.printf("Pour afficher toutes les commandes : \n\t %s\n", HELP_CMD);
        System.out.printf("Pour se connecter : \n\t %s %s %s\n", LOGIN_CMD, LOGIN_ARGS[0], LOGIN_ARGS[1]);
        System.out.printf("Pour s'inscrire : \n\t %s %s %s\n", REGISTER_CMD, REGISTER_ARGS[0], REGISTER_ARGS[1]);
    }
}
