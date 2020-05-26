package com.sagar.learning.patterns;

/**
 * Created by sagarsingh on 2020-05-26
 */
public class KMPPatternMatching {

    public static boolean matchPatttern(String input, String pattern) {
        return false;
    }

    public static int[] computeLPS(String pattern) {
        int[] lps = new int[pattern.length()];
        int len = 0, index = 1;
        lps[0] = 0;
        while (index < pattern.length()) {
            if (pattern.charAt(index) == pattern.charAt(len)) {
                len++;
                lps[index] = len;
                index++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[index] = 0;
                    index++;
                }
            }
        }
        return lps;
    }
}
