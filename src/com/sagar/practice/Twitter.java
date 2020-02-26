package com.sagar.practice;


import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sagarsingh on 2020-02-03
 */
public class Twitter {
    private int order;
    Map<Integer,Node> userMap;
    /** Initialize your data structure here. */
    public Twitter() {
        userMap= new HashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if(userMap.containsKey(userId)) {
            Node user = userMap.get(userId);
            user.tweet(new Tweet(tweetId,++order));
        }else {
            Node user = new Node(userId);
            user.tweet(new Tweet(tweetId,++order));
        }
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> feed = new ArrayList<>();
        Node user = userMap.get(userId);
        if(user!=null) {
            PriorityQueue<Tweet> pq = new PriorityQueue<>(10,(o1, o2)->o2.order-o1.order);
            for(int follow:user.followed) {
                for(Tweet tweet:userMap.get(follow).tweets) {
                    pq.add(tweet);
                    if(pq.size()==10) {
                        pq.poll();
                    }
                }
                feed.addAll(pq.stream().map(tweet -> tweet.id).collect(Collectors.toList()));
            }

        }
        return feed;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(userMap.containsKey(followerId) && userMap.containsKey(followeeId)) {
            userMap.get(followerId).follow(followeeId);
        }
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(userMap.containsKey(followerId) && userMap.containsKey(followeeId)) {
            userMap.get(followerId).followed.remove(followeeId);
        }
    }

    class Tweet {
        int id;
        int order;
        public Tweet(int id,int order) {
            this.id = id;
            this.order=order;
        }
    }
    class Node {
        int userId;
        Set<Integer> followed;
        Set<Tweet> tweets;
        public Node(int userId) {
            this.userId = userId;
            followed = new HashSet<>();
            tweets = new TreeSet<>((o1, o2)->o1.order-o2.order);
        }
        public void follow(int id) {
            followed.add(id);
        }
        public void tweet(Tweet tweet) {
            tweets.add(tweet);
        }
    }
}
