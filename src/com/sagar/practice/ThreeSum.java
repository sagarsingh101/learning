package com.sagar.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sagarsingh on 2020-02-13
 */
public class ThreeSum {
    public static void main(String[] args) {
        int[] input = {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(input).toString());
    }
    public static List<List<Integer>> threeSum(int [] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++) {
            int j= i+1 , k=nums.length-1;
            int c = -nums[i];
            while (j<k) {
                int currSum = nums[j] + nums[k];
                if(currSum==c) {
                    result.add(Arrays.asList(nums[i],nums[j],nums[k]));
                    j++;
                    k--;
                }else if(currSum>c) {
                    k--;
                } else {
                    j++;
                }
            }
            while(nums[i]==nums[i+1]) i++;
        }
        return result;
    }
}
