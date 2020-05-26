package com.sagar.practice;

/**
 * Created by sagarsingh on 2020-03-20
 */
public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        int[] input = new int[]{0,8,4,12,2};
        LongestIncreasingSubsequence sequence = new LongestIncreasingSubsequence();
        System.out.println(sequence.lengthOfLISFaster(input));
    }
    int[] mem;
    int lis;
    public int lengthOfLIS(int[] nums) {
        if(nums.length==0) return 0;
        lis=1;
        mem = new int[nums.length];
        for(int i=0;i<nums.length;i++) {
            mem[i]=Math.max(1,mem[i]);
            for(int j=i+1;j<nums.length;j++) {
                if(nums[j]>nums[i]) {
                    mem[j] = Math.max(mem[i]+1,mem[j]);
                    lis = Math.max(lis,mem[j]);
                }
            }
        }
        return lis;
    }

    public int lengthOfLISFaster(int[] nums) {
        if(nums.length==0) return 0;
        mem = new int[nums.length];
        for(int n:nums) {
            int index = search(n,0,lis-1);
            if(index<0) {
                index = -(index+1);
            }
            mem[index]=n;
            if(index==lis) {
                lis++;
            }
        }
        return lis;
    }

    private int search(int n,int st,int end) {
        if(st<=end) {
            int mid = (end-st)/2+st;
            if(mem[mid]==n) {
                return mid;
            }
            if(mem[mid]>n) {
                return search(n,st,mid-1);
            }
            return search(n,mid+1,end);
        }
        return -(st+1);
    }
}
