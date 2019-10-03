package com.best.druk.twitteranalyzer.api;

import com.best.druk.twitteranalyzer.service.NLPService;
import com.best.druk.twitteranalyzer.service.TweetService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@AllArgsConstructor
public class TweetController {

    private TweetService tweetManager;
    private NLPService nlpService;

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @GetMapping("/tweets/{topic}")
    public Map<String, Integer> getTweets(@PathVariable("topic") String topic, @RequestParam("limit") int limit) {

        var tweets = tweetManager.getTweets(topic, limit);
        var tweetMap = new HashMap<String, Integer>();
        tweets.forEach(tweet -> tweetMap.put(tweet, nlpService.findSentiment(tweet)));
        return tweetMap;
    }

}
