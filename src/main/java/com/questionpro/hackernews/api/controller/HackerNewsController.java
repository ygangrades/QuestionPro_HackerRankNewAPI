package com.questionpro.hackernews.api.controller;

import com.questionpro.hackernews.api.model.Comment;
import com.questionpro.hackernews.api.model.Story;
import com.questionpro.hackernews.api.services.CommentsService;
import com.questionpro.hackernews.api.services.PastStoriesService;
import com.questionpro.hackernews.api.services.TopStoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HackerNewsController {

    @Autowired
    private TopStoriesService topStoriesService;

    @Autowired
    CommentsService commentsService;

    @Autowired
    PastStoriesService pastStoriesService;

    @GetMapping("/top-stories")
    public ResponseEntity<List<Story>> getTopStories() {
        List<Story> stories = topStoriesService.getTopStories();
        return ResponseEntity.ok(stories);
    }

    @GetMapping("/past-stories")
    public ResponseEntity<List<Story>> getPastStories() {
        List<Story> stories = pastStoriesService.getPastStories();
        return ResponseEntity.ok(stories);
    }

    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getComments(@RequestParam("storyId") Long storyId,
                                                     @RequestParam(value = "limit", defaultValue = "10") int limit) {
        List<Comment> comments = commentsService.getComments(storyId, limit);
        return ResponseEntity.ok(comments);
    }
}

