package com.sagar.practice.graph;

import com.sagar.practice.utils.LeetCodeUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class EventualSafeStates {
    public static void main(String[] args) {
        LeetCodeUtils.convertMatrixInputToOutput("[[1,2],[2,3],[5],[0],[5],[],[]]");
        int[][] graph = new int[][]{{1,2},{2,3},{5},{0},{5},{},{}};
        EventualSafeStates safeStates = new EventualSafeStates();
        System.out.println(safeStates.eventualSafeNodes(graph));
    }
    Map<Integer, List<Integer>> nodes;
    Map<Integer,Boolean> nodeStates;
    public List<Integer> eventualSafeNodes(int[][] graph) {
        nodes = new HashMap<>();
        nodeStates = new HashMap<>();
        int i=0;
        for(int[] edge:graph) {
            nodes.computeIfAbsent(i,x->new ArrayList<>());
            for(int c:edge) {
                nodes.get(i).add(c);
            }
            i++;
        }
        checkForSafeNodes();
        List<Integer> result =  nodeStates.entrySet().stream()
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey).collect(Collectors.toList());
         result.sort(Comparator.naturalOrder());
         return result;
    }

    private void checkForSafeNodes() {
        for(Map.Entry<Integer,List<Integer>> node:nodes.entrySet()) {
            if(!nodeStates.containsKey(node.getKey())) {
                dfs(node.getKey(),new HashSet<>());
            }
        }
    }

    private boolean dfs(int node, Set<Integer> visited) {
        visited.add(node);
        boolean safe = true;
        for(Integer child:nodes.get(node)) {
            if(visited.contains(child)) {
                safe = false;
                break;
            } else {
                safe = safe && dfs(child,visited);
            }
        }
        nodeStates.put(node,safe);
        return safe;
    }
}
