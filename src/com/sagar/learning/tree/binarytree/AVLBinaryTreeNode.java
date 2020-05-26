package com.sagar.learning.tree.binarytree;

/**
 * Created by sagarsingh on 2020-05-25
 */
public class AVLBinaryTreeNode<V> extends BinaryTreeNode<V> {
    public AVLBinaryTreeNode<V> parent;
    public int ht = 1;

    public AVLBinaryTreeNode(V value) {
        super(value);
    }

    public AVLBinaryTreeNode(V value, AVLBinaryTreeNode<V> parent) {
        super(value);
        this.parent = parent;
    }

    public void calculateHt() {
        int left = this.left == null ? 0 : ((AVLBinaryTreeNode) this.left).ht;
        int right = this.right == null ? 0 : ((AVLBinaryTreeNode) this.right).ht;
        this.ht = 1 + Math.max(left, right);
    }

    public boolean isUnbalanced() {
        int diff = htDiff();
        return diff >= 2 || diff <= -2;
    }

    public int htDiff() {
        return (this.left != null ? ((AVLBinaryTreeNode) this.left).ht : 0) - (this.right != null ? ((AVLBinaryTreeNode) this.right).ht : 0);
    }
}
