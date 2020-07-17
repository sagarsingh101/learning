package com.sagar.twitter;

import java.util.Scanner;

/**
 * Created by sagarsingh on 2020-07-15
 */
public class Driver {
    public static void main(String[] args) {
        HashTagService parser = new HashTagService();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the tweets to be parsed : ");
        String tweet = scanner.nextLine();
        parser.parseTweet(tweet);
        System.out.println(parser.getTopHashTags(10));
    }
}
