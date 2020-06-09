package com.sagar.practice.slidingWindow;

public class MaxConsecutiveOne {
    public static void main(String[] args) {
        MaxConsecutiveOne consecutiveOne = new MaxConsecutiveOne();
        consecutiveOne.longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0},2);
    }
    public int longestOnes(int[] A, int K) {

        int start = 0, end=0;
        int inverted = 0,length=0;
        while(end<A.length) {
            if(A[end]==0) {
                if(inverted==K) {
                    while(start<=end && A[start]==1) start++;
                    start++;
                } else {
                    inverted++;
                }
            }
            length = Math.max(end-start+1,length);
            end++;
        }
        return Math.max(length,end-start);
    }

    public int longestOnesIntuition(int[] A, int K) {

       int left =0 , right =0;
       while (right<A.length) {
           if(A[right]==0) K--;
           if(K<0) {
               if(A[left]==0) K++;
               left++;
           }
           right++;
       }
       return left-right;
    }
}
