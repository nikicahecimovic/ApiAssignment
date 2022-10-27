package com.assignment.repository;

import com.assignment.model.PhotosChangelog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChangelogRepository extends JpaRepository<PhotosChangelog, Long> {
}
