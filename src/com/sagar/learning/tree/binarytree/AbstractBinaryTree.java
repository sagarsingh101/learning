package com.sagar.learning.tree.binarytree;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sagarsingh on 2019-12-30
 */
public abstract class AbstractBinaryTree<V> implements BinaryTree<V> {
    private BinaryTreeNode<V> root;
    private Integer diameter;

    @Override
    public void insert(V value) {
        insert(new BinaryTreeNode<>(value));
    }


    private void insert(BinaryTreeNode<V> node) {
        if (root == null) {
            root = node;
            return;
        }
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
    public boolean exists(V value) {
        BinaryTreeNode<V> node = findNode(value);
        return node != null;
    }

    @Override
    public List<V> inorder() {
        if (root == null) return null;
        List<V> result = new ArrayList<>();
        Stack<BinaryTreeNode> st = new Stack<>();
        BinaryTreeNode curr = root;
        while (curr != null || !st.isEmpty()) {
            while (curr != null) {
                st.push(curr);
                curr = curr.left;
            }
            curr = st.pop();
            result.add((V) curr.getValue());
            curr = curr.right;
        }
        return result;
    }

    @Override
    public List<V> postOrder() {
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
        return finalStack.stream()
                .map(node -> (V) node.getValue())
                .collect(Collectors.toList());
    }

    @Override
    public List<V> preOrder() {
        if (root == null) return null;
        List<V> result = new ArrayList<>();
        Stack<BinaryTreeNode> st = new Stack<>();
        st.push(root);
        while (!st.isEmpty()) {
            BinaryTreeNode temp = st.pop();
            if (temp.right != null) st.push(temp.right);
            if (temp.left != null) st.push(temp.left);
            result.add((V) temp.value);
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
        delete(root, value);
    }

    protected void delete(BinaryTreeNode<V> root, V value) {
        if (root == null) return;
        LinkedList<BinaryTreeNode> queue = new LinkedList<>();
        BinaryTreeNode<V> parent = null;
        BinaryTreeNode<V> node = null;
        queue.add(root);
        boolean leftChild = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                BinaryTreeNode<V> curr = queue.poll();
                if (curr.left != null) {
                    if (curr.left.value.equals(value)) {
                        parent = curr;
                        node = curr.left;
                        leftChild = true;
                        break;
                    }
                    queue.add(curr.left);
                }
                if (curr.right != null) {
                    if (curr.right.value.equals(value)) {
                        parent = curr;
                        node = curr.right;
                        break;
                    }
                    queue.add(curr.right);
                }
            }
        }
        if (node == null) return;

        if (isLeafNode(node)) {
            if (leftChild) parent.left = null;
            else parent.right = null;
            return;
        }
        if (node.left == null) {
            if (leftChild) parent.left = node.right;
            else parent.right = node.right;
            return;
        }
        if (node.right == null) {
            if (leftChild) parent.left = node.left;
            else parent.right = node.left;
        }
        BinaryTreeNode<V> leafNode = findAnyLeafNode(node);
        delete(node, leafNode.getValue());
        node.value = leafNode.getValue();
    }

    protected BinaryTreeNode<V> findAnyLeafNode(BinaryTreeNode<V> node) {
        LinkedList<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                BinaryTreeNode<V> curr = queue.poll();
                if (isLeafNode(curr)) return curr;
                if (curr.left != null) {
                    queue.add(curr.left);
                }
                if (curr.right != null) {
                    queue.add(curr.right);
                }
            }
        }
        return null;
    }

    protected boolean isLeafNode(BinaryTreeNode<V> node) {
        return node != null && node.left == null && node.right == null;
    }

    @Override
    public BinaryTreeNode<V> getRoot() {
        return root;
    }

    public void setRoot(BinaryTreeNode<V> root) {
        this.root = root;
    }

    @Override
    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(BinaryTreeNode<V> root) {
        if (root == null) return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }


    @Override
    public int getDiameter() {
        diameter = 0;
        calculateDiameter(root);
        int value = diameter;
        diameter = null;
        return value;
    }

    private int calculateDiameter(BinaryTreeNode<V> root) {
        if (root == null) return 0;
        int left = calculateDiameter(root.left);
        int right = calculateDiameter(root.right);
        diameter = Math.max(diameter, 1 + left + right);
        return 1 + Math.max(left, right);
    }

    @Override
    public BinaryTreeNode<V> LCA(BinaryTreeNode<V> n1, BinaryTreeNode<V> n2) {
        return getLCA(root, n1, n2);

    }

    private BinaryTreeNode<V> getLCA(BinaryTreeNode<V> root, BinaryTreeNode<V> n1, BinaryTreeNode<V> n2) {
        if (root == null) return null;
        if (n1 == root) return n1;
        if (n2 == root) return n2;
        BinaryTreeNode<V> left = getLCA(root.left, n1, n2);
        BinaryTreeNode<V> right = getLCA(root.right, n1, n2);
        if (left == null && right == null) return null;
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }


    @Override
    public BinaryTreeNode<V> findNode(V value) {
        if (root == null) return null;
        LinkedList<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                BinaryTreeNode<V> curr = queue.poll();
                if (curr.value.equals(value)) {
                    return curr;
                }
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
        }
        return null;
    }

    @Override
    public BinaryTreeNode<V> findNodeUsingDFS(V value) {
        if (root == null) return null;
        Stack<BinaryTreeNode<V>> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            BinaryTreeNode<V> curr = stack.pop();
            if (curr.value == value) return curr;
            if (curr.right != null) stack.add(curr.right);
            if (curr.left != null) stack.add(curr.left);
        }
        return null;
    }
}
