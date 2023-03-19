package com.questionpro.hackernews.api.services;

import com.questionpro.hackernews.api.model.Story;
import com.questionpro.hackernews.api.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PastStoriesService {

    @Autowired
    private StoryRepository storyRepository;

    public List<Story> getPastStories() {
        return storyRepository.findAll();
    }
}

