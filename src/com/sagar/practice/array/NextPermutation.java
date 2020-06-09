package com.sagar.practice.array;

import java.util.Arrays;

public class NextPermutation {
    public void nextPermutation(int[] nums) {
        boolean descending = false;
        int firstDip = -1;
        for(int i=nums.length-1;i>0;i--) {
            if(nums[i]>nums[i-1]) {
                descending = false;
                firstDip = i-1;
                break;
            }
        }
        if(descending) {
            Arrays.sort(nums);
        } else {
            Arrays.sort(nums,firstDip+1,nums.length);
        }
    }
}
