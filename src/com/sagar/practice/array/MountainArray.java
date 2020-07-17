package com.sagar.practice.array;

/**
 * Created by sagarsingh on 2020-07-12
 */
public class MountainArray {
    public static void main(String[] args) {
        MountainArray mountainArray = new MountainArray();
        System.out.println(mountainArray.peakIndexInMountainArray(new int[]{1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 39, 37, 35, 33, 31, 29, 27, 25, 23, 21, 19, 17, 15, 13, 11, 9, 7, 5, 3}));
    }

    public int peakIndexInMountainArray(int[] A) {
        int st = 0, end = A.length - 1;
        while (st <= end) {
            int mid = (end - st) / 2 + st;
            if (mid == 0) {
                String s = new String();

                st = st + 1;
                continue;
            }
            if (A[mid] > A[mid - 1] && A[mid] > A[mid + 1]) {
                return mid;
            } else if (A[mid] < A[mid + 1]) {
                st = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }
}
