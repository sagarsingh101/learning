package com.sagar.practice.bst;

import com.sagar.learning.tree.binarytree.BinarySearchTree;
import com.sagar.learning.tree.binarytree.BinaryTreeNode;
import com.sagar.learning.tree.binarytree.SimpleBST;

public class ClosestBinarySearchTreeValue {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new SimpleBST<>();
        bst.insert(1500000000,1400000000);
        ClosestBinarySearchTreeValue closestBinarySearchTreeValue = new ClosestBinarySearchTreeValue();
        System.out.println(closestBinarySearchTreeValue.closestValue(bst.getRoot(),-1500000000.0));
    }
    private int closest = 0;
    private double diff = Double.MAX_VALUE;
    public int closestValue(BinaryTreeNode<Integer> root,double value) {
        findClosestValue(root,value);
        return closest;
    }

    public void findClosestValue(BinaryTreeNode<Integer> root,double value) {
        if(root==null) return;
        double currDiff = Math.abs(value-(double)root.getValue());
        if(currDiff<=diff) {
            diff=currDiff;
            closest = root.getValue();
            if(diff==0) return;
        }
        if(root.getValue()>value) closestValue(root.getLeft(),value);
        else closestValue(root.getRight(),value);
    }
}
