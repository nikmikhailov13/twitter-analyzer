package com.best.druk.twitteranalyzer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import twitter4j.*;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TweetService {

    public ArrayList<String> getTweets(String topic, int limit) {

        var twitter = new TwitterFactory().getInstance();
        var tweetList = new ArrayList<String>();
        try {
            var query = new Query(topic);
            query.setCount(limit);
            query.lang("en");
            QueryResult result;
            do {
                if (tweetList.size() == limit)
                    return tweetList;
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                tweets.forEach(tweet -> tweetList.add(tweet.getText()));
            } while ((query = result.nextQuery()) != null && tweetList.size() < limit);
        } catch (TwitterException te) {
            log.error("Failed to search tweets: " + te.getMessage());
        }
        return tweetList;
    }
}
