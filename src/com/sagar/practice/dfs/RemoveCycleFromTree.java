package com.sagar.practice.dfs;

import com.sagar.learning.sets.DisjointSetIndexBased;
import com.sagar.practice.utils.LeetCodeUtils;

import java.util.*;

/**
 *  685. Redundant Connection II https://leetcode.com/problems/redundant-connection-ii/
 * In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all
 * other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has
 * no parents.
 *
 * The given input is a directed graph that started as a rooted tree with N nodes (with distinct values 1, 2, ..., N),
 * with one additional directed edge added. The added edge has two different vertices chosen from 1 to N, and was not
 * an edge that already existed.
 *
 * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] that represents a
 * directed edge connecting nodes u and v, where u is a parent of child v.
 *
 * Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes. If there are multiple
 * answers, return the answer that occurs last in the given 2D-array.
 */
public class RemoveCycleFromTree {
    public static void main(String[] args) {
        int[][] edges = new int[][]{{2,3},{1,3},{3,4},{4,1}};
        RemoveCycleFromTree cyclicEdge = new RemoveCycleFromTree();
        System.out.println(Arrays.toString(cyclicEdge.findRedundantDirectedConnectionUsingDFS(edges)));
    }

    /**
     * Solution : There are two ways this graph is not a rooted tree with N nodes , one either one node has two parents
     * or there is cycle.
     *
     * If an incoming edge causes two parent scenario , and there was already an edge that caused a cycle then we definitely
     * have to remove the first parent as the question states that only one edge is bad , thus the previous parent will be the defective
     * edge that caused the cycle , and if even after removal of the first parent it does not convert into a tree then
     * the input is wrong , as it would require two edges to be removed;
     *
     * If an incoming causes a cycle  , and there already exists a node with dual parents then we can safely assume that
     * the one of the parents is the culprit because we know removing the incoming edge will not solve the problem as
     * there still be a node with two parents , then we remove the first parent as we have not added the second parent
     * in the map , does only first parent can cause the cycle.
     *
     *
     * @param edges
     * @return
     */
    public int[] findRedundantDirectedConnectionUsingDFS(int[][] edges) {
        Map<Integer,Integer> parentMap = new HashMap<>();
        List<int[]> twoParentCandidates = new ArrayList<>();
        twoParentCandidates.toArray(new Integer[10]);

        int[] cycle = null;
        for(int[] edge:edges) {
            if(!parentMap.containsKey(edge[0])) parentMap.put(edge[0],edge[0]);
            if(!parentMap.containsKey(edge[1]) || parentMap.get(edge[1]).equals(edge[1])) {
                parentMap.put(edge[1],edge[0]);
            }
            else {
                twoParentCandidates.add(new int[]{parentMap.get(edge[1]),edge[1]});
                twoParentCandidates.add(edge);
                if(cycle!=null) return twoParentCandidates.get(0);
            }
            if(cycle==null && isCyclic(parentMap,edge[1])) {
                cycle = edge;
                if(!twoParentCandidates.isEmpty()) return twoParentCandidates.get(0);
            }
        }
        if(cycle!=null) return cycle;
        if(!twoParentCandidates.isEmpty()) return twoParentCandidates.get(1);
        return null;
    }

    public boolean isCyclic(Map<Integer,Integer> tree, int vertex) {
        int curr = vertex;
        while (curr!=tree.get(curr)) {
              curr = tree.get(curr);
            if(curr==vertex) return true;
        }
        return false;
    }

    public int[] findRedundantDirectedConnectionUsingUnion(int[][] edges) {
        int n = edges.length;
        int[] parents = new int[n+1];
        int[] roots = new int[n+1];
        Arrays.fill(parents, -1);
        int first = -1;
        int second = -1;
        int last = -1;
        for (int i=0; i<n; i++) {
            int p = edges[i][0];
            int c = edges[i][1];
            if (parents[c] != -1) {
                first = parents[c];
                second = i;
                continue;
            }
            parents[c] = i;
            int r = findRoot(roots, p);
            if (r == c) {
                last = i;
            } else {
                roots[c] = r;
            }
        }
        if (last == -1) {
            return edges[second];
        } else if (first == -1) {
            return edges[last];
        } else {
            return edges[first];
        }
    }

    private int findRoot(int[] roots, int x) {
        if (roots[x] == 0) {
            return x;
        }
        roots[x] = findRoot(roots, roots[x]);
        return roots[x];
    }
}
