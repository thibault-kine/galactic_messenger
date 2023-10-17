package galactic_messenger.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import galactic_messenger.app.models.UserEntity;
import galactic_messenger.app.services.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/user/all")
    public String allUsers() {
        List<UserEntity> users = service.getAll();
        try {
            return new ObjectMapper().writeValueAsString(users);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "No user found :(";
    }

    @PostMapping("/register")
    public void UserRegister(@RequestParam("username") String username, @RequestParam("password") String password) {
        service.createUser(username, password);
    }

    @PostMapping("/login") 
    public void UserLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
        
    }
}
