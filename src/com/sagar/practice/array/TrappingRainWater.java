package com.sagar.practice.array;

import java.util.Stack;

/**
 * https://leetcode.com/problems/trapping-rain-water/
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 */
public class TrappingRainWater {
    public static void main(String[] args) {
        TrappingRainWater rainWater = new TrappingRainWater();
        rainWater.maxAreaDp(new int[]{0,1,0,2,1,0,1,3,2,1,2,1});
    }
    public int maxAreaDp(int[] heights) {
        int len = heights.length;
        int[] leftMax = new int[len];
        int[] rightMax = new int[len];
        leftMax[0]=heights[0];
        for(int i=1;i<len;i++) {
            leftMax[i] = Math.max(leftMax[i-1],heights[i]);
        }
        rightMax[len-1]=heights[len-1];
        for (int j=len-2;j>=0;j--) {
            rightMax[j] = Math.max(rightMax[j+1],heights[j]);
        }
        int area = 0;
        for(int i=0;i<len;i++) {
            area+=Math.min(leftMax[i],rightMax[i]) - heights[i];
        }
        return area;
    }
    public int maxAreaUsingStack(int[] heights) {
        Stack<Integer> st = new Stack<>();
        int len = heights.length;
        int i=0,area=0;
        while(i<len) {
            while(!st.isEmpty() && heights[i] > heights[st.peek()]) {
               int top = st.pop();
               if(st.isEmpty()) break;
                int distance = i - st.peek() - 1;
                int bounded_height = Math.min(heights[i],heights[st.peek()])-heights[top];
                area+=distance*bounded_height;
            }
            st.push(i++);
        }
        return area;
    }
}
