package com.assignment.repository;

import com.assignment.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagsRepository extends JpaRepository <Tag, Long> {
    @Query(value = "SELECT * FROM Tag t WHERE t.photo_id = :id", nativeQuery = true)
    List<Tag> getTagsByPhotoId(@Param("id") Long id);
}
