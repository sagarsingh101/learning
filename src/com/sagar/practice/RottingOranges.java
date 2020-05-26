package com.sagar.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sagarsingh on 2020-03-16
 */
public class RottingOranges {
    int[] r = new int[]{1,-1,0,0};
    int[] c = new int[]{0,0,1,-1};
    public int orangesRotting(int[][] grid) {
        if(grid.length==0) return 0;
        List<Point> points = new ArrayList<>();
        while(true) {
            int steps = 0;
            for(int i=0;i<grid.length;i++) {
                for(int j=0;j<grid.length;j++) {
                    if(grid[i][j]==2) {
                        for(int k=0;k<r.length;k++) {
                            if(isValid(r[k]+i,c[k]+j,grid)) {
                                points.add(new Point(r[k]+i,c[k]+j));
                            }
                        }
                    }
                }
            }
            if(points.isEmpty()) {
                if(stateCheck(grid)) {
                    return steps;
                } else {
                    return -1;
                }
            }
            for(Point p:points) {
                grid[p.r][p.c]=2;
            }
            points.clear();
        }
    }

    private boolean isValid(int r,int c, int[][] grid) {
        return r>=0 && r<grid.length && c>=0 && c<grid[r].length && grid[r][c]==1;
    }

    private boolean stateCheck(int[][] grid) {
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid.length;j++) {
                if(grid[i][j]==1) {
                    return false;
                }
            }
        }
        return true;
    }

    class Point {
        int r;
        int c;
        Point(int r,int c) {
            this.r = r;
            this.c = c;
        }
    }
}
