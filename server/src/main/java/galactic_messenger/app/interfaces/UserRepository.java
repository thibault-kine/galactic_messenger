package main.java.galactic_messenger.app.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import main.java.galactic_messenger.app.models.UserEntity;



public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByUsername(String username);
}
