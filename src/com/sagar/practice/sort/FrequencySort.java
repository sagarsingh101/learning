package com.sagar.practice.sort;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class FrequencySort {
    public static void main(String[] args) {
        FrequencySort frequencySort = new FrequencySort();
        System.out.println(frequencySort.sort("abbbaacacf"));
    }
    Random random = new Random();
    public String sort(String s) {
        Map<Character,Integer> characterMap = new HashMap<>();
        for(char c:s.toCharArray()) {
            characterMap.put(c,characterMap.getOrDefault(c,0)+1);
        }
        int[][] freq = new int[characterMap.size()][2];
        int i=0;
        for(Map.Entry<Character,Integer> entry:characterMap.entrySet()) {
            freq[i][0] = entry.getKey();
            freq[i][1] = entry.getValue();
            i++;
        }

        quickSort(freq);
        StringBuilder sb = new StringBuilder();
        for(i=0;i<freq.length;i++) {
            int count = freq[i][1];
            while (count-->0) {
                sb.append((char) freq[i][0]);
            }
        }
        return sb.toString();
    }

    private void quickSort(int[][] m) {
        quickSort(m,0,m.length-1,1);
    }

    private void quickSort(int[][] m, int low, int high, int indexToMatch) {
        if(low<high) {
            int pivot  = partition(m,low,high,indexToMatch);
            quickSort(m,low,pivot-1,indexToMatch);
            quickSort(m,pivot+1,high,indexToMatch);
        }
    }

    private int partition(int[][] m, int low, int high, int indexToMatch) {
        int pivot = random.nextInt(high-low) + low;
        swap(m,pivot,high);
        int pos = low-1;
        for(int i=low;i<high;i++) {
            if(m[i][indexToMatch]>m[high][indexToMatch]) {
                pos++;
                swap(m,pos,i);
            }
        }
        swap(m,pos+1,high);
        return pos+1;
    }

    private void swap(int[][] m, int i, int j) {
        int[] temp = m[i];
        m[i] = m[j];
        m[j]=temp;
    }
}
