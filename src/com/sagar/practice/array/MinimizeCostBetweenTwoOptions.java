package com.sagar.practice.array;

import java.util.Arrays;
import java.util.Comparator;

public class MinimizeCostBetweenTwoOptions {
    public int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, (o1,o2)->o1[0]-o2[1]);
        int totalCost = 0;
        for(int k=0;k<costs.length;k++) {
            if(k<costs.length/2) {
                totalCost+=costs[k][0];
            } else {
                totalCost+=costs[k][1];
            }
        }
        return totalCost;
    }
}
