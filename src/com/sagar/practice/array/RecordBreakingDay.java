package com.sagar.practice.array;

import java.util.Scanner;

/**
 * Created by sagarsingh on 2020-07-12
 */
public class RecordBreakingDay {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int caseNo = 1;
        while (caseNo <= T) {
            int N = sc.nextInt();
            int[] days = new int[N];
            for (int i = 0; i < N; i++) {
                days[i] = sc.nextInt();
            }
            int count = 0;
            int record = 0;
            for (int i = 0; i < N; i++) {
                if (days[i] > record) {
                    record = days[i];
                    if (i == days.length - 1 || days[i] > days[i + 1]) {
                        count++;
                    }
                }
            }
            System.out.println("Case #" + caseNo + ": " + count);
            caseNo++;
        }
    }
}
