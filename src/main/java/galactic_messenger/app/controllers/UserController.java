package galactic_messenger.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import galactic_messenger.app.models.UserEntity;
import galactic_messenger.app.services.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    public String allUsers() {
        List<UserEntity> users = service.getAll();
        try {
            return new ObjectMapper().writeValueAsString(users);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "No user found :(";
    }

    @PostMapping("/user/register")
    public void UserRegister(@RequestParam("username") String username, @RequestParam("password") String password) {
        service.createUser(username, password);
    }

    public void UserLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
        
    }
}
