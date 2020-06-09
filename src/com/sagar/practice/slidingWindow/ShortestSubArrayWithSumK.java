package com.sagar.practice.slidingWindow;

import java.util.Arrays;

public class ShortestSubArrayWithSumK {
    public static void main(String[] args) {
        ShortestSubArrayWithSumK arr = new ShortestSubArrayWithSumK();
        System.out.println(arr.shortestSubarray(new int[]{-28,81,-20,28,-29},89));
    }
    public int shortestSubarray(int[] A, int K) {
        int start=0,end=0,length=Integer.MAX_VALUE,currSum=0;
        while(end<A.length) {
            currSum+=A[end];
            if(currSum>=K) {
                while(start<=end && currSum>K) {
                    length = Math.min(length,end-start+1);
                    currSum-=A[start];
                    start++;
                }
            }
            end++;
        }
        while(start<end) {
            if(currSum>=K) {
                length = Math.min(length,end-start);
            }

            currSum-=A[start++];
        }
        return length==Integer.MAX_VALUE?-1:length;
    }
}
