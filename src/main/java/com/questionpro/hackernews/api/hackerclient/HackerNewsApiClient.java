package com.questionpro.hackernews.api.hackerclient;

import com.questionpro.hackernews.api.model.Comment;
import com.questionpro.hackernews.api.model.Story;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class HackerNewsApiClient {

    private final RestTemplate restTemplate;

    @Value("${hackernews.api.url}")
    private String apiUrl;

    public HackerNewsApiClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<Long> getTopStories() {
        String url = apiUrl + "/topstories.json";
        ResponseEntity<Long[]> response = restTemplate.getForEntity(url, Long[].class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return Arrays.asList(response.getBody());
        }
        return Collections.emptyList();
    }

    public Story getStory(Long storyId) {
        String url = apiUrl + "/item/" + storyId + ".json";
        ResponseEntity<Story> response = restTemplate.getForEntity(url, Story.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return null;
    }

    public List<Comment> getCommentsByStoryId(Long storyId) {
        String url = apiUrl + "/item/" + storyId + ".json";
        ResponseEntity<Story> responseEntity = restTemplate.getForEntity(url, Story.class);
        Story story = responseEntity.getBody();

        List<Long> commentIds = story.getKids();
        List<Comment> comments = new ArrayList<>();
        for (Long commentId : commentIds) {
            String commentUrl = apiUrl + "/item/" + commentId + ".json";
            ResponseEntity<Comment> commentResponseEntity = restTemplate.getForEntity(commentUrl, Comment.class);
            Comment comment = commentResponseEntity.getBody();
            if(comment.getKids() == null){
                comment.setKids(new ArrayList<>());
            }
            comments.add(comment);
        }

        return comments;
    }

}

