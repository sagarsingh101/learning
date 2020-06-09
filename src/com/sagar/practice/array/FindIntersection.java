package com.sagar.practice.array;

import java.util.*;

public class FindIntersection {
    public static void main(String[] args) {
        FindIntersection intersection = new FindIntersection();
        int[] num1 = new int[]{4,9,5};
        int[] num2 = new int[]{9,4,9,8,4};
        System.out.println(Arrays.toString(intersection.intersection(num1,num2)));
    }
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> uniqueItems = new HashSet<>();
        for(int i:nums1) {
            uniqueItems.add(i);
        }
        int i=0;
        for(int n:nums2) {
            if(uniqueItems.contains(n)) {
                nums1[i++] = n;
                uniqueItems.remove(n);
            }
        }
        return Arrays.copyOfRange(nums1,0,i);
    }
}
