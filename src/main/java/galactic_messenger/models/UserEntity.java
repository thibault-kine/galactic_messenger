package galactic_messenger.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserEntity {
        @Id
        private Long id;
        private String name;
}
