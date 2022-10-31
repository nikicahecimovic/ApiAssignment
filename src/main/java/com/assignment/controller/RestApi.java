package com.assignment.controller;

import com.assignment.model.Photo;
import com.assignment.model.PhotosChangelog;
import com.assignment.model.Tag;
import com.assignment.request.CreatePhotoRequest;
import com.assignment.request.UpdatePhotoRequest;
import com.assignment.response.PhotoResponse;
import com.assignment.response.TagResponse;
import com.assignment.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RestApi {

    @Autowired
    ApiService apiService;

    @GetMapping(value = "photo/photos")
    public List<PhotoResponse> getPhotos(){
        return getResponse(apiService.getPhotos());
    }

    @GetMapping(value = "photo/filterWithTags/{tagValue}")
    public List<PhotoResponse> filterPhotosWithTags(@PathVariable String tagValue){
        return getResponse(apiService.getFilteredPhotosWithTags(tagValue));
    }

    @GetMapping(value = "photo/filterWithoutTags/{tagValue}")
    public List<PhotoResponse> filterPhotosWithoutTags(@PathVariable String tagValue){
        return getResponse(apiService.getFilteredPhotosWithoutTags(tagValue));
    }

    @PostMapping(value = "photo/add")
    public void addPhoto(@RequestBody CreatePhotoRequest request){
        apiService.addPhoto(request);
    }

    @DeleteMapping(value = "photo/delete/{id}")
    public void deletePhoto(@PathVariable Long id){
        apiService.deletePhoto(id);
    }

    //TODO ne brisu se editani tagovi nego im se samo makne photo_id??

    @PutMapping(value = "photo/edit/{id}")
    public void editPhoto(@PathVariable Long id, @RequestBody UpdatePhotoRequest request){
        apiService.editPhoto(id, request);
    }

    @GetMapping(value = "photo/history/{id}/{time}")
    public PhotoResponse getPhotoHistory(@PathVariable Long id, @PathVariable String time){

       PhotosChangelog photo = apiService.getPhotoHistory(id, time);

        PhotoResponse photoResponse = new PhotoResponse();
        photoResponse.setId(photo.getPhoto().getId());
        photoResponse.setName(photo.getName());
        photoResponse.setDescription(photo.getDescription());
        photoResponse.setAuthor(photo.getAuthor());
        photoResponse.setImageUrl(photo.getImageUrl());
        photoResponse.setHeight(photo.getHeight());
        photoResponse.setWidth(photo.getWidth());

        return photoResponse;
    }

    @PostMapping(value = "tag/add")
    public void addTag(@RequestBody Tag tag){
        apiService.addTag(tag);
    }


    private List<PhotoResponse> getResponse(List<Photo> photos) {
        return photos.stream()
                .map(photo -> {
                    PhotoResponse photoResponse = new PhotoResponse();
                    photoResponse.setId(photo.getId());
                    photoResponse.setName(photo.getName());
                    photoResponse.setDescription(photo.getDescription());
                    photoResponse.setAuthor(photo.getAuthor());
                    photoResponse.setImageUrl(photo.getImageUrl());
                    photoResponse.setHeight(photo.getHeight());
                    photoResponse.setWidth(photo.getWidth());
                    photoResponse.setTags(
                            photo.getTags().stream()
                                    .map(tag -> {
                                        TagResponse tagResponse = new TagResponse();
                                        tagResponse.setId(tag.getId());
                                        tagResponse.setValue(tag.getValue());
                                        return tagResponse;
                                    })
                                    .collect(Collectors.toList())
                    );

                    return photoResponse;
                }).collect(Collectors.toList());
    }
}
