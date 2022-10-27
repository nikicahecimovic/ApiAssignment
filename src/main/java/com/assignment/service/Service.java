package com.assignment.service;

import com.assignment.model.Photo;
import com.assignment.model.PhotosChangelog;
import com.assignment.model.Tag;
import com.assignment.repository.ChangelogRepository;
import com.assignment.repository.PhotosRepository;
import com.assignment.repository.TagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
@Component
public class Service {
    @Autowired
    private ChangelogRepository changelogRepository;

    @Autowired
    private PhotosRepository photosRepository;

    @Autowired
    private TagsRepository tagsRepository;

    public List<Photo> getPhotos() {
        return photosRepository.findAll();
    }

    public void addPhoto(Photo photo) {
        photosRepository.save(photo);
    }

    public void deletePhoto(Long id) {
        photosRepository.deleteById(id);
    }

    //TODO ne brisu se editani tagovi nego im se samo makne photo_id??

    public void editPhoto(Long id, Photo photo) {
        Date date = new Date();
        Photo editedPhoto = photosRepository.findById(id).get();
        PhotosChangelog oldPhoto = new PhotosChangelog();
        oldPhoto.setAuthor(editedPhoto.getAuthor());
        oldPhoto.setName(editedPhoto.getName());
        oldPhoto.setDescription(editedPhoto.getDescription());
        oldPhoto.setHeight(editedPhoto.getHeight());
        oldPhoto.setWidth(editedPhoto.getWidth());
        oldPhoto.setTags(editedPhoto.getTags());
        oldPhoto.setDateEdited(new Timestamp(date.getTime()));

        editedPhoto.setAuthor(photo.getAuthor());
        editedPhoto.setName(photo.getName());
        editedPhoto.setDescription(photo.getDescription());
        editedPhoto.setHeight(photo.getHeight());
        editedPhoto.setWidth(photo.getWidth());
        editedPhoto.setTags(photo.getTags());

        photosRepository.save(editedPhoto);
        changelogRepository.save(oldPhoto);
    }

    public void addTag(Tag tag) {
        tagsRepository.save(tag);
    }
}
