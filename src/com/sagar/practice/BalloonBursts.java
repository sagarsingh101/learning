package com.sagar.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sagarsingh on 2020-03-13
 */
public class BalloonBursts {
    int[][] mem;
    public static void main(String[] args) {
        BalloonBursts b = new BalloonBursts();
        int[] nums = new int[]{8,3,4,3,5,0,5,6,6,2,8,5,6,2,3,8,3,5,1,0,2};
       System.out.println(b.maxCoins(nums));
    }
    public int maxCoins(int[] nums) {
        int n = nums.length+2;
        mem = new int[n][n];
        int[] copy = new int[n];
        for(int i=1;i<=nums.length;i++) {
            copy[i] = nums[i-1];
        }
        copy[0]=copy[n-1]=1;
        return calculateValue(copy,0,n-1);
    }

    private int calculateValue(int[] nums,int left,int right) {
        if(left+1==right) return 0;
        if(mem[left][right]>0) return mem[left][right];
        int maxForSet=0;
        for(int i=left+1;i<right;i++) {
         maxForSet = Math.max(maxForSet,nums[left]*nums[i]*nums[right]+calculateValue(nums,left,i)+calculateValue(nums,i,right));
        }
        mem[left][right]=maxForSet;
        return maxForSet;
    }
}
