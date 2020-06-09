package com.sagar.practice.array;

/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 *
 * Note:
 *
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2
 */
public class MergeSortedArrays {
    public static void main(String[] args) {
        MergeSortedArrays merger = new MergeSortedArrays();
        merger.merge(new int[]{1,2,3,0,0,0},3,new int[]{2,5,6},3);
    }
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = m+n-1;
        while(m>=1 && n>=1) {
            if(nums1[m-1]>=nums2[n-1]){
                nums1[index]=nums1[m-1];
                m--;
            } else {
                nums1[index] = nums2[n-1];
                n--;
            }
            index--;
        }
        while(m>=1) {
            nums1[index--]=nums1[--m];
        }
        while(n>=1) nums1[index--]=nums2[--n];
    }
}
