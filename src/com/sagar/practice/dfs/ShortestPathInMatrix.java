package com.sagar.practice.dfs;

import com.sagar.practice.utils.ArrayUtils;
import com.sagar.practice.utils.LeetCodeUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/01-matrix/
 * Not working
 */
public class ShortestPathInMatrix {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{0,0,0},{0,1,0},{1,1,1}};
        ShortestPathInMatrix shortestPath = new ShortestPathInMatrix();
        ArrayUtils.printMatrix(shortestPath.updateMatrix(matrix));
    }
    int[][] directions = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    public int[][] updateMatrix(int[][] matrix) {
        for(int i=0;i<matrix.length;i++) {
            for(int j=0;j<matrix.length;j++) {
                if(matrix[i][j]==1) {
                    findPath(matrix,i,j,new HashSet<Integer>());
                }
            }
        }
        for(int i=0;i<matrix.length;i++) {
            for(int j=0;j<matrix.length;j++) {
                if(matrix[i][j]<0) {
                    matrix[i][j] = -matrix[i][j];
                }
            }
        }
        return matrix;
    }

    public int findPath(int[][] matrix,int i, int j , Set<Integer> visited) {
        if(matrix[i][j]==0) return 0;
        if(matrix[i][j]<0) {
            return -matrix[i][j] + 1;
        }
        visited.add(i*matrix.length+j);
        int path = Integer.MAX_VALUE;
        for(int[] dir:directions) {
            int r = i+dir[0];
            int c = j+dir[1];
            if(isValid(matrix,r,c,visited)) {
                int currPath  = 1+findPath(matrix,r,c,visited);
                path = Math.min(path , currPath);
            }
        }
        matrix[i][j] = -path;
        return path;
    }

    private boolean isValid(int[][] matrix,int i ,int j , Set<Integer> visited) {
        return i>=0 && i<matrix.length && j>=0 && j<matrix[i].length && !visited.contains(matrix.length*i+j);
    }
}
