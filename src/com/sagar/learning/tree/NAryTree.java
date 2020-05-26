package com.sagar.learning.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by sagarsingh on 2020-03-16
 */
public class NAryTree {
    private NAryNode root;
    public List<List<NAryNode>> levelOrder() {
        Queue<NAryNode> q = new LinkedList<>();
        List<List<NAryNode>> result = new ArrayList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<NAryNode> level = new ArrayList<>();
            while (size-->0) {
                NAryNode n = q.poll();
                level.add(n);
                for(Object c:n.children) {
                    q.add((NAryNode)c);
                }
            }
            result.add(level);
        }
        return result;
    }
}
