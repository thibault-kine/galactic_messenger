package galactic_messenger.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // Pseudo utilisateur
    @Column
    private String username;

    // Mot de passe hashé
    @Column
    private String password;

    protected UserEntity() {}

    /**
     * @param username - Le pseudo utilisateur
     * @param password - Le mot de passe hashé
     */
    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return this.username;
    }

    public int getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getHashedPassword() {
        return this.password;
    }
}
