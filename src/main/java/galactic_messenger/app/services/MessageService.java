package galactic_messenger.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import galactic_messenger.app.interfaces.MessageRepository;
import galactic_messenger.app.models.MessageEntity;
import galactic_messenger.app.models.UserEntity;

@Service
public class MessageService {
    
    private MessageRepository repo;

    @Autowired
    public MessageService(MessageRepository repo) { this.repo = repo; }


    //#region POST

    public void writeMessage(UserEntity user, String message) {
        saveMessage(new MessageEntity(user, message));
    }

    private void saveMessage(MessageEntity message) {
        repo.save(message);
    }

    //#endregion

    //#region GET

    public List<MessageEntity> getAll() {
        return repo.findAll();
    }

    public List<MessageEntity> getAllByUser(UserEntity user) {
        List<MessageEntity> result = repo.findAll();
        for(MessageEntity msg : result) {
            // on enlève chaque message qui n'a pas été écrit par l'user
            if(!msg.getUser().equals(user)) {
                result.remove(msg);
            }
        }

        return result;
    }

    public MessageEntity getById(int id) {
        return repo.findById(null).get();
    }

    //#endregion

    //#region DELETE

    public void delete(int id) {
        repo.deleteById(id);
    }

    //#endregion
}
