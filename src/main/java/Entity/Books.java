package Entity;

import javax.persistence.*;

@Entity
@Table(name = "Books")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String author_name;

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    private String author_surname;

    public String getAuthor_surname() {
        return author_surname;
    }

    public void setAuthor_surname(String author_surrname) {
        this.author_surname = author_surrname;
    }

    public String getTitle() {
        return title;
    }

    private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    private boolean status = false;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", author_name='" + author_name + '\'' +
                ", author_surrname='" + author_surname + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
