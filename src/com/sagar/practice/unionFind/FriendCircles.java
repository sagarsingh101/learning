package com.sagar.practice.unionFind;

import com.sagar.learning.sets.DisjointSetIndexBased;
import com.sagar.learning.sets.NodeDisjointSet;

/**
 * LeetCode - 547 - https://leetcode.com/problems/friend-circles/
 * There are N students in a class. Some of them are friends, while some are not.
 * Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C,
 * then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.
 *
 * Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1,
 * then the ith and jth students are direct friends with each other, otherwise not. And you have to output the
 * total number of friend circles among all the students.
 */
public class FriendCircles {
    public static void main(String[] args) {
        int[][] M = new int[][]{{1,0,0,0,1,0,0,0,0,0,0,0,0,0,0},{0,1,0,0,0,1,0,0,0,0,0,0,0,0,0},{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},{1,0,0,0,1,0,0,0,0,0,0,0,1,0,0},{0,1,0,0,0,1,0,0,0,0,0,0,1,0,0},{0,0,0,0,0,0,1,0,0,0,0,1,0,0,0},{0,0,0,0,0,0,0,1,0,0,0,1,1,0,0},{0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,1,1,0,0,0},{0,0,0,0,0,0,1,1,0,0,1,1,0,0,1},{0,0,0,0,1,1,0,1,0,0,0,0,1,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},{0,0,0,0,0,0,0,0,0,0,0,1,0,0,1}};
        FriendCircles friendCircles = new FriendCircles();
        System.out.println(friendCircles.findCircleNumUsingIndex(M));
    }
    private NodeDisjointSet disjointSet;
    public int findCircleNum(int[][] M) {
        int index =0;
        disjointSet = new NodeDisjointSet();
        for(int i=0;i<M.length;i++) {
            for(int j=0;j<M[i].length;j++) {
                if(M[i][j]==1) {
                    disjointSet.union(i, j);
                }
            }
        }
       return disjointSet.countSets().size();
    }

    public int findCircleNumUsingIndex(int[][] M) {
        int index =0;
        DisjointSetIndexBased disjointSet = new DisjointSetIndexBased(M.length);
        for(int i=0;i<M.length;i++) {
            for(int j=0;j<M[i].length;j++) {
                if(M[i][j]==1) {
                    disjointSet.union(i, j);
                }
            }
        }
        return disjointSet.countSets().size();
    }
}
