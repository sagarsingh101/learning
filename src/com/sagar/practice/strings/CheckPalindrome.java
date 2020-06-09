package com.sagar.practice.strings;

/**
 * https://leetcode.com/problems/valid-palindrome/
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 *
 * Note: For the purpose of this problem, we define empty string as valid palindrome.
 */
public class CheckPalindrome {



    public boolean isPalindrome(String s) {
        int i=0,j=s.length()-1;
        while(i<j) {
            while(i<j && !isValid(s.charAt(i))) i++;
            while(i<j && !isValid(s.charAt(j))) j--;
            if(i<j ) {
                if(equal(s,i,j)) {
                    i++;
                    j--;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValid(char c) {
        return Character.isDigit(c) || Character.isAlphabetic(c);
    }

    public boolean equal(String s, int i, int j) {
        return Character.toLowerCase(s.charAt(i))==Character.toLowerCase(s.charAt(j));
    }
}
