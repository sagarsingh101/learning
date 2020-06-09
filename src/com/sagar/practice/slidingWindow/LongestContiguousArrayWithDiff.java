package com.sagar.practice.slidingWindow;

import java.util.TreeSet;

/**
 * https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/
 * Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray such that the
 * absolute difference between any two elements of this subarray is less than or equal to limit
 */
public class LongestContiguousArrayWithDiff {
    public static void main(String[] args) {
        LongestContiguousArrayWithDiff diff = new LongestContiguousArrayWithDiff();
        diff.longestSubarray(new int[]{8,2,4,7},4);
    }
    public int longestSubarrayUsingTreeSet(int[] nums, int limit) {
        if(nums.length==0) return 0;
        TreeSet<Integer> set = new TreeSet<>((a, b)->nums[a]==nums[b]?a-b:nums[a]-nums[b]);
        int i=0,left=0,length=0;
        while(i<nums.length) {
            set.add(i);
            while(nums[set.last()]-nums[set.first()]>limit) {
                set.remove(left++);
            }
            length = Math.max(length,i-left+1);
            i++;
        }
        return length;
    }

    public int longestSubarray(int[] nums, int limit) {
        int maxValue = Integer.MIN_VALUE;
        int minValue = Integer.MAX_VALUE;
        int start = 0,end=0,length=0;
        while(end<nums.length) {
            maxValue = Math.max(maxValue,Math.max(nums[start],nums[end]));
            minValue = Math.min(minValue,Math.min(nums[start],nums[end]));
            if(maxValue-minValue<=limit) {
                length = Math.max(length,end-start+1);
                end++;
            } else {
                start++;
                end = start;
                maxValue = nums[start];
                minValue = nums[start];
            }
        }
        return length;
    }
}
