package com.sagar.practice.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindKClosestElements {
    public static void main(String[] args) {
        FindKClosestElements closestElements = new FindKClosestElements();
     //   System.out.println(closestElements.findClosestElements(new int[]{1,2,3,4,5},4,3));
      //  System.out.println(closestElements.findClosestElements(new int[]{1,2,4,5},2,3));
        System.out.println(closestElements.findClosestElements(new int[]{0,0,1,2,3,3,4,7,7,8},3,5));
    }
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int pivot=findInsertionIndex(arr, x);
        int low = arr[pivot]==x?pivot:pivot-1;
        int high = arr[pivot]==x?pivot+1:pivot;
        while((high-low)<=k) {
            if(low>=0 && high<arr.length) {
                if(x-arr[low]<=arr[high]-x) {
                    low--;
                } else {
                    high++;
                }
            } else if(low<0) {
                high++;
            }else {
                low--;
            }
        }
        if(high>=arr.length) high = arr.length-1;
        List<Integer> result = new ArrayList<>();
        for(int i=low+1;i<=low+k;i++) {
            result.add(arr[i]);
        }
        return result;
    }

    private int findInsertionIndex(int[] arr,int x) {
        int st=0,end=arr.length-1;
        while(st<end) {
            int mid = (end-st)/2+st;
            if(arr[mid]>=x) {
                end = mid;
            } else{
                st=mid+1;
            }
        }
            return st;
    }
}
