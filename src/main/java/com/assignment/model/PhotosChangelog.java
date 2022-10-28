package com.assignment.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
public class PhotosChangelog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column
    Timestamp dateEdited;
    @Column
    String name;
    @Column
    String description;
    @Column
    String author;
    @Column
    String imageUrl;
    @Column
    Long width;
    @Column
    Long height;
    @OneToMany(targetEntity = TagChangelog.class)
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    Set<TagChangelog> tags;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Set<TagChangelog> getTags() {
        return tags;
    }

    public void setTags(Set<TagChangelog> tags) {
        this.tags = tags;
    }

    public Timestamp getDateEdited() {
        return dateEdited;
    }

    public void setDateEdited(Timestamp dateEdited) {
        this.dateEdited = dateEdited;
    }
}
