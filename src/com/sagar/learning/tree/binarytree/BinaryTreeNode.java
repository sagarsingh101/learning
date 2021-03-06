package com.sagar.learning.tree.binarytree;

/**
 * Created by sagarsingh on 2019-12-27
 */
public class BinaryTreeNode<V> {
    public V value;
    public BinaryTreeNode<V> left;
    public BinaryTreeNode<V> right;

    public BinaryTreeNode(V value) {
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public BinaryTreeNode<V> getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode<V> left) {
        this.left = left;
    }

    public BinaryTreeNode<V> getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode<V> right) {
        this.right = right;
    }

    @Override
    protected BinaryTreeNode<V> clone() {
        BinaryTreeNode<V> newNode = new BinaryTreeNode<>(this.value);
        if (this.left != null) {
            newNode.setLeft(this.left.clone());
        }
        if (this.right != null) {
            newNode.setRight(this.right.clone());
        }
        return newNode;
    }

    @Override
    public String toString() {
        return "BinaryTreeNode{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
