package com.sagar.practice;

import java.util.Stack;

/**
 * Created by sagarsingh on 2020-03-13
 */
public class TrapRainWater {
    public static void main(String[] args) {
        TrapRainWater rainWater = new TrapRainWater();
        int[] ht = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(rainWater.trap(ht));
    }
    public int trap(int[] height) {
        int total = 0;
        Stack<Integer> st = new Stack<>();
        for(int i=height.length-1;i>=0;i--) {
            if(st.isEmpty() || height[i]<height[st.peek()]) {
                st.push(i);
            } else {
                int lastbar = 0;
                while(!st.isEmpty() && height[i]>=height[st.peek()]) {
                    total+=(height[st.peek()]-lastbar)*(st.peek()-i-1);
                    lastbar =height[st.pop()];
                }
                if(!st.isEmpty()) {
                    total += (height[i] - lastbar) * (st.peek() - i - 1);
                }
                st.push(i);
            }
        }
        return total;
    }
}
