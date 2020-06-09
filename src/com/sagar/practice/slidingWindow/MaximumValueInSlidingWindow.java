package com.sagar.practice.slidingWindow;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeSet;

/**
 * https://leetcode.com/problems/sliding-window-maximum/
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.
 */
public class MaximumValueInSlidingWindow {
    public static void main(String[] args) {
        MaximumValueInSlidingWindow window = new MaximumValueInSlidingWindow();
        window.maxSlidingWindowUsingDeque(new int[]{-6,-10,-7,-1,-9,9,-8,-4,10,-5,2,9,0,-7,7,4,-2,-10,8,7},7);
    }

    public int[] maxSlidingWindowUsingDeque(int[] nums, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        for(int i=0;i<k;i++) {
            removeSmallerElements(deque,i,k,nums);
            deque.addLast(i);
    }
        int[] result = new int[nums.length-k+1];
        result[0]=nums[deque.getFirst()];
        int start = k;
        while (start<nums.length) {
            removeSmallerElements(deque,start,k,nums);
            deque.addLast(start);
            result[start-k+1] = nums[deque.getFirst()];
            start++;
        }
        return result;
    }

    private void removeSmallerElements(Deque<Integer> deque,int start, int k,int[] nums) {
        if(!deque.isEmpty() && deque.getFirst() == start-k) {
            deque.removeFirst();
        }
        while (!deque.isEmpty() && nums[start]>nums[deque.getLast()]) {
            deque.removeLast();
        }
    }

    public int[] maxSlidingWindowUsingLeftAndRightBlocks(int[] nums, int k) {
        int len = nums.length;
        int[] left = new int[len];
        int[] right = new int[len];
        left[0]=nums[0];
        right[len-1]=nums[len-1];
        for(int i=1;i<nums.length;i++) {
            if(i%k==0) left[i]=nums[i];
            else left[i]=Math.max(left[i-1],nums[i]);
            int j = len-i-1;
            if((j+1%k)==0) right[j]=nums[j];
            else right[j]=Math.max(right[j+1],nums[j]);
        }
        int[] result = new int[nums.length-k+1];
        int start=0 , end = k-1;
        while (end<len) {
            result[start] = Math.max(right[start],left[end]);
            start++;
            end++;
        }
        return result;
    }

        /**
         * using TreeSet , complexity = (n-k)logk
         * @param nums
         * @param k
         * @return
         */
    public int[] maxSlidingWindowTreeSet(int[] nums, int k) {
        int[] result = new int[nums.length-k+1];
        int i=0;
        TreeSet<Node> tree = new TreeSet<Node>((n1,n2)->n1.value==n2.value?n1.index-n2.index:n2.value-n1.value);
        while(i<k) {
            tree.add(new Node(nums[i],i));
            i++;
        }
        int j=0;
        for(int p=0;p<result.length;p++) {
            result[p]=tree.first().value;
            tree.remove(new Node(nums[j],j));
            j++;
            if(i<nums.length)
                tree.add(new Node(nums[i],i));
            i++;
        }
        return result;
    }

    class Node{
        int value;
        int index;

        public Node(int v,int i) {
            value = v;
            index = i;
        }
    }
}
