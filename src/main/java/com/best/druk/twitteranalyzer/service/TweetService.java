package com.best.druk.twitteranalyzer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import twitter4j.*;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TweetService {

    public ArrayList<String> getTweets(String topic) {

        var twitter = new TwitterFactory().getInstance();
        var tweetList = new ArrayList<String>();
        try {
            var query = new Query(topic);
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    tweetList.add(tweet.getText());
                }
            } while ((query = result.nextQuery()) != null);
        } catch (TwitterException te) {
            log.error("Failed to search tweets: " + te.getMessage());
        }
        return tweetList;
    }
}