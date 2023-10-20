package main.java.galactic_messenger.app;

import java.util.Scanner;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import jakarta.json.JsonObject;
import main.java.galactic_messenger.app.models.UserEntity;
import main.java.galactic_messenger.app.services.UserService;


public class ConsoleHandler {

    UserService userService;

    private String serverIp;
    private int port = 8080;
    private String serverUrl;

    final public String HELP_CMD = "/help";

    final public String QUIT_CMD = "/quit";

    final public String REGISTER_CMD = "/register";
    final private String[] REGISTER_ARGS = { "<username>", "<password>" };

    final public String LOGIN_CMD = "/login";
    final private String[] LOGIN_ARGS = { "<username>", "<password>" };

    final public String LOGOUT_CMD = "/logout";


    public ConsoleHandler(String serverIp, int port) {
        this.serverIp = serverIp;
        this.port = port;
        this.serverUrl = String.format("http://%s:%d", this.serverIp, this.port);

        this.userService = new UserService();
    }

    /**
     * Démarre la console du serveur
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\n");
        boolean running = true;

        while (running) {

            UserEntity u = Session.get("current_user") != null
                    && Session.get("current_user").getClass() == UserEntity.class
                            ? (UserEntity) Session.get("current_user")
                            : null;

            System.out.printf("%s > ", u == null ? "[ INVITÉ ]" : u.toString());
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

                case LOGOUT_CMD:
                    if (Session.get("current_user") != null) {
                        handleLogout();
                    }
                    break;

                // QUIT_CMD
                case QUIT_CMD:
                    running = false;
                    break;

                default:
                    System.out.printf("Commande inconnue: '%s'\nVeuillez vous référer à '%s'\n", cmd, HELP_CMD);
                    break;
            }
        }

        sc.close();
    }

    private void handleLogin(String username, String password) {
        RestTemplate restTemplate = new RestTemplate(); // permet de faire des requêtes
        HttpHeaders httpHeaders = new HttpHeaders(); // headers

        // data de formulaire encodé dans l'URL (ex: "localhost/index?fname=John&lname=Cena")
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED); 
        // paramètres de la requête
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", username);
        map.add("password", password);

        // créé une nouvelle entité http
        HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(map, httpHeaders);
        // lance une requête post à un url donné, avec la requête donnée et un type de retour donné
        restTemplate.postForObject(this.serverUrl + "/user/login", req, JsonObject.class, map);
    }

    private void handleLogout() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();

        restTemplate.postForObject(this.serverUrl + "/user/logout", httpHeaders, Void.class);
    }

    private void handleRegister(String username, String password) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", username);
        map.add("password", password);

        HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(map, httpHeaders);
        restTemplate.postForObject(this.serverUrl + "/user/register", req, Void.class, map);

        userService.register(serverUrl, username, password);
    }

    private void handleHelp() {
        System.out.println("\n=== MENU D'AIDE ===\n");
        System.out.printf("Pour afficher toutes les commandes : \n\t %s\n", HELP_CMD);
        System.out.printf("Pour quitter le serveur : \n\t %s\n", QUIT_CMD);
        System.out.printf(
                "Pour s'inscrire : \n\t %s %s %s\n",
                REGISTER_CMD, REGISTER_ARGS[0], REGISTER_ARGS[1]);
        System.out.printf(
                "Pour se connecter : \n\t %s %s %s\n",
                LOGIN_CMD, LOGIN_ARGS[0], LOGIN_ARGS[1]);
        System.out.printf("Pour se déconnecter : \n\t %s\n", LOGOUT_CMD);

        System.out.println("\n");
    }
}