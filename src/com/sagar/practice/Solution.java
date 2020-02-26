package com.sagar.practice;


import java.util.*;

/**
 * Created by sagarsingh on 2020-01-26
 */
public class Solution {
    public static void main(String[] args) {
        int[] input = {0,1,0,3,12};
        shiftArray(input);
        for(int i:input) {
            System.out.println(i);
        }
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
