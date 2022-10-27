package com.assignment.repository;

import com.assignment.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotosRepository extends JpaRepository<Photo, Long> {
}
