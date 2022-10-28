package com.assignment.repository;

import com.assignment.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PhotosRepository extends JpaRepository<Photo, Long> {
    @Query("SELECT DISTINCT p " +
            "FROM Photo p " +
            "LEFT JOIN p.tags t " +
            "WHERE t.tag = :tagValue")
    List<Photo> filterPhotosWithTags(@Param("tagValue") String tagValue);

    @Query("SELECT p " +
            "FROM Photo p " +
            "LEFT JOIN p.tags t " +
            "WHERE t.tag <> :tagValue")
    List<Photo> filterPhotosWithoutTags(@Param("tagValue") String tagValue);

}
