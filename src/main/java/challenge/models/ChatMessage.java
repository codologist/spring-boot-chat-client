package challenge.models;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "chats")
public class ChatMessage extends AuditModel{
    @Id
    @GeneratedValue(generator="messid_generator")
    @SequenceGenerator(
            name = "messid_generator",
            sequenceName = "messid_sequence",
            initialValue = 10
    )
    private int id;
    private int user_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
