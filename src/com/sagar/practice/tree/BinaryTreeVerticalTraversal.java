package com.sagar.practice.tree;

import com.sagar.learning.tree.binarytree.BinaryTreeNode;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class BinaryTreeVerticalTraversal {
    
    public List<List<Integer>> verticalTraversal(BinaryTreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root==null) return result;
        Queue<Triplet<BinaryTreeNode,Integer,Integer>> queue = new LinkedList<>();
        queue.add(new Triplet(root,0,0));
        List<Triplet<Integer,Integer,Integer>> list = new ArrayList<>();
        while(!queue.isEmpty()) {
            Triplet<BinaryTreeNode,Integer,Integer> curr = queue.poll();
            BinaryTreeNode n = curr.first;
            list.add(new Triplet(n.getValue(),curr.second,curr.third));
            if(n.left!=null) queue.add(new Triplet<>(n.left,curr.second+1,curr.third-1));
            if(n.right!=null) queue.add(new Triplet<>(n.right,curr.second+1,curr.third+1));
        }
        Collections.sort(list,(t1,t2)->{
            if(!t1.second.equals(t2.second)) return t1.second-t2.second;
            if(!t1.third.equals(t2.third)) return t1.third-t2.third;
            return t1.first-t2.first;
        });
        List<Integer> columnList = new ArrayList<>();
        int column = list.get(0).second;
        for(Triplet<Integer,Integer,Integer> triplet:list) {
            if(triplet.second==column) {
                columnList.add(triplet.first);
            } else {
                result.add(columnList);
                column = triplet.second;
                columnList = new ArrayList<>();
                columnList.add(triplet.first);
            }
        }
        result.add(columnList);
        return result;
    }

    class Triplet<F,S,T> {
        F first;
        S second;
        T third;
        Triplet(F f,S s, T t) {
            first = f;
            second = s;
            third = t;
        }
    }
}
