package mantisTests.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mantis_user_table")
public class User {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    public String getId() {
        return String.valueOf(id);
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
