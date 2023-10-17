package galactic_messenger.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import galactic_messenger.app.interfaces.UserRepository;
import galactic_messenger.app.models.UserEntity;

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
     * 
     * @param username    - Pseudo utilisateur
     * @param rawPassword - Mot de passe brut. Sera hashé par la suite
     */
    public void createUser(String username, String rawPassword) {
        saveUser(new UserEntity(username, hashPassword(rawPassword)));
    }

    private void saveUser(UserEntity user) {
        userRepo.save(user);
        userRepo.flush();
        System.out.println("ici");
    }

    private String hashPassword(String rawPassword) {
        return new BCryptPasswordEncoder().encode(rawPassword);
    }

    // #endregion

    // #region GET

    public UserEntity getById(int id) {
        return userRepo.findById(id).get();
    }

    public List<UserEntity> getAll() {
        return userRepo.findAll();
    }

    // #endregion

    // #region DELETE

    public void delete(int id) {
        userRepo.delete(getById(id));
    }

    // #endregion
}
