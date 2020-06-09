package com.sagar.practice.strings;

/**
 * 953. Verifying an Alien Dictionary : https://leetcode.com/problems/verifying-an-alien-dictionary/
 * In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order.
 * The order of the alphabet is some permutation of lowercase letters.
 *
 * Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if
 * the given words are sorted lexicographicaly in this alien language
 */
public class SortByDifferentOrder {
    public static void main(String[] args) {
        SortByDifferentOrder sorter = new SortByDifferentOrder();
        String[] words = new String[]{"app","apple"};
        String order = "hlabcdefgijkmnopqrstuvwxyz";
        System.out.println(sorter.isAlienSorted(words,order));
    }
    public boolean isAlienSorted(String[] words, String order) {
        StringComparator comp = new StringComparator(order);
        for(int i=1;i<words.length;i++) {
            if(!comp.isLTE(words[i-1],words[i])) return false;
        }
        return true;
    }
    class StringComparator {
        int[] order;
        public StringComparator(String order) {
            this.order = new int[26];
            for(int i=0;i<order.length();i++) {
                this.order[order.charAt(i)-'a'] = i;
            }
        }

        public boolean isLTE(String s1,String s2) {
            int i=0,j=0;
            while(i<s1.length() && j<s2.length()) {
                int cmp = order[s1.charAt(i)-'a']-order[s2.charAt(j)-'a'];
                if(cmp>0)
                {
                    return false;
                } else if(cmp<0) {
                    return true;
                }
                i++;
                j++;
            }
            return s1.length()<=s2.length();
        }
    }
}
