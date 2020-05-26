package com.sagar.practice;

/**
 * Created by sagarsingh on 2020-03-12
 */
public class Trie {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.startsWith("app"));
    }
        Node root;
        /** Initialize your data structure here. */
        public Trie() {
            root = new Node();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            insert(root,word,0);
        }

        private void insert(Node root,String s, int index) {
            if(index>=s.length()) return;
            char c = s.charAt(index);
            if(root.child[c-'a']==null) {
                root.child[c-'a'] = new Node();
            }
            if(index==s.length()-1) root.child[c-'a'].leaf = true;
            insert(root.child[c-'a'],s,index+1);
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            return search(root,word,0);
        }

        private boolean search(Node root,String s,int index) {
            if(index>=s.length()) return false;
            Node child = root.child[s.charAt(index)-'a'];
            if(child==null) return false;
            if(index==s.length()-1) return child.leaf;
            return search(child,s,index+1);
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            return startsWith(root,prefix,0);
        }

        private boolean startsWith(Node root,String s,int index) {
            if(index>=s.length()) return true;
            Node child = root.child[s.charAt(index)-'a'];
            if(child==null) return false;
            return startsWith(child,s,index+1);
        }



        class Node {
            Node[] child;
            boolean leaf;
            public Node() {
                child = new Node[26];
            }
        }
    }
