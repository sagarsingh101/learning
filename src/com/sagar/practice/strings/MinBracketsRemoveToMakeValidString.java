package com.sagar.practice.strings;

/**
 * https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
 * Given a string s of '(' , ')' and lowercase English characters.
 *
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions )
 * so that the resulting parentheses string is valid and return any valid string.
 *
 * Formally, a parentheses string is valid if and only if:
 *
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 */
public class MinBracketsRemoveToMakeValidString {
    public static void main(String[] args) {
        MinBracketsRemoveToMakeValidString remover = new MinBracketsRemoveToMakeValidString();
        System.out.println(remover.minRemoveToMakeValid("sa(ga))(ri))()())"));
    }
    public String minRemoveToMakeValid(String s) {
        StringBuilder result =  new StringBuilder();
        int balance = 0;
        for(char c:s.toCharArray()) {
            if(c=='(') {
                balance++;
                result.append(c);
            }
            else if(c==')') {
                balance--;
                if(balance<0) balance=0;
                else result.append(c);
            } else {
                result.append(c);
            }
        }
        if(balance==0) return result.toString();
        int i = result.length()-1;
        while (balance>0 && i>=0) {
            if(result.charAt(i)=='(') {
                result.deleteCharAt(i);
                balance--;
            }
            i--;
        }
        return result.toString();
    }
}
