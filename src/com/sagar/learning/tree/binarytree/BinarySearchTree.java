package com.sagar.learning.tree.binarytree;

/**
 * Created by sagarsingh on 2020-05-25
 */
public interface BinarySearchTree<V> {
    BinaryTreeNode<V> findInorderSuccessor(V value);
}
