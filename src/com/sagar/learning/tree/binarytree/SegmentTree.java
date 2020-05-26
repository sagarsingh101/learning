package com.sagar.learning.tree.binarytree;

/**
 * Created by sagarsingh on 2020-05-11
 */
public class SegmentTree {
    private int[] tree;
    private int n;

    public SegmentTree(int[] arr) {
        n = arr.length;
        int x = (int) Math.ceil(Math.log(n) / Math.log(2));
        tree = new int[2 * (int) Math.pow(2, x) - 1];
        initialize(tree, arr, 0, n - 1, 0);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 5};
        SegmentTree segmentTree = new SegmentTree(arr);
        System.out.println(segmentTree.sumRange(0, 2));
        segmentTree.update(1, 2);
        System.out.println(segmentTree.sumRange(0, 2));

    }

    public void update(int index, int val) {
        update(index, val, 0, n - 1, 0);
    }

    private int update(int i, int val, int st, int end, int index) {
        if (st == end) {
            int diff = val - tree[index];
            tree[index] = val;
            return diff;
        }
        int mid = end - (end - st) / 2;
        int diff = 0;

        if (mid <= i) {
            diff = update(i, val, mid, end, 2 * index + 2);
        } else {
            diff = update(i, val, st, mid - 1, 2 * index + 1);
        }
        tree[index] += diff;
        return diff;
    }

    public int sumRange(int i, int j) {
        return findSum(0, n - 1, i, j, 0);
    }

    private int findSum(int st, int end, int i, int j, int index) {
        if (st == i && end == j) {
            return tree[index];
        }
        if (j < st || end < i) return 0;

        int mid = end - (end - st) / 2;
        if (mid >= i && mid <= j) {
            return findSum(st, mid - 1, i, mid - 1, 2 * index + 1)
                    + findSum(mid, end, mid, j, 2 * index + 2);
        }
        if (mid < i) {
            return findSum(mid, end, i, j, 2 * index + 2);
        }
        return findSum(st, mid - 1, i, j, 2 * index + 1);
    }

    private int initialize(int[] tree, int[] arr, int st, int end, int index) {
        if (st > end) return 0;

        if (st == end) {
            tree[index] = arr[st];
            return tree[index];
        }

        int mid = end - (end - st) / 2;
        tree[index] = initialize(tree, arr, st, mid - 1, 2 * index + 1)
                + initialize(tree, arr, mid, end, 2 * index + 2);
        return tree[index];
    }
}
