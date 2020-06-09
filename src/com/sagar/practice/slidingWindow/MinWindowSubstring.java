package com.sagar.practice.slidingWindow;

import java.util.HashMap;
import java.util.Map;

public class MinWindowSubstring {
    public static void main(String[] args) {
        MinWindowSubstring windowSubstring = new MinWindowSubstring();
        System.out.println(windowSubstring.minWindow("ADOBECODEBANC","ABC"));
    }
    public String minWindow(String S,String T) {
        Map<Character,Integer> characterMap = new HashMap<>();
        for(char c:T.toCharArray()) {
            characterMap.put(c,characterMap.getOrDefault(c,0)+1);
        }
        Map<Character,Integer> windowMap = new HashMap<>();
        int required = characterMap.size();
        int found=0;
        int minLength = S.length()+1,winStart = 0,winEnd = 0;
        int start = 0 ,end = 0;
        while (end<S.length()) {
            char c = S.charAt(end);
            windowMap.put(c,windowMap.getOrDefault(c,0)+1);
            if(characterMap.getOrDefault(c,-1).equals(windowMap.get(c))) {
                found++;
            }
            if(found==required) {
                while (end>=start && found==required) {
                    c=S.charAt(start);
                    if(end-start+1<minLength) {
                        minLength=end-start+1;
                        winStart=start;
                        winEnd=end;
                    }
                    windowMap.put(c,windowMap.get(c)-1);
                    if(characterMap.getOrDefault(c,Integer.MIN_VALUE).intValue()>windowMap.get(c).intValue()) {
                        found--;
                    }
                    start++;
                }
            }
            end++;
        }
        return S.substring(winStart,winEnd+1);
    }
}
