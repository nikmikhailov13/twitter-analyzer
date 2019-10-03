package com.best.druk.twitteranalyzer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Configuration
public class TwitterConfig {

    @Bean
    public Twitter twitter() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("VwjJWOrB5OMSGpGApALtivHbI")
                .setOAuthConsumerSecret("niyxjpg6JivZ2njDWVJp5o243z1Y2rxL3hggfZ52ZDNNH1dMzS")
                .setOAuthAccessToken("1179663443424632832-nX0Lab2f9WUfqCGLPioY6crkrwAYOk")
                .setOAuthAccessTokenSecret("Cc3TxWjBU0yVFXnCrwcGZBpETp2h93awwOi30SObptfpW")
                .setTweetModeExtended(true);
        TwitterFactory tf = new TwitterFactory(cb.build());
        return tf.getInstance();
    }

}
