package com.sagar.learning.strings;

import java.util.Arrays;

/**
 * Created by sagarsingh on 2020-05-26
 */
public class Driver {
    public static void main(String[] args) {
        String pattern = "ABABC";
        String input = "ABDABABCAWA";
        System.out.println(Arrays.toString(KMPPatternMatching.computeLPS(pattern)));
        System.out.println(KMPPatternMatching.matchPattern(input,pattern));
    }
}
