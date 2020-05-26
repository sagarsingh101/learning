package com.sagar.practice;

import java.util.*;

/**
 * Created by sagarsingh on 2020-03-17
 */
public class CourseSchedule {
    public static void main(String[] args) {
        CourseSchedule schedule = new CourseSchedule();
        int courses = 3;
        int[][] preq = new int[][]{{1,0},{2,0}};
        System.out.println(schedule.usingTopologicalSort(courses,preq));

    }
    public boolean usingTopologicalSort(int numCourses, int[][] prerequisites) {
        Node[] graph = createGraph(numCourses,prerequisites);
        Queue<Node> pq = new LinkedList<>();
        for(Node n:graph) {
            if(n.parents()==0)
            pq.add(n);
        }
        int removedNodes = 0;
        while (!pq.isEmpty()) {
            Node n = pq.poll();
            removedNodes++;
            if(n.parents()>0) return false;
            for(Node c:n.children) {
                c.parent.remove(n.data);
                if(c.parents()==0) {
                    pq.add(c);
                }
            }
        }
        return removedNodes==numCourses;
    }
    private Node[] createGraph(int n,int[][] relations) {
        Node[] nodes = new Node[n];
        for(int i=0;i<n;i++) {
            nodes[i]= new Node(i);
        }
        for(int[] arr:relations) {
            nodes[arr[0]].children.add(nodes[arr[1]]);
            nodes[arr[1]].parent.add(arr[0]);
        }
        return nodes;
    }
    public boolean usingDFS(int numCourses, int[][] prerequisites) {
        Node[] nodes = new Node[numCourses];
        for(int i=0;i<numCourses;i++) {
            nodes[i]= new Node(i);
        }
        for(int[] arr:prerequisites) {
            nodes[arr[0]].children.add(nodes[arr[1]]);
        }
        boolean[] checked = new boolean[numCourses];
        for(Node n:nodes) {
            int parent = n.data;
            boolean[] seen = new boolean[numCourses];
            Queue<Node> q = new LinkedList<>();
            q.add(n);
            while(!q.isEmpty()) {
                Node p = q.poll();
                if(checked[p.data]) continue;
                if(seen[p.data]) continue;
                seen[p.data]=true;;
                for(Node c:p.children) {
                    if(c.data==parent) return false;
                    q.add(c);
                }
            }
            checked[parent] = true;
        }
        return true;
    }

    class Node {
        int data;
        Set<Node> children;
        Set<Integer> parent;
        public Node(int data) {
            this.data = data;
            this.children = new HashSet<>();
            this.parent = new HashSet<>();
        }

        public int parents() {
            return parent.size();
        }
    }
}
