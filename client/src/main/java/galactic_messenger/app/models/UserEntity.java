package main.java.galactic_messenger.app.models;

import java.util.List;

public class UserEntity {

    private int id;

    // Pseudo utilisateur
    private String username;

    // Mot de passe non-hashé
    private String password;

    // private List<MessageEntity> messages;

    protected UserEntity() {}

    /**
     * @param username - Le pseudo utilisateur
     * @param password - Le mot de passe non-hashé
     */
    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("[ %s ]", this.username);
    }

    public int getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}
