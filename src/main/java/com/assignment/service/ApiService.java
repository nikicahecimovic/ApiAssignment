package com.assignment.service;

import com.assignment.model.Photo;
import com.assignment.model.PhotosChangelog;
import com.assignment.model.Tag;
import com.assignment.model.TagChangelog;
import com.assignment.repository.ChangelogRepository;
import com.assignment.repository.PhotosRepository;
import com.assignment.repository.TagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class ApiService {
    @Autowired
    private ChangelogRepository changelogRepository;

    @Autowired
    private PhotosRepository photosRepository;

    @Autowired
    private TagsRepository tagsRepository;

    public List<Photo> getPhotos() {
        return photosRepository.findAll();
    }

    public List<Photo> getFilteredPhotosWithTags(String tagValue) {
       return photosRepository.filterPhotosWithTags(tagValue);
    }

    public List<Photo> getFilteredPhotosWithoutTags(String tagValue) {
        return photosRepository.filterPhotosWithoutTags(tagValue);
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
        Set<TagChangelog> oldTagList = new HashSet<>();
        oldPhoto.setAuthor(editedPhoto.getAuthor());
        oldPhoto.setName(editedPhoto.getName());
        oldPhoto.setDescription(editedPhoto.getDescription());
        oldPhoto.setHeight(editedPhoto.getHeight());
        oldPhoto.setWidth(editedPhoto.getWidth());
        for (Tag tag : editedPhoto.getTags()) {
            TagChangelog oldTag = new TagChangelog();
            oldTag.setId(tag.getId());
            oldTag.setTag(tag.getTag());
            oldTag.setDateEdited(new Timestamp(date.getTime()));
            oldTagList.add(oldTag);
        }
        oldPhoto.setTags(oldTagList);
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
