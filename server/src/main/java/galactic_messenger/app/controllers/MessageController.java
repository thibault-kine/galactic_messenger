package main.java.galactic_messenger.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.java.galactic_messenger.app.models.MessageEntity;
import main.java.galactic_messenger.app.models.UserEntity;
import main.java.galactic_messenger.app.services.MessageService;


@RestController
public class MessageController {

    @Autowired
    private MessageService service;
    
    @GetMapping("/hello")
    public String Hello() {
        return "HELLO TEST !";
    }

    @GetMapping("/msg/all")
    public String AllMessages() {
        List<MessageEntity> messages = service.getAll();
        try {
            return new ObjectMapper().writeValueAsString(messages);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "No message found :(";
    }

    @PostMapping("/msg/new")
    public void NewMessage(@RequestBody UserEntity user, @RequestBody String message) {
        service.writeMessage(user, message);
    }
}
