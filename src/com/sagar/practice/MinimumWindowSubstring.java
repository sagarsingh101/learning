package com.sagar.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sagarsingh on 2020-03-15
 */
public class MinimumWindowSubstring {
    public static void main(String[] args) {
        MinimumWindowSubstring windowSubstring = new MinimumWindowSubstring();
        System.out.println(windowSubstring.minWindow("ADOBECODEBANC","ABC"));
    }
   Map<Character,Integer> charCountMap= new HashMap<>();
    public String minWindow(String s, String t) {
        int start =0,end=0,minLength=Integer.MAX_VALUE,minStart=0,minEnd=-1;
        if(t.length()==0 || s.length()==0) return "";
        populateMap(charCountMap,t);
        int curr = 0,tCurr =-1;
        while(curr<s.length()) {
            char c = s.charAt(curr);
            if(charCountMap.getOrDefault(c,0)>0) {
                tCurr++;
                charCountMap.put(c,charCountMap.get(c)-1);
                if(tCurr==t.length()-1) {
                    if(minLength>curr-start+1) {
                        minLength = curr-start+1;
                        minStart =start;
                        minEnd = curr+1;
                    }
                    charCountMap.put(s.charAt(start),charCountMap.get(s.charAt(start))+1);
                    start++;
                    while (start<curr && !charCountMap.containsKey(s.charAt(start))) start++;
                    tCurr--;
                }
            } else {
                if(tCurr==-1) start++;
            }
            curr++;
        }
        if(minEnd==-1) return "";
        return s.substring(minStart,minEnd);
    }

    private void populateMap(Map<Character, Integer> charCountMap, String t) {
        for(char c:t.toCharArray()) {
            charCountMap.put(c,charCountMap.getOrDefault(c,0)+1);
        }
    }

}
