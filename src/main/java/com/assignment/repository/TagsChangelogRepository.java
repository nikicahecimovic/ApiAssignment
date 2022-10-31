package com.assignment.repository;

import com.assignment.model.TagsChangelog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TagsChangelogRepository extends JpaRepository<TagsChangelog, Long> {
//    @Query("SELECT * FROM TagsChangelog c WHERE c.dateEdited > :time AND c.photo.id = :photoId")
//    List<TagsChangelog> getTagsHistory(@Param("photoId") Long photoId, @Param("time") LocalDateTime ldt, Pageable pageable);


}
