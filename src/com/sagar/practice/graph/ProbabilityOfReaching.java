package com.sagar.practice.graph;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by sagarsingh on 2020-07-12
 */
public class ProbabilityOfReaching {
    public static void main(String[] args) {
        int[][] edges = new int[][]{{0, 1}, {1, 2}, {0, 2}};
        double[] prb = new double[]{0.5, 0.5, 0.2};
        ProbabilityOfReaching reaching = new ProbabilityOfReaching();
        reaching.maxProbability(3, edges, prb, 0, 2);
    }

    Map<Integer, List<Pair<Integer, Double>>> graph;
    double[] probability;

    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        probability = new double[n];
        Arrays.fill(probability, -1);
        probability[start] = 1;
        graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            graph.computeIfAbsent(edge[0], x -> new ArrayList<>());
            graph.computeIfAbsent(edge[1], x -> new ArrayList<>());
            graph.get(edge[0]).add(new Pair<>(edge[1], succProb[i]));
            graph.get(edge[1]).add(new Pair<>(edge[0], succProb[i]));
        }
        traverse(start, end);
        if (probability[end] == -1) return 0;
        return probability[end];
    }

    private void traverse(int source, int dest) {
        if (source == dest) return;
        boolean[] seen = new boolean[probability.length];
        PriorityQueue<Pair<Integer, Double>> pq = new PriorityQueue<>((p1, p2) -> Double.compare(p2.getValue(), p1.getValue()));
        pq.add(new Pair<>(source, 1.0));
        while (!pq.isEmpty()) {
            Pair<Integer, Double> node = pq.poll();
            seen[node.getKey()] = true;
            if (node.getKey() == dest) return;
            for (Pair<Integer, Double> child : graph.getOrDefault(node.getKey(), new ArrayList<>())) {
                if (!seen[child.getKey()]) {
                    probability[child.getKey()] = Math.max(node.getValue() * child.getValue(), probability[child.getKey()]);
                    seen[child.getKey()] = true;
                    Pair<Integer, Double> childPair = new Pair<>(child.getKey(), probability[child.getKey()]);
                    pq.add(childPair);
                }
            }
        }
    }
}
