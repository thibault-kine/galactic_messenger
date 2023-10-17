package galactic_messenger.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import galactic_messenger.services.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public void UserRegister(@RequestParam("username") String username, @RequestParam("password") String password) {
        service.createUser(username, password);
    }

    @PostMapping("/login") 
    public void UserLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
        // TODO: à implémenter avec Spring Security
    }
}
