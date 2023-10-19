package galactic_messenger.app.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import galactic_messenger.app.models.MessageEntity;

public interface MessageRepository extends JpaRepository<MessageEntity, Integer> { }
