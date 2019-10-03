package com.best.druk.twitteranalyzer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tweet {

    private String author;
    private String text;
    private int score;

}
