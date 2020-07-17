package com.sagar.practice.strings;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by sagarsingh on 2020-07-12
 */
public class FindCommonCharacters {
    public static void main(String[] args) {
        FindCommonCharacters commonCharacters = new FindCommonCharacters();
        System.out.println(commonCharacters.commonChars(new String[]{"bella", "lable", "roller"}));
    }

    public List<String> commonChars(String[] A) {
        int[] finalArray = commonChars(A, 0, A.length - 1);
        List<String> result = new ArrayList<>();
        for (int i = 0; i < finalArray.length; i++) {
            while (finalArray[i] > 0) {
                result.add(String.valueOf((char) (i + 'a')));
                finalArray[i]--;
                TreeSet<Integer> set = new TreeSet<>();
            }
        }
        return result;
    }

    public int[] commonChars(String[] A, int st, int end) {
        if (st == end) {
            return indexArray(A[st]);
        }
        if (st < end) {
            int mid = (end - st) / 2 + st;
            int[] left = commonChars(A, st, mid);
            int[] right = commonChars(A, mid + 1, end);
            return merge(left, right);
        }
        return new int[]{};
    }

    private int[] merge(int[] left, int[] right) {
        if (right.length == 0) return left;
        if (left.length == 0) return right;
        for (int i = 0; i < left.length; i++) {
            left[i] = Math.min(left[i], right[i]);
        }
        return left;
    }

    private int[] indexArray(String s) {
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
        }
        return arr;
    }
}
