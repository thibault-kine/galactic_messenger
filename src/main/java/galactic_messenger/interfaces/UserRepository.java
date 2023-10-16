package galactic_messenger.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import galactic_messenger.models.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> { }
