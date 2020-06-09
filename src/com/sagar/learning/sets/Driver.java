package com.sagar.learning.sets;

/**
 * Created by sagarsingh on 2020-01-25
 */
public class Driver {
    public static void main(String[] args) {
        NodeDisjointSet disjointSets = new NodeDisjointSet();
        for (int i = 1; i <= 7; i++) disjointSets.makeSet(i);
        disjointSets.union(1, 2);
        disjointSets.union(2, 3);
        disjointSets.union(4, 5);
        disjointSets.union(6, 7);
        disjointSets.union(3, 7);
        System.out.println(disjointSets.findSet(6).data);
        System.out.println(disjointSets.findSet(1).data);
        System.out.println(disjointSets.findSet(4).data);
        System.out.println(disjointSets.findSet(7).data);

    }


}
