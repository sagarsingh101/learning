package com.sagar.practice.array;

public class MonotoneArray {
    public static void main(String[] args) {
        MonotoneArray arr = new MonotoneArray();
        System.out.println(arr.isMonotonic(new int[]{6,5,4,4}));
    }
    public boolean isMonotonic(int[] A) {
        if(A.length<2) return true;
        boolean increasing;
        int mid = (A.length)/2;
        int left = mid-1;
        int right = mid+1;
        while(left>=0 && A[left]==A[mid]) left--;
        while(right<A.length && A[right]==A[mid]) right++;
        if(left<0 && right>=A.length) return true;
        if(left<0) {
            increasing = A[mid]<A[right];
        } else if(right>=A.length) {
            increasing= A[mid]>A[left];
        } else {
            increasing = A[right]>=A[mid];
        }
        if(increasing) {
            for(int i=1;i<A.length;i++) {
                if(A[i]<A[i-1]) return false;
            }
        } else {
            for(int i=1;i<A.length;i++) {
                if(A[i]>A[i-1]) return false;
            }
        }
        return true;
    }

    public boolean isMonotonicSimple(int[] A) {
        boolean increasing = true;
        boolean decreasing = true;
        for (int i = 0; i < A.length - 1; ++i) {
            if (A[i] > A[i+1])
                increasing = false;
            if (A[i] < A[i+1])
                decreasing = false;
        }

        return increasing || decreasing;
    }
}
