package com.sagar.practice.dfs;


import javafx.util.Pair;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SurroundedRegions {
    public static void main(String[] args) {
        char[][] matrix = new char[][]{{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        SurroundedRegions regions = new SurroundedRegions();
        regions.solve(matrix);
        for(char[] row:matrix)
        System.out.println(Arrays.toString(row));
    }
        int[] x = new int[]{1,-1,0,0};
        int[] y = new int[]{0,0,-1,1};
        public void solve(char[][] board) {
            if(board.length==0) return;
            int ROW = board.length;
            int COL = board[0].length;
            List<Pair<Integer,Integer>> borderPairs = new LinkedList<>();
            for(int i=0;i<ROW;i++) {
                borderPairs.add(new Pair<>(i,0));
                borderPairs.add(new Pair<>(i,COL-1));
            }
            for(int j=1;j<COL;j++) {
                borderPairs.add(new Pair<>(0,j));
                borderPairs.add(new Pair<>(ROW-1,j));
            }
            for(Pair<Integer,Integer> pair:borderPairs) {
                markRegion(board,pair.getKey(),pair.getValue());
            }
            for(int i=0;i<ROW;i++) {
                for(int j=0;j<COL;j++) {
                    if(board[i][j]=='E') board[i][j]='O';
                    else if(board[i][j]=='O') board[i][j]='X';
                }
            }
        }

        public void markRegion(char[][] board ,int r,int c) {
            if(!isValid(board,r,c)) return;
            if(board[r][c]=='X') return;
            board[r][c]='E';
            for(int i=0;i<x.length;i++) {
                this.markRegion(board,r+x[i],c+y[i]);
            }
        }
        public boolean isValid(char[][] board ,int r,int c) {
            return r>=0 && r<board.length && c>=0 && c<board[r].length;
        }
}
