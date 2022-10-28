package com.assignment.controller;

import com.assignment.model.Photo;
import com.assignment.model.Tag;
import com.assignment.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestApi {

    @Autowired
    ApiService apiService;

    @GetMapping(value = "photo/photos")
    public List<Photo> getPhotos(){
        return apiService.getPhotos();
    }

    @GetMapping(value = "photo/filterWithTags/{tagValue}")
    public List<Photo> filterPhotosWithTags(@PathVariable String tagValue){
        return apiService.getFilteredPhotosWithTags(tagValue);
    }

    @GetMapping(value = "photo/filterWithoutTags/{tagValue}")
    public List<Photo> filterPhotosWithoutTags(@PathVariable String tagValue){
        return apiService.getFilteredPhotosWithoutTags(tagValue);
    }

    @PostMapping(value = "photo/add")
    public void addPhoto(@RequestBody Photo photo){
        apiService.addPhoto(photo);
    }

    @DeleteMapping(value = "photo/delete/{id}")
    public void deletePhoto(@PathVariable Long id){
        apiService.deletePhoto(id);
    }

    //TODO ne brisu se editani tagovi nego im se samo makne photo_id??

    @PutMapping(value = "photo/edit/{id}")
    public void editPhoto(@PathVariable Long id, @RequestBody Photo photo){
        apiService.editPhoto(id, photo);
    }

    @PostMapping(value = "tag/add")
    public void addTag(@RequestBody Tag tag){
        apiService.addTag(tag);
    }

}
