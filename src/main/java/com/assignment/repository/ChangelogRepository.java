package com.assignment.repository;

import com.assignment.model.Photo;
import com.assignment.model.PhotosChangelog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ChangelogRepository extends JpaRepository<PhotosChangelog, Long> {
    @Query("SELECT c FROM PhotosChangelog c WHERE c.dateEdited > :time AND c.photo.id = :photoId")
    List<PhotosChangelog> getPhotoHistory(@Param("photoId") Long photoId, @Param("time") LocalDateTime ldt, Pageable pageable);
}
