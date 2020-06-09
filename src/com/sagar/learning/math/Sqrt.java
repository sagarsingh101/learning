package com.sagar.learning.math;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class Sqrt {
    private Double TOLERANCE = 1.0;
    public static void main(String[] args) {
        Sqrt sqrt = new Sqrt();
        List<List<Long>> results = new ArrayList<>();
        List<Long> result = new ArrayList<>();
        for(int i=3;i<=7;i++) {
            double end = Math.pow(10,i);
            long startTime = System.currentTimeMillis();
            for(int j=1;j<=end;j++) {
                sqrt.binarySearch(j);
            }
            long time = System.currentTimeMillis() - startTime;
            result.add(time);
        }
        results.add(result);

        List<Long> result2 = new ArrayList<>();
        for(int i=3;i<=7;i++) {
            double end = Math.pow(10,i);
            long startTime = System.currentTimeMillis();
            for(int j=1;j<=end;j++) {
                sqrt.bitShift(j);
            }
            long time = System.currentTimeMillis() - startTime;
            result2.add(time);
        }
        results.add(result2);

        List<Long> result3 = new ArrayList<>();
        for(int i=3;i<=7;i++) {
            double end = Math.pow(10,i);
            long startTime = System.currentTimeMillis();
            for(int j=1;j<=end;j++) {
                sqrt.newtonsMethod(j);
            }
            long time = System.currentTimeMillis() - startTime;
            result3.add(time);
        }
        results.add(result3);
        System.out.println(results);
    }

    /**
     * This gives exact sqrt , with an precision dictated by the tolerance.
     * @param a
     * @return
     */
    public double newtonsMethod(double a) {
        if (a == 0) return 0;
        a = Math.abs(a);
        double x = (a + 1) / 2;
        double x0 = a;
        while (x0 - x >= TOLERANCE) {
            x0 = x;
            x = (x + a / x) / 2;
        }
        return x;
    }

    /**
     * This gives floor of the sqrt in log(n) time.
     * @param x
     * @return
     */
    public int binarySearch(int x) {
        if(x<2) return x;
        int end = x/2;
        int start = 2;
        int pivot;
        while (start<=end) {
            pivot = (end-start)/2 + start;
            long square = pivot*pivot;
            if(square==x) return pivot;
            if(square>x) end=pivot-1;
            else start = pivot+1;
        }
        return end;
    }
    /**
     * This gives floor of the sqrt in log(n) time using bit shifting for division.
     * @param x
     * @return
     */
    public int bitShift(int x) {
        if(x<2) return x;
        int left = bitShift(x>>2) << 1;
        int right = left+1;
        return (long) (right*right) > x ?    left:right;
    }
}
