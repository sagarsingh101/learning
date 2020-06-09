package com.sagar.practice.slidingWindow;

import java.util.HashMap;
import java.util.Map;

public class BalanceString {
    public static void main(String[] args) {
        BalanceString balanceString = new BalanceString();
        System.out.println(balanceString.balancedString("WWQQRRRRQRQQ"));
    }
    public int balancedString(String s) {
        int len = s.length() , n = len/4;
        int minIndex = -1, maxIndex = -1;
        int q=0,e=0,r=0,w=0;
        char[] charArray = new char[]{'Q','W','E','R'};
        int[] charCount = new int[4];
        Map<Character,Integer> characterMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<len;i++) {
            char c = s.charAt(i);
            if(c==charArray[0]) charCount[0]++; ;
            if(c==charArray[1]) charCount[1]++; ;
            if(c==charArray[2]) charCount[2]++; ;
            if(c==charArray[3]) charCount[3]++; ;
        }

        for(int i=0;i<charCount.length;i++) {
            if(charCount[i]>n)
                characterMap.put(charArray[i],charCount[i]-n);
        }

        return minWindowSubstring(s,characterMap);
    }
    public int balancedStringBetter(String s) {
        if(s.length() % 4 != 0) return 0;
        int balanceFactor = s.length() / 4;
        char[] input = s.toCharArray();
        int[] charMap = new int[26];
        for(int i = 0; i < input.length; i++){
            charMap[input[i] - 'A']++;
        }

        int left = 0, right = 0, minWindowSize = Integer.MAX_VALUE;
        while(right < input.length){
            charMap[input[right] - 'A']--;
            //Q = 16 ; W = 22; E = 4; R = 17
            while(charMap[16] <= balanceFactor && charMap[22] <= balanceFactor && charMap[4] <= balanceFactor && charMap[17] <= balanceFactor && left < input.length){
                int currentWindowSize = right - left + 1;
                minWindowSize = Math.min(minWindowSize, currentWindowSize);
                charMap[input[left++] - 'A']++;
            }
            right++;
        }
        return minWindowSize;
    }

    private int minWindowSubstring(String s, Map<Character,Integer> map) {
        if(map.isEmpty()) return 0;
        int required = map.size();
        int found = 0;
        Map<Character,Integer> windowMap = new HashMap<>();
        int start = 0 , end=0,minLength=s.length()+1;
        while(end<s.length()) {
            char c = s.charAt(end);
            windowMap.put(c,windowMap.getOrDefault(c,0)+1);
            if(map.containsKey(c) && windowMap.get(c).equals(map.get(c))) {
                found++;
                if (found == required) {
                    while (start <= end && found == required) {
                        minLength = Math.min(end - start + 1, minLength);
                        c = s.charAt(start);
                        windowMap.put(c, windowMap.get(c) - 1);
                        if (map.containsKey(c) && map.get(c) > windowMap.get(c)) {
                            found--;
                        }
                        start++;
                    }
                }
            }
            end++;
        }
        return minLength;
    }
}
