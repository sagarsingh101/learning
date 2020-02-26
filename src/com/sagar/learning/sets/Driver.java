package com.sagar.learning.sets;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sagarsingh on 2020-01-25
 */
public class Driver {
    public static void main(String[] args) {
        DisjoinSets disjoinSets = new DisjoinSets();
        for (int i = 1; i <= 7; i++) disjoinSets.makeSet(i);
        disjoinSets.union(1, 2);
        disjoinSets.union(2, 3);
        disjoinSets.union(4, 5);
        disjoinSets.union(6, 7);
        disjoinSets.union(3, 7);
        System.out.println(disjoinSets.findSet(6).data);
        System.out.println(disjoinSets.findSet(1).data);
        System.out.println(disjoinSets.findSet(4).data);
        System.out.println(disjoinSets.findSet(7).data);
    }
}
