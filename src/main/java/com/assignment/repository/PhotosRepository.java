package com.assignment.repository;

import com.assignment.model.Photo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Set;

@Repository
public interface PhotosRepository extends JpaRepository<Photo, Long> {
    @Query("SELECT DISTINCT p " +
            "FROM Photo p " +
            "LEFT JOIN p.tags t " +
            "WHERE t.value = :tagValue " +
            "ORDER BY p.dateEdited")
    List<Photo> filterPhotosWithTags(@Param("tagValue") String tagValue, Pageable page);

    @Query(value = "SELECT *" +
            "FROM photos.photo p " +
            "LEFT JOIN photos.tag t ON p.id = t.photo_id " +
            "WHERE :tagValue NOT IN (t.value) " +
            "ORDER BY p.date_edited", nativeQuery = true)
    List<Photo> filterPhotosWithoutTags(@Param("tagValue") String tagValue, Pageable page);


}
