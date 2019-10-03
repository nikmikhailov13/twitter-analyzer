package com.best.druk.twitteranalyzer.api;

import com.best.druk.twitteranalyzer.model.Tweet;
import com.best.druk.twitteranalyzer.service.TweetService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
public class TweetController {

    private TweetService tweetManager;

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    @ResponseBody
    @GetMapping("/tweets/{topic}")
    public List<Tweet> getTweets(@PathVariable("topic") String topic, @RequestParam("limit") int limit) {
        log.info("Getting tweets");
        return tweetManager.getTweets(topic, limit);
    }


}
