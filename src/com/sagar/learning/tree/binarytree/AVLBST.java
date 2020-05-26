package com.sagar.learning.tree.binarytree;

import java.util.Comparator;

/**
 * Created by sagarsingh on 2020-05-25
 */
public class AVLBST<V> extends SimpleBST<V> {

    public AVLBST(Comparator<V> comparator) {
        super(comparator);
    }

    public AVLBST() {
    }

    @Override
    public void insert(V value) {
        if (this.getRoot() == null) {
            this.setRoot(new AVLBinaryTreeNode<>(value));
            return;
        }
        Comparable<? super V> comparable = comparator == null ? (Comparable<? super V>) value : null;
        AVLBinaryTreeNode<V> node = (AVLBinaryTreeNode<V>) this.getRoot();
        while (node != null) {
            int cmp = comparable.compareTo(node.value);
            if (cmp > 0) {
                if (node.right == null) {
                    node.right = new AVLBinaryTreeNode<>(value, node);
                    break;
                }
                node = (AVLBinaryTreeNode) node.right;
            } else if (cmp < 0) {
                if (node.left == null) {
                    node.left = new AVLBinaryTreeNode<>(value, node);
                    break;
                }
                node = (AVLBinaryTreeNode) node.left;
            } else {
                return;
            }
        }
        while (node != null) {
            node.calculateHt();
            if (node.isUnbalanced()) {
                balanceNode(node);
            }
            node = node.parent;
        }
    }

    private void balanceNode(AVLBinaryTreeNode<V> node) {
        if (node.htDiff() == 2) {
            AVLBinaryTreeNode<V> left = (AVLBinaryTreeNode) node.left;
            if (left.htDiff() >= 0) {
                leftRotate(node);
            } else {
                rightRotate(left);
                leftRotate(node);
            }
        } else {
            AVLBinaryTreeNode<V> right = (AVLBinaryTreeNode) node.right;
            if (right.htDiff() <= 0) {
                rightRotate(node);
            } else {
                leftRotate(right);
                rightRotate(node);
            }
        }
    }

    private void rightRotate(AVLBinaryTreeNode<V> node) {
        AVLBinaryTreeNode right = (AVLBinaryTreeNode) node.right;
        node.right = right.left;
        if (node.right != null) {
            ((AVLBinaryTreeNode) node.right).parent = node;
        }
        right.left = node;
        if (node.parent != null) {
            if (node.parent.left == node) {
                node.parent.left = right;
            } else {
                node.parent.right = right;
            }
        } else {
            this.setRoot(right);
        }
        right.parent = node.parent;
        node.parent = right;
        node.calculateHt();
    }

    private void leftRotate(AVLBinaryTreeNode<V> node) {
        AVLBinaryTreeNode left = (AVLBinaryTreeNode) node.left;
        node.left = left.right;
        if (node.left != null) {
            ((AVLBinaryTreeNode) node.left).parent = node;
        }
        left.right = node;
        if (node.parent != null) {
            if (node.parent.left == node) {
                node.parent.left = left;
            } else {
                node.parent.right = left;
            }
        } else {
            this.setRoot(left);
        }
        left.parent = node.parent;
        node.parent = left;
        node.calculateHt();
    }

    @Override
    public void delete(V value) {
        AVLBinaryTreeNode node = (AVLBinaryTreeNode) findNode(value);
        if (node == null) return;
        if (node.left != null & node.right != null) {
            AVLBinaryTreeNode successor = (AVLBinaryTreeNode) getInorderSuccessor(node);
            node.value = successor.value;
            deleteNode(successor);
        }
        deleteNode(node);
        return;
    }

    private void deleteNode(AVLBinaryTreeNode node) {
        if (node.parent == null) {
            if (node.left != null) {
                ((AVLBinaryTreeNode) node.left).parent = null;
                this.setRoot(node.left);
            } else if (node.right != null) {
                ((AVLBinaryTreeNode) node.right).parent = null;
                this.setRoot(node.right);
            } else {
                this.setRoot(null);
            }
            return;
        }
        if (node.parent.right == node) {
            if (node.left != null) {
                node.parent.right = node.left;
                ((AVLBinaryTreeNode) node.left).parent = node.parent;
            } else {
                node.parent.left = node.right;
                if (node.right != null)
                    ((AVLBinaryTreeNode) node.right).parent = node.parent;
            }
        }
        if (node.parent.left == node) {
            if (node.left != null) {
                node.parent.left = node.left;
                ((AVLBinaryTreeNode) node.left).parent = node.parent;
            } else {
                node.parent.left = node.right;
                if (node.right != null)
                    ((AVLBinaryTreeNode) node.right).parent = node.parent;
            }
        }
        node = node.parent;
        while (node != null) {
            node.calculateHt();
            if (node.isUnbalanced()) {
                balanceNode(node);
            }
            node = node.parent;
        }
    }
}
