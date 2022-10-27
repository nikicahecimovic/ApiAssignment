package com.assignment.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class TagChangelog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column
    String tag;
    @Column
    Timestamp dateEdited;

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

    public Timestamp getDateEdited() {
        return dateEdited;
    }

    public void setDateEdited(Timestamp dateEdited) {
        this.dateEdited = dateEdited;
    }
}
