package com.sagar.learning.tree;

import java.util.List;

/**
 * Created by sagarsingh on 2019-12-30
 */
public interface BinaryTree<V> {
    BinaryTreeNode<V> insert(BinaryTreeNode<V> treeNode);
    BinaryTreeNode<V> insert(V value);
    boolean exists(BinaryTreeNode<V> treeNode);
    List<BinaryTreeNode> inorder();
    List<BinaryTreeNode> postOrder();
    List<BinaryTreeNode> preOrder();
    List<List<BinaryTreeNode>> levelOrder();
    void delete(V value);
    BinaryTreeNode<V> getRoot();
    int getHeight();
    int getDiameter();
    int calculateLongestPath();
    List<BinaryTreeNode> getLongestPath();
    BinaryTreeNode<V> getLCA();
    BinaryTreeNode<V> getInOrderSuccessor(BinaryTreeNode<V> node);
    BinaryTreeNode<V> getPostOrderSuccessor(BinaryTreeNode<V> node);
    BinaryTreeNode<V> getPreOrderSuccessor(BinaryTreeNode<V> node);
}
