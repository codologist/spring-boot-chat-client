package challenge.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User extends AuditModel implements Serializable {
    @Id
    @GeneratedValue(generator="userid_generator")
    @SequenceGenerator(
            name = "userid_generator",
            sequenceName = "userid_sequence",
            initialValue = 10
    )
    private int user_id;

    @NotBlank
    @Column(columnDefinition = "text")
    private String username;

    @NotBlank
    @Size(min = 6, max = 20)
    @Column(columnDefinition = "text")
    private String password;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
