package com.sagar.learning.tree.binarytree;

import java.util.Comparator;

/**
 * Created by sagarsingh on 2020-05-24
 */
public class SimpleBST<V> extends AbstractBinaryTree<V> implements BinarySearchTree<V> {
    protected final Comparator<V> comparator;

    public SimpleBST() {
        comparator = null;
    }

    public SimpleBST(Comparator<V> comparator) {
        super();
        this.comparator = comparator;
    }

    @Override
    public void insert(V value) {
        if (this.getRoot() == null) {
            super.insert(value);
            return;
        }
        if (comparator != null) {
            insertUsingComparator(value);
        } else {
            Comparable<? super V> comparable = (Comparable<? super V>) value;
            BinaryTreeNode<V> node = this.getRoot();
            while (node != null) {
                int cmp = comparable.compareTo(node.value);
                if (cmp > 0) {
                    if (node.right == null) {
                        node.right = new BinaryTreeNode<>(value);
                        break;
                    }
                    node = node.right;
                } else if (cmp < 0) {
                    if (node.left == null) {
                        node.left = new BinaryTreeNode<>(value);
                        break;
                    }
                    node = node.left;
                } else {
                    break;
                }
            }
        }
    }

    private void insertUsingComparator(V value) {
        BinaryTreeNode<V> node = this.getRoot();
        while (node != null) {
            int cmp = comparator.compare(node.value, value);
            if (cmp > 0) {
                if (node.right == null) {
                    node.right = new BinaryTreeNode<>(value);
                    break;
                }
                node = node.right;
            } else if (cmp < 0) {
                if (node.left == null) {
                    node.left = new BinaryTreeNode<>(value);
                    break;
                }
                node = node.left;
            } else {
                break;
            }
        }
    }


    @Override
    public boolean exists(V value) {
        return findNode(value) != null;
    }

    @Override
    public void delete(V value) {
        BinaryTreeNode<V> curr = getRoot();
        BinaryTreeNode<V> parent = null;
        Comparable<? super V> comparable = comparator == null ? (Comparable<? super V>) value : null;
        while (curr != null) {
            int cmp = comparator == null ? comparable.compareTo(curr.getValue()) : comparator.compare(value, curr.value);
            if (cmp > 0) {
                parent = curr;
                curr = curr.right;
            } else if (cmp < 0) {
                parent = curr;
                curr = curr.left;
            } else {
                break;
            }
        }
        if (curr == null) return;
        if (curr.left != null && curr.right != null) {
            BinaryTreeNode<V> successor = getInorderSuccessor(curr);
            curr.value = successor.value;
            if (parent == null) {
                this.setRoot(curr);
            }
            deleteEdgeNode(findParent(curr, successor), successor);
            return;
        }
        deleteEdgeNode(parent, curr);
        return;
    }

    private BinaryTreeNode<V> findParent(BinaryTreeNode<V> curr, BinaryTreeNode<V> successor) {
        if (curr == successor) {
            return curr;
        }
        Comparable<? super V> comparable = comparator == null ? (Comparable<? super V>) successor.value : null;
        while (curr != null && curr.left != successor && curr.right != successor) {
            int cmp = comparator == null ? comparable.compareTo(curr.getValue()) : comparator.compare(successor.value, curr.value);
            if (cmp > 0) {
                curr = curr.right;
            } else if (cmp < 0) {
                curr = curr.left;
            } else {
                break;
            }
        }
        return curr;
    }

    private void deleteEdgeNode(BinaryTreeNode<V> parent, BinaryTreeNode<V> curr) {
        if (curr.right != null) {
            if (parent == null) {
                this.setRoot(curr.right);
            } else {
                if (parent.right == curr) parent.right = curr.right;
                else parent.left = curr.right;
            }
            return;
        }
        if (curr.left != null) {
            if (parent == null) {
                this.setRoot(curr.left);
            } else {
                if (parent.right == curr) parent.right = curr.left;
                else parent.left = curr.left;
            }
            return;
        }
        if (parent == null) {
            this.setRoot(null);
        } else {
            if (parent.left == curr) parent.left = null;
            else parent.right = null;
        }
    }

    /**
     * this method is not full proof as it cannot return the parent if there are no successors found in the subtree.
     *
     * @param curr
     * @return
     */
    protected BinaryTreeNode<V> getInorderSuccessor(BinaryTreeNode<V> curr) {
        if (curr == null) return null;
        curr = curr.right;
        while (curr != null && curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }

    @Override
    public BinaryTreeNode<V> LCA(BinaryTreeNode<V> n1, BinaryTreeNode<V> n2) {
        return findLCA(n1, n2);
    }

    private BinaryTreeNode<V> findLCA(BinaryTreeNode<V> n1, BinaryTreeNode<V> n2) {
        BinaryTreeNode<V> curr = this.getRoot();
        Comparable<? super V> compn1 = comparator == null ? (Comparable<? super V>) n1.value : null;
        Comparable<? super V> compn2 = comparator == null ? (Comparable<? super V>) n2.value : null;
        while (curr != null) {
            int cmpn1 = comparator == null ? compn1.compareTo(curr.getValue()) : comparator.compare(n1.value, curr.value);
            int cmpn2 = comparator == null ? compn2.compareTo(curr.getValue()) : comparator.compare(n1.value, curr.value);
            if (cmpn1 > 0 && cmpn2 < 0) {
                return curr;
            }
            if (cmpn1 > 0 && cmpn2 > 0) {
                curr = curr.right;
            } else if (cmpn1 < 0 && cmpn2 < 0) curr = curr.left;
            else if (cmpn1 == 0) return n1;
            else if (cmpn2 == 0) return n2;
        }
        return null;
    }

    @Override
    public BinaryTreeNode<V> findNode(V value) {
        if (this.getRoot() == null) {
            return null;
        }
        if (comparator != null) {
            return findUsingComparator(value);
        } else {
            Comparable<? super V> comparable = (Comparable<? super V>) value;
            BinaryTreeNode<V> node = this.getRoot();
            while (node != null) {
                int cmp = comparable.compareTo(node.value);
                if (cmp > 0) {
                    node = node.right;
                } else if (cmp < 0) {
                    node = node.left;
                } else {
                    return node;
                }
            }
        }
        return null;
    }

    private BinaryTreeNode<V> findUsingComparator(V value) {
        BinaryTreeNode<V> node = this.getRoot();
        while (node != null) {
            int cmp = comparator.compare(node.value, value);
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                return node;
            }
        }
        return null;
    }

    @Override
    public BinaryTreeNode<V> findNodeUsingDFS(V value) {
        return findNode(value);
    }

    @Override
    public BinaryTreeNode<V> findInorderSuccessor(V value) {
        BinaryTreeNode node = findNode(value);
        BinaryTreeNode successor = getInorderSuccessor(node);
        if(successor==null) {
            //need to return parent if exists here.
        }
        return successor;
    }
}
