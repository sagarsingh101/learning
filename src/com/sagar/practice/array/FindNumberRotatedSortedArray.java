package com.sagar.practice.array;

public class FindNumberRotatedSortedArray {
    public static void main(String[] args) {
        FindNumberRotatedSortedArray finder = new FindNumberRotatedSortedArray();
          System.out.println(finder.findMin(new int[]{5,1,2,3,4}));
        System.out.println(finder.findMin(new int[]{4,5,6,7,0,1,2}));
        System.out.println(finder.findMin(new int[]{1,2,3,4,5,6,7}));
        System.out.println(finder.findMin(new int[]{7,6,5,4,3,2,1}));
    }
    public int search(int[] nums,int target) {
        int st=0,end=nums.length-1;
        while (st<=end) {
            int mid = (end-st)/2+st;
            if(nums[mid]==target) return mid;
            if(nums[mid]>nums[st]) {
                if(target>=nums[st] && target < nums[mid]) end = mid-1;
                else st= mid+1;
            } else {
                if(target <= nums[end] && target > nums[mid]) st = mid+1;
                else end=mid-1;
            }
        }
        return -1;
    }

    public int findMin(int[] nums) {
        int st=0,end=nums.length-1;
        while (st<=end) {
            int mid = (end-st)/2+st;
            if(mid==0) return Math.min(nums[st],nums[end]);
            if(mid==nums.length-1) return nums[mid];
            if(nums[mid-1]>nums[mid] && nums[mid+1]>nums[mid]) return nums[mid];
            if(nums[end]<nums[st]) {
                if(nums[mid]<nums[end]) end = mid-1;
                else st=mid+1;
            } else {
                end = mid - 1;
            }
        }
        return 0;
    }
}
