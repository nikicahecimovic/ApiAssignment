package com.assignment.service;

import com.assignment.model.Photo;
import com.assignment.model.PhotosChangelog;
import com.assignment.model.Tag;
import com.assignment.repository.ChangelogRepository;
import com.assignment.repository.PhotosRepository;
import com.assignment.repository.TagsRepository;
import com.assignment.request.CreatePhotoRequest;
import com.assignment.request.UpdatePhotoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ApiService {
    private final ChangelogRepository changelogRepository;

    private final PhotosRepository photosRepository;

    private final TagsRepository tagsRepository;

    @Autowired
    public ApiService(ChangelogRepository changelogRepository, PhotosRepository photosRepository, TagsRepository tagsRepository) {
        this.changelogRepository = changelogRepository;
        this.photosRepository = photosRepository;
        this.tagsRepository = tagsRepository;
    }

    public List<Photo> getPhotos() {
        return photosRepository.findAll();
    }

    public List<Photo> getFilteredPhotosWithTags(String tagValue) {
       return photosRepository.filterPhotosWithTags(tagValue);
    }

    public List<Photo> getFilteredPhotosWithoutTags(String tagValue) {
        return photosRepository.filterPhotosWithoutTags(tagValue);
    }

    public void addPhoto(CreatePhotoRequest request) {
        Photo photo = new Photo();
        photo.setAuthor(request.getAuthor());
        photo.setName(request.getName());
        photo.setDescription(request.getDescription());
        photo.setHeight(request.getHeight());
        photo.setWidth(request.getWidth());
        photo.setTags(
                request.getTags().stream()
                        .map(tag -> mapTagFromRequest(tag, photo))
                        .collect(Collectors.toSet()
                        )
        );


        photosRepository.save(photo);
    }

    public void deletePhoto(Long id) {
        photosRepository.deleteById(id);
    }

    //TODO ne brisu se editani tagovi nego im se samo makne photo_id??

    public void editPhoto(Long id, UpdatePhotoRequest request) {
        Photo photo = photosRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(String.format("No photo found with ID = %d", id)));

        PhotosChangelog changelog = new PhotosChangelog();
        changelog.setName(photo.getName());
        changelog.setAuthor(photo.getAuthor());
        changelog.setDescription(photo.getDescription());
        changelog.setHeight(photo.getHeight());
        changelog.setWidth(photo.getWidth());
        changelog.setImageUrl(photo.getImageUrl());
        changelog.setPhoto(photo);
        changelogRepository.save(changelog);

        photo.setAuthor(request.getAuthor());
        photo.setName(request.getName());
        photo.setDescription(request.getDescription());
        photo.setHeight(request.getHeight());
        photo.setWidth(request.getWidth());
        photo.setImageUrl(request.getImageUrl());
       photo.setTags(
               request.getTags().stream()
                .map(tag -> mapTagFromRequest(tag, photo))
               .collect(Collectors.toSet())
       );

        photosRepository.save(photo);
    }

    private static Tag mapTagFromRequest(String tagRequest, Photo photo) {
        Tag tag = new Tag();
        tag.setTag(tagRequest);
        return tag;
    }

    public void addTag(Tag tag) {
        tagsRepository.save(tag);
    }
}
