package com.sagar.practice;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sagarsingh on 2020-01-26
 */
public class Solution {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/sagarsingh/Downloads/dataops_280/viewIds.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/sagarsingh/Downloads/dataops_280/viewIds.txt"));
        Map<String, Integer> uniqueMap = new HashMap<>();
        bufferedReader.lines().forEach(line -> uniqueMap.put(line, uniqueMap.getOrDefault(line, 0) + 1));
        List<String> ids = new ArrayList<>(uniqueMap.keySet());
        ids.sort((s1, s2) -> uniqueMap.get(s2) - uniqueMap.get(s1));
        System.out.println(uniqueMap.size());
        ids.forEach((id -> System.out.println(id)));
    }
    private static int[] shiftArray(int[] input) {
        int i=0;
        while (i<input.length) {
            while(i<input.length && input[i]!=0) i++;
            int j=i+1;
            while(j<input.length && input[j]==0) j++;
            if(i>=input.length || j>=input.length) break;
            int temp = input[i];
            input[i]=input[j];
            input[j]=temp;
            i=j;
        }
        return input;
    }
}
