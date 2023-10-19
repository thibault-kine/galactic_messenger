package galactic_messenger.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import galactic_messenger.app.Session;
import galactic_messenger.app.models.UserEntity;
import galactic_messenger.app.services.UserService;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public void UserRegister(@RequestParam("username") String username, @RequestParam("password") String password) {
        service.createUser(username, password);
        System.out.println("Inscription réussie ! Pour vous connecter, veuillez utiliser la commande suivante :");
        System.out.println("\t/login <username> <password>");
    }

    @PostMapping("/login")
    public void UserLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
        UserEntity user = service.findByLogin(username, password);
        
        Session.set("current_user", user);
    }

    @PostMapping("/logout") 
    public void UserLogout() {
        Session.remove("current_user");
        System.out.println("Vous avez bien été déconnecté\n");
    }
}
