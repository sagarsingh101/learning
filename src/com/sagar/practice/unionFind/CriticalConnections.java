package com.sagar.practice.unionFind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sagarsingh on 2020-07-10
 */
public class CriticalConnections {
    public static void main(String[] args) {
        int n = 4;
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(0, 1));
        list.add(Arrays.asList(1, 2));
        list.add(Arrays.asList(2, 0));
        list.add(Arrays.asList(1, 3));
        CriticalConnections connections = new CriticalConnections();
        System.out.println(connections.criticalConnections(n, list));

    }

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < connections.size(); i++) {
            DisjointSet disjointSet = new DisjointSet(n);
            for (int j = 0; j < connections.size(); j++) {
                if (i != j) {
                    disjointSet.union(connections.get(j).get(0), connections.get(j).get(1));
                }
            }
            if (!disjointSet.isConnected()) {
                result.add(connections.get(i));
            }
        }
        return result;
    }

    class DisjointSet {
        int[] parents;
        int[] rank;

        DisjointSet(int size) {
            parents = new int[size];
            rank = new int[size];
            Arrays.fill(parents, -1);
        }

        public void union(int x, int y) {
            int parentX = find(x);
            int parentY = find(y);
            if (parentX != parentY) {
                if (rank[parentX] >= rank[parentY]) {
                    parents[parentY] = parentX;
                    rank[parentY] = 0;
                    rank[parentX]++;
                } else {
                    parents[parentX] = parentY;
                    rank[parentX] = 0;
                    rank[parentY]++;
                }
            }
        }

        public int find(int x) {
            if (parents[x] == -1) return x;
            return find(parents[x]);
        }

        public boolean isConnected() {
            int component = 0;
            for (int parent : parents) {
                if (parent == -1) component++;
            }
            return component <= 1;
        }
    }
}
