package com.assignment.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column
    String value;
    @Column
    LocalDateTime dateEdited = LocalDateTime.now();

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "photo_id", referencedColumnName = "id")
//    Photo photo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
