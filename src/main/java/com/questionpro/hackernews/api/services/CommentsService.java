package com.questionpro.hackernews.api.services;

import com.questionpro.hackernews.api.hackerclient.HackerNewsApiClient;
import com.questionpro.hackernews.api.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CommentsService {

    @Autowired
    private HackerNewsApiClient hackerNewsApiClient;

    public List<Comment> getComments(Long storyId, int limit) {
        // Fetch the comments from the Hacker News API
        List<Comment> comments = hackerNewsApiClient.getCommentsByStoryId(storyId);

        // Sort the comments by number of child comments
        Collections.sort(comments, (c1, c2) -> c2.getKids().size() - c1.getKids().size());

        // Convert the comments to DTOs
        List<Comment> returnComments = new ArrayList<>();
        for (int i = 0; i < Math.min(limit, comments.size()); i++) {
            Comment comment = comments.get(i);
            returnComments.add(comment);
        }

        return returnComments;
    }
}

