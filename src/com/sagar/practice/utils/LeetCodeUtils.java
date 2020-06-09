package com.sagar.practice.utils;

import java.util.StringJoiner;

public class LeetCodeUtils {
    public static void convertMatrixInputToOutput(String input) {
       input = input.replace("[","{");
       input = input.replace("]","}");
       System.out.println(input);
    }
}
