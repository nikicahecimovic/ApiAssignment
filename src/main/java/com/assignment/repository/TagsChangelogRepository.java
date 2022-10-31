package com.assignment.repository;

import com.assignment.model.TagsChangelog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TagsChangelogRepository extends JpaRepository<TagsChangelog, Long> {
    @Query(value = "SELECT * FROM tags_changelog c WHERE c.date_edited > :time AND c.photo_id = :photoId", nativeQuery = true)
    List<TagsChangelog> getTagsHistory(@Param("photoId") Long photoId, @Param("time") LocalDateTime ldt);
}
