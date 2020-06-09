package com.sagar.practice.strings;

public class AdvancedSearch {
    public static void main(String[] args) {
        AdvancedSearch search = new AdvancedSearch();
        search.addWord("at","an","add","and","bat");
        search.search("bad");
    }
        Node root;
        /** Initialize your data structure here. */
        public AdvancedSearch() {
            root = new Node();
        }

    public void addWord(String... word) {
        for(String w:word) addWord(w);
    }

        /** Adds a word into the data structure. */
        public void addWord(String word) {
            Node curr = root;
            for(Character c:word.toCharArray()) {
                if(curr.child[c]==null) {
                    curr.child[c]=new Node();
                }
                curr = curr.child[c];
            }
            curr.leaf=true;
        }

        /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
        public boolean search(String word) {
            if(root==null) return false;
            return search(root,word,0);
            // Queue<Node> queue = new LinkedList<>();
            // queue.add(root);
            // int i=0;
            // while(i<word.length) {
            //     Node curr = queue.poll();
            //     if(word.charAt(i)=='.') {
            //         for(Node child:curr.child) {
            //             if(child!=null) queue.add(child);
            //         }
            //         i++;
            //     } else {
            //         if(curr.child[word.charAt(i)]==null && !queue.isEmpty()) return false;
            //         if(curr.child[word.charAt(i)]!=null) queue.add(curr.child[word.charAt(i)]);
            //     }
            // }
        }

        public boolean search(Node curr,String word,int index) {
            if(index==word.length()) return curr.leaf;
            if(word.charAt(index)=='.') {
                boolean search = false;
                for(Node child:curr.child) {
                    if(child!=null) {
                        search = search || search(child,word,index+1);
                    }
                }
                return search;
            } else {
                if(curr.child[word.charAt(index)]==null) return false;
                if(index==word.length()-1) {
                    return curr.child[word.charAt(index)].leaf;
                }
                return search(curr.child[word.charAt(index)],word,index+1);
            }
        }

        class Node {
            Node[] child;
            boolean leaf;
            public Node() {
                child = new Node[256];
            }
        }
}
