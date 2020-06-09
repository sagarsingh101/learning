package com.sagar.practice.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TweetParser {
    private final Pattern hashTagPattern;
    private final HashTagCounter counter;

    public TweetParser() {
        this.hashTagPattern = Pattern.compile("(?:\\s|\\A)[##]+([A-Za-z0-9-_]+)");
        this.counter = new HashTagCounter();
    }

    public static void main(String[] args) {
        TweetParser parser = new TweetParser();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the tweets to be parsed : ");
        String tweet = scanner.nextLine();
        parser.parseHashTags(tweet);
        System.out.println(parser.getTopHashTags(10));
    }

    private List<HashTag> getTopHashTags(int count) {
        return counter.getTopHashTags(count);
    }

    private void parseHashTags(String tweet) {
        Matcher matcher = this.hashTagPattern.matcher(tweet);
        while (matcher.find()) {
            this.counter.addHashTag(matcher.group(0));
        }
    }


    class HashTagCounter {
        private final Map<String, HashTag> hashTagMap;
        private final TreeSet<HashTag> hashTagCounter;

        public HashTagCounter() {
            this.hashTagCounter = new TreeSet<>((h1, h2) -> h1.count == h2.count ? h1.value.compareTo(h2.value) : h2.count - h1.count);
            this.hashTagMap = new HashMap<>();
        }

        public void addHashTag(String value) {
            if (hashTagMap.containsKey(value)) {
                HashTag hashTag = hashTagMap.get(value);
                hashTagCounter.remove(hashTag);
                hashTag.incrementCount();
                ;
                hashTagCounter.add(hashTag);
            } else {
                HashTag tag = new HashTag(value);
                hashTagMap.put(value, tag);
                hashTagCounter.add(tag);
            }
        }

        public List<HashTag> getTopHashTags(int count) {
            int counter = 0;
            List<HashTag> hashTags = new ArrayList<>();
            for (HashTag tag : hashTagCounter) {
                hashTags.add(tag);
                counter++;
                if (counter >= count) break;
            }
            return hashTags;
        }
    }

    class HashTag {
        String value;
        int count;

        public HashTag(String value) {
            this.value = value;
        }

        public void incrementCount() {
            this.count++;
        }

        public String toString() {
            return value + " has been used " + count + " times";
        }
    }
}
