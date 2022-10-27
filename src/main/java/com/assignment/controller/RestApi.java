package com.assignment.controller;

import com.assignment.model.Photo;
import com.assignment.model.Tag;
import com.assignment.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestApi {

    @Autowired
    Service service;

    @GetMapping(value = "photo/photos")
    public List<Photo> getPhotos(){
        return service.getPhotos();
    }

    @PostMapping(value = "photo/add")
    public void addPhoto(@RequestBody Photo photo){
        service.addPhoto(photo);
    }

    @DeleteMapping(value = "photo/delete/{id}")
    public void deletePhoto(@PathVariable Long id){
        service.deletePhoto(id);
    }

    //TODO ne brisu se editani tagovi nego im se samo makne photo_id??

    @PutMapping(value = "photo/edit/{id}")
    public void editPhoto(@PathVariable Long id, @RequestBody Photo photo){
        service.editPhoto(id, photo);
    }

    @PostMapping(value = "tag/add")
    public void addTag(@RequestBody Tag tag){
        service.addTag(tag);
    }

}
