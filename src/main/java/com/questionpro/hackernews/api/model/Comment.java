package com.questionpro.hackernews.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @JsonIgnore
    private Long id;

    private String text;

    private String by;

    @JsonIgnore
    private List<Long> kids;
}

