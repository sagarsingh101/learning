package com.sagar.practice.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by sagarsingh on 2020-06-15
 */
public class CheapestFlightWithKStops {
    public static void main(String[] args) {
        int[][] flights = new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        CheapestFlightWithKStops flightWithKStops = new CheapestFlightWithKStops();
        flightWithKStops.findCheapestPrice(3, flights, 0, 2, 1);
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[] distance = new int[n];
        Arrays.fill(distance, -1);
        ArrayList<Edge>[] graph = new ArrayList[n];
        for (int i = 0; i < graph.length; i++) graph[i] = new ArrayList<>();
        for (int[] flight : flights) {
            graph[flight[0]].add(new Edge(flight[2], flight[1]));
        }
        distance[src] = 0;
        boolean[] visited = new boolean[n];
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>(Comparator.comparingInt((e1) -> e1.wt));
        pq.add(new Edge(0, src));
        int steps = 0, cost = -1;
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            visited[e.node] = true;
            for (Edge c : graph[e.node]) {
                if (distance[c.node] == -1) {
                    distance[c.node] = e.wt + c.wt;
                } else {
                    distance[c.node] = Math.min(e.wt + c.wt, distance[c.node]);
                }
                if (c.node == dst) {
                    if (steps <= K) {
                        cost = cost == -1 ? distance[dst] : Math.min(distance[dst], cost);
                    }
                }
                if (!visited[c.node])
                    pq.add(c);
            }
            steps++;
        }
        return cost;

    }

    class Edge {
        int wt;
        int node;

        public Edge(int w, int n) {
            wt = w;
            node = n;
        }
    }
}
