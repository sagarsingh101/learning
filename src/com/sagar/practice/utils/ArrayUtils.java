package com.sagar.practice.utils;

import java.util.Arrays;

public class ArrayUtils {
    public static void printMatrix(Object[][] matrix) {
        for(Object[] arr : matrix) {
            System.out.println(Arrays.toString(arr));
        }
    }
    public static void printMatrix(int[][] matrix) {
        for(int[] arr : matrix) {
            System.out.println(Arrays.toString(arr));
        }
    }
}
