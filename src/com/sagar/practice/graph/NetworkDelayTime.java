package com.sagar.practice.graph;

import com.sagar.practice.utils.LeetCodeUtils;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/network-delay-time/
 * Shortest path problem.
 */
public class NetworkDelayTime {
    int[] time;

    public static void main(String[] args) {
        LeetCodeUtils.convertMatrixInputToOutput("[[2,1,1],[2,3,1],[3,4,1]]");
        int[][] graph = new int[][]{{2,1,1},{2,3,1},{3,4,1}};
        NetworkDelayTime delayTime = new NetworkDelayTime();
        System.out.println(delayTime.networkDelayTime(graph,4,2));
    }
    public int networkDelayTime(int[][] times, int N, int K) {
        time = new int[N + 1];
        Arrays.fill(time, -1);
        time[K] = 0;
        Map<Integer, List<Pair<Integer, Integer>>> graph = new HashMap<>();
        for (int[] node : times) {
            graph.computeIfAbsent(node[0], x -> new ArrayList<>());
            graph.get(node[0]).add(new Pair<>(node[1], node[2]));
        }
        PriorityQueue<Pair<Integer, Integer>> q = new PriorityQueue<>((n1, n2) -> n1.getValue() - n2.getValue());
        q.add(new Pair<>(K, 0));
        while (!q.isEmpty()) {
            Pair<Integer, Integer> node = q.poll();
            if (graph.containsKey(node.getKey())) {
                for (Pair<Integer, Integer> child : graph.get(node.getKey())) {
                    time[child.getKey()] = time[child.getKey()] == -1 ? node.getValue() + child.getValue() :
                            Math.min(time[child.getKey()], child.getValue()+node.getValue());
                    q.add(new Pair(child.getKey(), child.getValue() + node.getValue()));
                }
            }
        }
        int maxTime = 0;
        for (int i=1;i<=N;i++) {
            if (time[i] == -1) return -1;
            maxTime = Math.max(maxTime,time[i]);
        }
        return maxTime;
    }
}
