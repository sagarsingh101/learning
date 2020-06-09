package com.sagar.practice.strings;

/**
 * https://leetcode.com/problems/valid-palindrome-ii/
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 */
public class MakePalindrome {
    public boolean validPalindrome(String s) {
        int i=0;
        int j=s.length()-1;
        while(i<j) {
            if(s.charAt(i)==s.charAt(j)) {
                i++;
                j--;
            } else {
                return isPalindrome(s,i,j-1) || isPalindrome(s,i+1,j);
            }
        }
        return true;
    }

    public boolean isPalindrome(String s, int st , int end) {
        while(st<end) {
            if(s.charAt(st) !=s.charAt(end)) return false;
            st++;
            end--;
        }
        return true;
    }
}
