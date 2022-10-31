package com.assignment.service;

import com.assignment.model.Photo;
import com.assignment.model.PhotosChangelog;
import com.assignment.model.Tag;
import com.assignment.model.TagsChangelog;
import com.assignment.repository.ChangelogRepository;
import com.assignment.repository.PhotosRepository;
import com.assignment.repository.TagsChangelogRepository;
import com.assignment.repository.TagsRepository;
import com.assignment.request.CreatePhotoRequest;
import com.assignment.request.UpdatePhotoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ApiService {
    private final ChangelogRepository changelogRepository;

    private final PhotosRepository photosRepository;

    private final TagsRepository tagsRepository;

    private final TagsChangelogRepository tagsChangelogRepository;

    @Autowired
    public ApiService(ChangelogRepository changelogRepository, PhotosRepository photosRepository, TagsRepository tagsRepository, TagsChangelogRepository tagsChangelogRepository) {
        this.changelogRepository = changelogRepository;
        this.photosRepository = photosRepository;
        this.tagsRepository = tagsRepository;
        this.tagsChangelogRepository = tagsChangelogRepository;
    }

    public List<Photo> getPhotos() {
        return photosRepository.findAll();
    }

    public Photo getPhotoById(Long id) {
        return photosRepository.getReferenceById(id);
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
        photo.setImageUrl(request.getImageUrl());
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
        for (Tag tag : photo.getTags()) {
            TagsChangelog oldLog = new TagsChangelog();
            oldLog.setValue(tag.getValue());
            oldLog.setTag(tag);
            oldLog.setPhotoId(photo.getId());
            tagsChangelogRepository.save(oldLog);
        }
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

    public PhotosChangelog getPhotoHistory(Long photoId, String time){
        LocalDateTime ldt = LocalDateTime.parse(time);
        List<PhotosChangelog> photoList = changelogRepository.getPhotoHistory(photoId, ldt, PageRequest.of(0,1));
        if (photoList.size()>0){
            return photoList.get(0);
        } else {
            return null;
        }
    }

    public List<TagsChangelog> getTagHistory(Long photoId, String time) {
        LocalDateTime ldt = LocalDateTime.parse(time);
        List<TagsChangelog> tagList = tagsChangelogRepository.getTagsHistory(photoId, ldt);
        List<TagsChangelog> close = new ArrayList<>();
        LocalDateTime temp = null;
        if (tagList.size() > 0) {
            temp = tagList.get(0).getDateEdited();
            ZonedDateTime zdt = temp.atZone(ZoneId.of("Europe/Paris"));
            for (TagsChangelog changelog : tagList) {
                long tagTime = changelog.getDateEdited().atZone(ZoneId.of("Europe/Paris")).toEpochSecond();
                long compareTime = zdt.toEpochSecond();
                if ((tagTime - compareTime) <= 1) {
                    close.add(changelog);
                }
            }
        }
        return close;
    }

    private static Tag mapTagFromRequest(String tagRequest, Photo photo) {
        Tag tag = new Tag();
        tag.setValue(tagRequest);
        return tag;
    }

    public void addTag(Tag tag) {
        tagsRepository.save(tag);
    }
}
