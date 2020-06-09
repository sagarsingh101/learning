package com.sagar.practice.array;

import com.sagar.practice.utils.ArrayUtils;
import com.sagar.practice.utils.LeetCodeUtils;

import java.util.Arrays;

public class MergeIntervals {
    public static void main(String[] args) {
        MergeIntervals merger = new MergeIntervals();
        ArrayUtils.printMatrix(merger.merge(new int[][]{{1,3},{2,6},{8,10},{15,18}}));
    }
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals,(n1, n2)->n1[0]-n2[0]);
        int k=0,i=0;
        int[][] result = new int[intervals.length][2];
        while(i<intervals.length) {
            int[] interval = intervals[i];
            i++;
            while(i<intervals.length && interval[1]>=intervals[i][0]) {
                interval[1] = intervals[i][1];
                i++;
            }
            result[k++] = interval;
        }
        int[][] ans = new int[k][2];
        System.arraycopy(result,0,ans,0,k);
        return ans;
    }
}
