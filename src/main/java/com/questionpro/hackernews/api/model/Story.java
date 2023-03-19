package com.questionpro.hackernews.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "story")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Story {

    @Id
    private Long id;

    private String title;

    private String url;

    private Long score;

    private Timestamp time;

    private String by;

    @Transient
    @JsonIgnore
    private List<Long> kids;
}

