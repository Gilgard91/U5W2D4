package epicode.u5d7hw.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "blogposts")
public class Blogpost {
    @Id
    @GeneratedValue
    private int id;
    private String category;
    private String title;
    private String cover;
    private String content;
    private double readingTime;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Blogpost(String category, String title, String content, double readingTime, Author author) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.readingTime = readingTime;
        this.author = author;
    }

    public Blogpost(String category, String title, String cover, String content, double readingTime, Author author) {
        this.category = category;
        this.title = title;
        this.cover = cover;
        this.content = content;
        this.readingTime = readingTime;
        this.author = author;



    }
}
