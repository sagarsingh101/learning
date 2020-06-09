package com.sagar.practice.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/subarrays-with-k-different-integers/
 */
public class SubArraysWithKdistinct {
    public static void main(String[] args) {
        SubArraysWithKdistinct kdistinct = new SubArraysWithKdistinct();
        System.out.println(kdistinct.subarraysWithKDistinct(new int[]{1,2,1,3,4},3));
    }
    public int subarraysWithKDistinct(int[] A, int K) {
        Map<Integer,Integer> countMap = new HashMap<>();
        int start = 0 , end=0 , count = 0;
        while(end<A.length) {
            countMap.put(A[end],countMap.getOrDefault(A[end],0)+1);
            if(countMap.size()==K) {
                count++;
            } else if(countMap.size()>K) {
                while(start<=end && countMap.size()>K) {
                    countMap.put(A[start],countMap.getOrDefault(A[start],0)-1);
                    if(countMap.get(A[start])==0) countMap.remove(A[start]);
                    else count++;
                    start++;
                }
            }
            end++;
        }
        return count;
    }
}
