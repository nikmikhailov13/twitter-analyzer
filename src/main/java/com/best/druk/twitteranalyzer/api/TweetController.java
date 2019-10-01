package com.best.druk.twitteranalyzer.api;

import com.best.druk.twitteranalyzer.service.NLPService;
import com.best.druk.twitteranalyzer.service.TweetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class TweetController {

    private TweetService tweetManager;
    private NLPService nlpService;

    @GetMapping("/tweets/{topic}")
    public Map<String, Integer> getTweets(@PathVariable("topic") String topic, @RequestParam("limit") int limit) {

        ArrayList<String> tweets = tweetManager.getTweets(topic, limit);
        var tweetMap = new HashMap<String, Integer>();
        tweets.forEach(tweet -> tweetMap.put(tweet, nlpService.findSentiment(tweet)));
        System.out.println(tweetMap);
        return tweetMap;
    }

}
