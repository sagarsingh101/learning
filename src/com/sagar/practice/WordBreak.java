package com.sagar.practice;

import java.util.*;

/**
 * Created by sagarsingh on 2020-03-21
 */
public class WordBreak {
    public static void main(String[] args) {
        WordBreak wordBreak = new WordBreak();
        List<String> dict = Arrays.asList("cat","sand","dog");
        System.out.println(wordBreak.canBreak("catssanddog",dict));
    }
    Set<String> dictionary;
    public boolean canBreak(String s, List<String> wordDict) {
        if(s.length()==0) return true;
        if(wordDict.size()==0) return false;
        dictionary = new HashSet<>(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        boolean[] seen = new boolean[s.length()];
        while (!queue.isEmpty()) {
            int start = queue.poll();
            if(seen[start]) continue;
            for (int end=start+1;end<=s.length();end++) {
                if(dictionary.contains(s.substring(start,end))) {
                    queue.add(end);
                    if(end==s.length()) return true;
                }
            }
            seen[start]=true;
        }
        return false;
    }
}
