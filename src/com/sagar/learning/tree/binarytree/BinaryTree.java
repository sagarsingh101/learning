package com.sagar.learning.tree.binarytree;

import java.util.List;

/**
 * Created by sagarsingh on 2019-12-30
 */
public interface BinaryTree<V> {
    void insert(V value);

    boolean exists(V value);

    List<V> inorder();

    List<V> postOrder();

    List<V> preOrder();

    List<List<BinaryTreeNode>> levelOrder();

    BinaryTreeNode<V> findNode(V value);

    BinaryTreeNode<V> findNodeUsingDFS(V value);

    void delete(V value);

    BinaryTreeNode<V> getRoot();

    int getHeight();

    /**
     * Longest Path between any two nodes.
     *
     * @return
     */
    int getDiameter();

    BinaryTreeNode<V> LCA(BinaryTreeNode<V> n1, BinaryTreeNode<V> n2);
}
