package com.assignment.model;

import javax.persistence.*;

@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column
    String tag;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "photo_id", referencedColumnName = "id")
//    Photo photo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
