package com.best.druk.twitteranalyzer.service;

import com.best.druk.twitteranalyzer.model.Tweet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import twitter4j.*;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TweetService {

    private final NLPService nlpService;
    private final Twitter twitter;

    public List<Tweet> getTweets(String topic, int limit) {

        List<Tweet> tweetList = new ArrayList<Tweet>();
        try {
            Query query = new Query(topic);
            query.setCount(limit);
            query.setCount(limit);
            QueryResult result;
            do {
                if (tweetList.size() == limit) {
                    return tweetList;
                }
                result = twitter.search(query);
                List<Status> tweetsStatus = result.getTweets();
                tweetsStatus.forEach(tweet -> tweetList.add(buildTweet(tweet)));

            } while ((query = result.nextQuery()) != null && tweetList.size() < limit);

        } catch (TwitterException te) {
            log.error("Failed to search tweets: " + te.getMessage());
        }
        return tweetList;
    }

    private Tweet buildTweet(Status status) {
        String text = status.isRetweet() ? status.getRetweetedStatus().getText() : status.getText();
        return new Tweet(status.getUser().getScreenName(), text, nlpService.findSentiment(text));
    }
}
