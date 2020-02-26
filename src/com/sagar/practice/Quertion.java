package com.sagar.practice;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

/**
 * Created by sagarsingh on 2020-02-03
 */
public class Quertion {
    String input = "my name is Jassim munir Jassim Jassim and and I am am taking taking interview interview";
    public String removeLastDuplicate(String input) {
        if(input==null || input.length()<2) return input;
        String[] words = input.split(" ");
        StringJoiner output = new StringJoiner(" ");
        Set<String> wordSet = new HashSet<>();
        Set<String> duplicateWords = new HashSet<>();

        for(String word:words) {
            if(wordSet.contains(word)) {
                duplicateWords.add(word);
            } else {
                wordSet.add(word);
            }
        }
        for(int i=words.length-1;i>=0;i--) {
            if(!duplicateWords.contains(words[i])) {
                output.add(words[i]);
            }  else {
                duplicateWords.remove(words[i]);
            }
        }
        return output.toString();
    }

}
