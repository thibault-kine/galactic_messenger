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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    public HttpSession session;

    @PostMapping("/user/register")
    public void UserRegister(@RequestParam("username") String username, @RequestParam("password") String password) {
        service.createUser(username, password);
    }

    @PostMapping("/user/login")
    public void UserLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
        UserEntity user = service.findByLogin(username, password);
        System.out.println("findByLogin ok");
        if(user != null) {
            // HttpSession session = request.getSession(false);
            System.out.println("user existe");
            session.setAttribute("user", user);
            System.out.println("Session : " + session.getAttribute("user"));
        }
        else {
            System.out.println("user null");
        }
    }
}
