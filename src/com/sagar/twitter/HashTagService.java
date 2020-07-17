package com.sagar.twitter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This service will parse any given tweet using a regex pattern to separate out the hashTags , and will report
 * on the k highest used hashtags.
 */
public class HashTagService {
    private final Pattern hashTagPattern;
    private final HashTagCounter counter;

    public HashTagService() {
        this.hashTagPattern = Pattern.compile("(?:\\s|\\A)[##]+([A-Za-z0-9-_]+)");
        this.counter = new HashTagCounterMap();
    }

    public List<HashTag> getTopHashTags(int count) {
        return counter.getTopHashTags(count);
    }

    public void parseTweet(String tweet) {
        Matcher matcher = this.hashTagPattern.matcher(tweet);
        while (matcher.find()) {
            this.counter.addHashTag(matcher.group(0));
        }
    }
}
