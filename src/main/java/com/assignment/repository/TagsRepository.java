package com.assignment.repository;

import com.assignment.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagsRepository extends JpaRepository <Tag, Long> {
}
