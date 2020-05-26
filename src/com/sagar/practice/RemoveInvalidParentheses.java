package com.sagar.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sagarsingh on 2020-03-14
 */

/**
 * This is not working
 */
public class RemoveInvalidParentheses {
    public static void main(String[] args) {
        RemoveInvalidParentheses parentheses = new RemoveInvalidParentheses();
        System.out.println(parentheses.removeInvalidParentheses("(((k()(("));
    }

    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        int leftCount = 0;
        List<Integer> invalidIndexes = new ArrayList();
        for(int i=0;i<s.length();i++) {
            if(s.charAt(i)=='(') leftCount++;
            if(s.charAt(i)==')') leftCount--;
            if(leftCount<0) {
                invalidIndexes.add(i);
                leftCount++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s.length();i++) {
            if(!invalidIndexes.contains(i)) sb.append(s.charAt(i));
        }
        if(sb.length()==0) {
            result.add("");
            return result;
        }
        int i = sb.length()-1;
        while(sb.length()>0 && leftCount>0) {
            if(sb.charAt(i)=='(') {
                sb.deleteCharAt(i);
                leftCount--;
            }
            i--;
        }
        if(sb.length()==0) {
            result.add("");
            return result;
        }
        String valid = sb.toString();
        result.add(valid);
        for(int j:invalidIndexes) {
            int k = 0;
            while(k<valid.length() && k<j-1) {
                if(valid.charAt(k)==')') {
                    result.add(valid.substring(0,k) + valid.substring(k+1,j) + ')' + valid.substring(j));
                }
                k++;
            }
        }
        return result;

    }
}
