package com.sagar.practice;

import javafx.scene.control.Cell;

import java.util.*;

/**
 * Created by sagarsingh on 2020-03-22
 */
public class KthSmallestInMatrix {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,  3,  5},
                {6, 7, 12},
                {11, 14, 14}};
        KthSmallestInMatrix k = new KthSmallestInMatrix();
        System.out.println(k.kthSmallest(matrix,7));
    }
    Set<Integer> seen;
    public int kthSmallest(int[][] matrix, int k) {
        int R = matrix.length;
        TreeSet<Cell> set = new TreeSet<>((o1, o2)->{
            if(o1.value==o2.value) {
                return 1;
            } else {
                return o1.value-o2.value;
            }
        });
        set.add(new Cell(matrix[0][0],0,0));
        seen = new HashSet<>();
        while(--k>0) {
            Cell cell = set.pollFirst();
                int r = cell.r;
                int c = cell.c;
                seen.add(R*r+c);
                if (isValid(matrix, r + 1, c)) {
                    Cell down = new Cell(matrix[r + 1][c], r + 1, c);
                    if(!seen.contains((R*(r+1)+c))) {
                        set.add(new Cell(matrix[r + 1][c], r + 1, c));
                        seen.add(R*(r+1)+c);
                    }
                }
                if (isValid(matrix, r, c + 1)) {
                    Cell right = new Cell(matrix[r][c + 1], r, c + 1);
                    if(!seen.contains(R*r+c+1)) {
                        set.add(new Cell(matrix[r][c+1], r , c+1));
                        seen.add(R*r+c+1);
                    }
                }
        }
        return set.first().value;
    }

    private boolean isValid(int[][] matrix,int r, int c){
        return r>=0 && r<matrix.length && c>=0 && c<matrix[r].length && !seen.contains(matrix.length*r+c);
    }

    private class Cell {
        int r;
        int c;
        int value;

        public Cell(int value,int r, int c) {
            this.r = r;
            this.c = c;
            this.value = value;
        }
    }
}
