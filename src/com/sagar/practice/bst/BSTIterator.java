package com.sagar.practice.bst;

import com.sagar.learning.tree.binarytree.BinarySearchTree;
import com.sagar.learning.tree.binarytree.BinaryTree;
import com.sagar.learning.tree.binarytree.BinaryTreeNode;
import com.sagar.learning.tree.binarytree.SimpleBST;

import java.util.Stack;

public class BSTIterator {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new SimpleBST<>();
        bst.insert(7,3,15,20,9);
        BSTIterator iterator = new BSTIterator(bst.getRoot());
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
       Stack<BinaryTreeNode<Integer>> stack;
        public BSTIterator(BinaryTreeNode<Integer> root) {
            stack = new Stack<>();
            while (root!=null) {
                stack.push(root);
                root = root.left;
            }
        }

        /** @return the next smallest number */
        public int next() {
            BinaryTreeNode<Integer> next = stack.pop();
            if(next.right!=null) {
                BinaryTreeNode curr = next.right;
                while (curr!=null) {
                    stack.push(curr);
                    curr=curr.left;
                }
            }
            return next.value;
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !stack.isEmpty();
        }
}
