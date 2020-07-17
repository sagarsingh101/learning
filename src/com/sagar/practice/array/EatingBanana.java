package com.sagar.practice.array;

/**
 * Created by sagarsingh on 2020-07-16
 */
public class EatingBanana {
    public static void main(String[] args) {
        EatingBanana banana = new EatingBanana();
        System.out.println(banana.minEatingSpeed(new int[]{3, 6, 7, 11}, 8));
    }

    public int minEatingSpeed(int[] piles, int H) {
        int maxPile = Integer.MIN_VALUE;
        int minPile = Integer.MAX_VALUE;
        for (int p : piles) {
            maxPile = Math.max(maxPile, p);
            minPile = Math.min(minPile, p);
        }
        return search(piles, H, minPile, maxPile);
    }

    public int search(int[] piles, int H, int st, int end) {
        while (st <= end) {
            int mid = (end - st) / 2 + st;
            if (isPossible(piles, H, mid)) {
                end = mid - 1;
            } else st = mid + 1;
        }
        return st;
    }

    private boolean isPossible(int[] piles, int H, int K) {
        for (int p : piles) {
            int q = p / K;
            int rem = p % K == 0 ? 0 : 1;
            q += rem;
            H = H - q;
            if (H < 0) {
                return false;
            }
        }
        return true;
    }
}
