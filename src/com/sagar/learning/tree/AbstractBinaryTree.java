package com.sagar.learning.tree;

import javax.swing.tree.TreeNode;
import java.util.*;

/**
 * Created by sagarsingh on 2019-12-30
 */
public abstract class AbstractBinaryTree<V> implements BinaryTree<V> {
    private BinaryTreeNode<V> root;

    @Override
    public BinaryTreeNode<V> insert(BinaryTreeNode<V> treeNode) {
        if (root == null) {
            root = treeNode.clone();
        } else {
            insert(root, treeNode.clone());
        }
        return root;
    }

    private void insert(BinaryTreeNode<V> root, BinaryTreeNode<V> node) {
        Deque<BinaryTreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode curr = queue.poll();
            if (curr.left == null) {
                curr.setLeft(node);
                return;
            } else if (curr.right == null) {
                curr.setRight(node);
                return;
            } else {
                queue.add(curr.getLeft());
                queue.add(curr.getRight());
            }
        }
    }


    @Override
    public BinaryTreeNode<V> insert(V value) {
        return insert(new BinaryTreeNode<>(value));
    }

    @Override
    public boolean exists(BinaryTreeNode treeNode) {
        return false;
    }

    @Override
    public List<BinaryTreeNode> inorder() {
        if (root == null) return null;
        List<BinaryTreeNode> result = new ArrayList<>();
        Stack<BinaryTreeNode> st = new Stack<>();
        BinaryTreeNode curr = root;
        while (curr != null || !st.isEmpty()) {
            while (curr != null) {
                st.push(curr);
                curr = curr.left;
            }
            curr = st.pop();
            result.add(curr);
            curr = curr.right;
        }
        return result;
    }

    @Override
    public List<BinaryTreeNode> postOrder() {
        if (root == null) return null;
        Stack<BinaryTreeNode> childStack = new Stack<>();
        Stack<BinaryTreeNode> finalStack = new Stack<>();
        childStack.add(root);
        while (!childStack.isEmpty()) {
            BinaryTreeNode curr = childStack.pop();
            if (curr.left != null) childStack.add(curr.left);
            if (curr.right != null) childStack.add(curr.right);
            finalStack.add(curr);
        }
        return new ArrayList<>(finalStack);
    }

    @Override
    public List<BinaryTreeNode> preOrder() {
        if (root == null) return null;
        List<BinaryTreeNode> result = new ArrayList<>();
        Stack<BinaryTreeNode> st = new Stack<>();
        st.push(root);
        while (!st.isEmpty()) {
            BinaryTreeNode temp = st.pop();
            if (temp.right != null) st.push(temp.right);
            if (temp.left != null) st.push(temp.left);
            result.add(temp);
        }
        return result;
    }

    @Override
    public List<List<BinaryTreeNode>> levelOrder() {
        if (root == null) return null;
        List<List<BinaryTreeNode>> result = new ArrayList<>();
        Deque<BinaryTreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<BinaryTreeNode> treeNodes = new ArrayList<>();
            while (size-- > 0) {
                BinaryTreeNode curr = queue.poll();
                if (curr != null) {
                    if (curr.left != null) {
                        queue.add(curr.left);
                    }
                    if (curr.right != null) {
                        queue.add(curr.right);
                    }
                    treeNodes.add(curr);
                }
            }
            result.add(treeNodes);
        }
        return result;
    }

    @Override
    public void delete(V value) {

    }

    @Override
    public BinaryTreeNode<V> getRoot() {
        return root;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getDiameter() {
        return 0;
    }

    @Override
    public int calculateLongestPath() {
        return 0;
    }

    @Override
    public List<BinaryTreeNode> getLongestPath() {
        return null;
    }

    @Override
    public BinaryTreeNode<V> getLCA() {
        return null;
    }

    @Override
    public BinaryTreeNode<V> getInOrderSuccessor(BinaryTreeNode<V> node) {
        return null;
    }

    @Override
    public BinaryTreeNode<V> getPostOrderSuccessor(BinaryTreeNode<V> node) {
        return null;
    }

    @Override
    public BinaryTreeNode<V> getPreOrderSuccessor(BinaryTreeNode<V> node) {
        return null;
    }

    public boolean isValidBST(BinaryTreeNode<Integer> root) {
        return validBST(root,Integer.MAX_VALUE+1,Integer.MIN_VALUE+1);
    }

    public boolean validBST(BinaryTreeNode<Integer> root , long maxValue,long minValue) {
        if(root==null) return true;
        if(root.getValue() >= maxValue || root.getValue()<=minValue) return false;
        return validBST(root.left,root.getValue(),minValue) && validBST(root.right,maxValue,root.getValue());
    }
}
