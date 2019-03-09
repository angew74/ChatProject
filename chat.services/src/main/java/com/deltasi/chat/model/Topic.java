package com.deltasi.chat.model;




import javax.persistence.*;

@Entity
@Table(name = "topics")
public class Topic {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Id
    private Integer id;

    @Column(name = "title")
    private String title;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
