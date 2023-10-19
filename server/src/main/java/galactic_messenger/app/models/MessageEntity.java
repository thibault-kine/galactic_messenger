package main.java.galactic_messenger.app.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GenerationType;

@Entity
public class MessageEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column
    private String message;


    public MessageEntity(UserEntity user, String message) {
        this.user = user;
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("%s dit :\n%s\n", this.user.getUsername(), this.message);
    }

    public int getId() {
        return this.id;
    }

    public UserEntity getUser() {
        return this.user;
    }

    public String getMessage() {
        return this.message;
    }
}
