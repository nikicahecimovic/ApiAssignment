package com.assignment.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TagsChangelog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String value;
    @Column
    private LocalDateTime dateEdited = LocalDateTime.now();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(columnDefinition = "id")
    private Tag tag;
    private Long photoId;

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

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public LocalDateTime getDateEdited() {
        return dateEdited;
    }
}
