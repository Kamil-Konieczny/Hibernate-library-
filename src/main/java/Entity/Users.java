package Entity;

import javax.persistence.*;

@Entity
@Table(name = "Users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String user_name;
    private String user_surname;
    private String residence_place;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_surname() {
        return user_surname;
    }

    public void setUser_surname(String user_surname) {
        this.user_surname = user_surname;
    }

    public String getResidence_place() {
        return residence_place;
    }

    public void setResidence_place(String residence_place) {
        this.residence_place = residence_place;
    }
}
