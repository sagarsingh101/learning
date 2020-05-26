package com.sagar.practice;

import java.util.*;

/**
 * Created by sagarsingh on 2020-03-22
 * Quesstion :
 * As the football coach at your local school, you have been tasked with picking a team of exactly P students to
 * represent your school. There are N students for you to pick from. The i-th student has a skill rating Si,
 * which is a positive integer indicating how skilled they are.
 *
 * You have decided that a team is fair if it has exactly P students on it and they all have the same skill rating.
 * That way, everyone plays as a team. Initially, it might not be possible to pick a fair team, so you will give some
 * of the students one-on-one coaching. It takes one hour of coaching to increase the skill rating of any student by 1.
 *
 * The competition season is starting very soon (in fact, the first match has already started!), so you'd like to find
 * the minimum number of hours of coaching you need to give before you are able to pick a fair team
 */
public class PickingTeam {
    public static void main(String[] args) {
        int T;
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        int t=1;
        while(t<=T) {
            int N,P;
            N=sc.nextInt();
            P = sc.nextInt();
            int [] st = new int[N];
            for(int i=0;i<N;i++) {
                st[i] = sc.nextInt();
            }
            Arrays.sort(st);
            Random r = new Random();
            r.nextInt(5);
            TreeMap m = new TreeMap();
            int minHours = Integer.MAX_VALUE;
            int hours=-1;
            for(int i=N-1;i>=P-1;i--) {
                if(hours==-1) {
                    hours = 0;
                    for (int j = 1; j < P; j++) {
                        hours += st[i - j];
                    }
                }
                minHours = Math.min(st[i]*(P-1)-hours,minHours);
                if(i-P>=0) {
                    hours = hours - st[i - 1] + st[i - P];
                }
            }
            System.out.println("Case #" + t + ": " + minHours);
            t++;
        }
    }
}
