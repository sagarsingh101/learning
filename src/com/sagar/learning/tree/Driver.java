package com.sagar.learning.tree;


import com.sagar.learning.tree.binarytree.*;

import java.util.*;

import java.util.stream.IntStream;

/**
 * Created by sagarsingh on 2020-01-21
 */
public class Driver {
    private static Random random=new Random();
    public static void main(String[] args) {
        AVLBST<Integer> avlTree = new AVLBST<>();
        insert(avlTree,100);
        deleteRandomNodes(avlTree,100);
        levelOrderTest(avlTree);
   }

    private static void deleteRandomNodes(BinaryTree<Integer> binaryTree, int maxValue) {
        for(int i=0;i<maxValue;i++) {
            if(i%10==0) {
                binaryTree.delete(random.nextInt(maxValue));
            }
        }
    }

    public void BSTTest() {
       SimpleBST<Integer> bst = new SimpleBST<>();
       for(int i=1;i<=1000;i++) {
           bst.insert(random.nextInt(1000));
       }
       List<Integer> inorder = bst.inorder();
       System.out.println("Number of nodes : " + inorder.size());
       System.out.println(inorder);
   }

   public void simpleBinaryTreeTest() {
       BinaryTree<Integer> tree = new SimpleBinaryTree();
       insert(tree,32);
       BinaryTreeNode<Integer> n1 = tree.findNode(7);
       BinaryTreeNode<Integer> n2 = tree.findNode(18);
       System.out.println(tree.LCA(n1,n2));

   }

    private static void insert(BinaryTree<Integer> tree, int numberOfNodes) {
        IntStream.range(1,numberOfNodes+1).forEach(x->tree.insert(x));
    }


    private static void levelOrderTest(BinaryTree tree) {
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
    public static void printObjects(List<Object> list) {
        if(list==null || list.size()==0) {
            System.out.println("Empty List Passed");
            return;
        }
        StringJoiner stringJoiner = new StringJoiner(",");
        for(Object node:list) {
            stringJoiner.add(node.toString());
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
