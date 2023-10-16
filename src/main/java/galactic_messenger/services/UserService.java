package galactic_messenger.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import galactic_messenger.interfaces.UserRepository;
import galactic_messenger.models.UserEntity;

@Service
public class UserService {

    private UserRepository repo;

    @Autowired
    public UserService(UserRepository repo) { this.repo = repo; }


    // POST
    public void createUser(String username, String rawPassword) {
        saveUser(new UserEntity(username, hashPassword(rawPassword)));
    }

    private void saveUser(UserEntity user) {
        repo.save(user);
    }

    private String hashPassword(String rawPassword) {
        return new BCryptPasswordEncoder().encode(rawPassword);
    }

    // GET
    public UserEntity getById(int id) {
        return repo.findById(id).get();
    }

    public List<UserEntity> getAll() {
        return repo.findAll();
    }

    // DELETE
    public void delete(int id) {
        repo.delete(getById(id));
    }
}
