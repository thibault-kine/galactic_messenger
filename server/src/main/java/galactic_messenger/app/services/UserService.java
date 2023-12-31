package main.java.galactic_messenger.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import main.java.galactic_messenger.app.interfaces.UserRepository;
import main.java.galactic_messenger.app.models.UserEntity;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    // #region POST

    /**
     * Créé un utilisateur et hashe son mot de passe brut 
     * @param username    - Pseudo utilisateur
     * @param rawPassword - Mot de passe brut. Sera hashé par la suite
     */
    public void createUser(String username, String password) {
        if (isUsernameUnique(username)) {
            userRepo.saveAndFlush(new UserEntity(username, hashPassword(password)));
        } else {
            System.out.println("Cet username est déjà pris");
        }
    }

    private String hashPassword(String rawPassword) {
        return new BCryptPasswordEncoder().encode(rawPassword);
    }

    // #endregion

    // #region GET

    public UserEntity findById(int id) {
        return userRepo.findById(id).get();
    }

    public List<UserEntity> findAll() {
        return userRepo.findAll();
    }

    public UserEntity findByLogin(String username, String rawPassword) {
        UserEntity user = userRepo.findByUsername(username);
        if(user != null && new BCryptPasswordEncoder().matches(rawPassword, user.getPassword())) {
            return user;
        }

        return null;
    }

    private boolean isUsernameUnique(String username) {
        if(userRepo.findByUsername(username) != null) {
            return false;
        }

        return true;
    }

    // #endregion

    // #region DELETE

    public void delete(int id) {
        userRepo.delete(findById(id));
    }

    // #endregion
}
