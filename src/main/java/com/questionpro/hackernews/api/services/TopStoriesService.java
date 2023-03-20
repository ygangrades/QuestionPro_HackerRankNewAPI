package com.questionpro.hackernews.api.services;

import com.questionpro.hackernews.api.hackerclient.HackerNewsApiClient;
import com.questionpro.hackernews.api.model.Story;
import com.questionpro.hackernews.api.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TopStoriesService {
    private List<Story> topStories = new ArrayList<>();
    private LocalDateTime lastRefreshTime;

    @Autowired
    private HackerNewsApiClient hackerNewsApiClient;

    @Autowired
    private StoryRepository storyRepository;

    @Cacheable(value = "top-stories", key = "'top-stories'")
    public List<Story> getTopStories() {
        if (topStories == null || lastRefreshTime == null || lastRefreshTime.plusMinutes(15).isBefore(LocalDateTime.now())) {
            List<Long> topStoryIds = hackerNewsApiClient.getTopStories();
            topStories = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Story story = storyRepository.findById(topStoryIds.get(i)).orElse(null);
                if (story == null) {
                    story = hackerNewsApiClient.getStory(topStoryIds.get(i));
                    if (story != null) {
                        story.setKids(null);
                        storyRepository.save(story);
                    }
                }
                if (story != null) {
                    topStories.add(story);
                }
            }
            lastRefreshTime = LocalDateTime.now();
        }
        return topStories;
    }

    @CacheEvict(value = "top-stories", allEntries = true)
    @Scheduled(fixedDelay = 15*60*1000) // evict cache every 15 minutes
    public void evictCache()  {}
}

