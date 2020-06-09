package com.sagar.learning.strings;

/**
 * Created by sagarsingh on 2020-05-26
 */
public class KMPPatternMatching {

    public static boolean matchPattern(String input, String pattern) {
        int[] lps = computeLPS(pattern);
        int i=0,j=0;
        while(i<input.length()) {
            if (input.charAt(i)==pattern.charAt(j)) {
                if(j==pattern.length()-1) {
                    return true;
                }
                j++;
            } else {
                if(j!=0)
                j=lps[j-1];
            }
            i++;
        }
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
