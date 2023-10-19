package galactic_messenger.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import galactic_messenger.app.Session;
import galactic_messenger.app.models.UserEntity;
import galactic_messenger.app.services.UserService;

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
        if (user != null) {
            Session.set("current_user", user);
        } else {
            System.out.println("Impossible de se connecter");
        }
    }
}
