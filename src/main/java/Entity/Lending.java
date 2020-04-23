package Entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Table(name = "Lendings")
@Entity
public class Lending {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long user_id;
    @Id
    private Long book_id;
    private LocalDateTime date_of_lending;
    private boolean status=false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public LocalDateTime getDate_of_lending() {
        return date_of_lending;
    }

    public void setDate_of_lending(LocalDateTime date_of_lending) {
        this.date_of_lending = date_of_lending;
    }

    public String  getStatus() {
        String ret = null;
        if(status == false)
        {
            ret =  "in_progress";
        }
        else if(status == true)
        {
            ret = "returned";
        }
        return ret;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Lending{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", book_id=" + book_id +
                ", date_of_lending=" + date_of_lending +
                ", status=" + status +
                '}';
    }
}
