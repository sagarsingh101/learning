package com.sagar.learning.patterns;

import java.util.Arrays;

/**
 * Created by sagarsingh on 2020-05-26
 */
public class Driver {
    public static void main(String[] args) {
        String pattern = "AAAFGAAAEAAGH";
        System.out.println(Arrays.toString(KMPPatternMatching.computeLPS(pattern)));
    }
}
