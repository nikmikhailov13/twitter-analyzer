package com.best.druk.twitteranalyzer.api;

import com.best.druk.twitteranalyzer.service.NLPService;
import com.best.druk.twitteranalyzer.service.TweetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@AllArgsConstructor
public class TweetController {

    private TweetService tweetManager;
    private NLPService nlpService;

    public void getTweets(){

        String topic = "ukraine";
        ArrayList<String> tweets = tweetManager.getTweets(topic);
        nlpService.init();
        for(String tweet : tweets) {
            System.out.println(tweet + " : " + nlpService.findSentiment(tweet));
        }
    }

}
