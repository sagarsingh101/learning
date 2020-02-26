package com.sagar.learning.tree;


import java.util.List;

import java.util.StringJoiner;
import java.util.stream.IntStream;

/**
 * Created by sagarsingh on 2020-01-21
 */
public class Driver {
    public static void main(String[] args) {
        BinaryTree<Integer> tree = new SimpleBinaryTree();
        IntStream.range(1,32).forEach(x->tree.insert(x));
        printNestedList(tree.levelOrder());
    }

    public static void printList(List<BinaryTreeNode> list) {
        if(list==null || list.size()==0) {
            System.out.println("Empty List Passed");
            return;
        }
        StringJoiner stringJoiner = new StringJoiner(",");
        for(BinaryTreeNode node:list) {
            stringJoiner.add(node.getValue().toString());
        }
        System.out.println(stringJoiner.toString());
    }

    public static void printNestedList(List<List<BinaryTreeNode>> list) {
        if(list==null || list.size()==0) {
            System.out.println("Empty List Passed");
            return;
        }
        list.forEach(Driver::printList);
    }
}
