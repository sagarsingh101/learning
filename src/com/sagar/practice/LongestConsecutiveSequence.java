package com.sagar.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sagarsingh on 2020-03-13
 */
public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        LongestConsecutiveSequence lcs = new LongestConsecutiveSequence();
        int[] nums = new int[]{0,3,7,2,5,8,4,6,0,1};
        System.out.println(lcs.longestConsecutive(nums));
    }
    Map<Integer,Node> nodeMap;
    int max = 0;
    public int longestConsecutive(int[] nums) {
        nodeMap = new HashMap<>();
        for(int i=0;i<nums.length;i++) {
            if(nodeMap.containsKey(nums[i])) continue;
            Node n = new Node();
            n.parent = true;
            if(nodeMap.containsKey(nums[i]-1)) {
                Node p = nodeMap.get(nums[i]-1);
                p.next = n;
                n.prev = p;
                n.parent=false;
                if(p.prev==null) p.parent = true;
            }
            if(nodeMap.containsKey(nums[i]+1)) {
                Node c = nodeMap.get(nums[i]+1);
                c.parent=false;
                c.prev=n;
                n.next = c;
                if(n.prev==null) n.parent = true;
            }
            nodeMap.put(nums[i],n);
        }
        nodeMap.entrySet().stream().filter(entry->entry.getValue().isParent()).forEach((entry->{
            Node n = entry.getValue();
            int count = 0;
            while(n!=null) {
                count++;
                n=n.next;
            }
            max = Math.max(max,count);
        }));
        return max;
    }
    private class Node {
        Node next;
        Node prev;
        boolean parent;
        private boolean isParent() {return parent;}
    }
}
