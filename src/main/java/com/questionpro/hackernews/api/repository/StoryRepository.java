package com.questionpro.hackernews.api.repository;

import com.questionpro.hackernews.api.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {
}

