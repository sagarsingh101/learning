package com.sagar.practice;

/**
 * Created by sagarsingh on 2020-03-15
 */

/**
 * Not working
 */
public class MaximalRectangle {
    public static void main(String[] args) {
        char[][] input = new char[][]{{'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}};
        char[][] input2 = new char[][]{{'0','1'},{'0','1'}};
        MaximalRectangle rectangle = new MaximalRectangle();
        System.out.println(rectangle.maximalRectangle(input2));
    }
    private int[][][] dp;
    int maxArea = 0;
    public int maximalRectangle(char[][] matrix) {
        dp = new int[matrix.length][matrix[0].length][2];
        for(int i=1;i<matrix.length;i++) {
            if(matrix[i][0]=='1') {
                dp[i][0][1] = dp[i-1][0][1] + 1;
                dp[i][0][0] = 1;
            }
        }
        for(int j=1;j<matrix.length;j++) {
            if(matrix[0][j]=='1') {
                dp[0][j][0] = dp[0][j-1][0]+1;
                dp[0][j][1] = 1;
            }
        }
        for(int i=1;i<matrix.length;i++) {
            for(int j=1;j<matrix[i].length;j++) {
                if(matrix[i][j]=='1') {
                    int area = 0;
                    if(matrix[i-1][j-1]=='0') {
                        dp[i][j][0] = dp[i][j-1][0]+1;
                        dp[i][j][1] = dp[i-1][j][1]+1;
                        area = Math.max(dp[i][j][0],dp[i][j][1]);
                    } else {
                        dp[i][j][0] = 1+Math.min(dp[i-1][j-1][0],dp[i][j-1][0]);
                        dp[i][j][1] = 1+Math.min(dp[i-1][j-1][1],dp[i-1][j][1]);
                        area = dp[i][j][0]*dp[i][j][1];
                    }
                    maxArea = Math.max(area,maxArea);
                }
            }
        }
        return maxArea;
    }
}
