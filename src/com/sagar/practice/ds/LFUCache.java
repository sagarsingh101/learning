package com.sagar.practice.ds;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sagarsingh on 2020-07-10
 */
class LFUCache {
    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(2);
        lfuCache.put(1, 1);
        lfuCache.put(2, 2);
        lfuCache.get(1);
        lfuCache.put(3, 3);
        lfuCache.get(3);
        lfuCache.put(4, 4);
        System.out.println(lfuCache.get(1));
        System.out.println(lfuCache.get(3));
        System.out.println(lfuCache.get(4));

    }

    Map<Integer, LRU> valueMap;
    Map<Integer, Node> cache;
    int leastFreq;
    int capacity;

    public LFUCache(int capacity) {
        cache = new HashMap<>();
        valueMap = new HashMap<>();
        leastFreq = 1;
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) return -1;
        updateNode(node);
        return node.val;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node == null) {
            node = new Node(key, value);
            if (cache.size() == capacity) {
                Node deletedNode = valueMap.get(leastFreq).removelast();
                if (deletedNode != null) cache.remove(deletedNode.key);
            }
            valueMap.computeIfAbsent(1, x -> new LRU());
            valueMap.get(1).addNode(node);
            leastFreq = 1;
            cache.put(key, node);
        } else {
            node.val = value;
            updateNode(node);
        }
    }

    private void updateNode(Node node) {
        LRU listCache = valueMap.get(node.freq);
        listCache.removeNode(node);
        if (node.freq == leastFreq && listCache.isEmpty()) {
            leastFreq++;
        }
        node.freq++;
        valueMap.computeIfAbsent(node.freq, x -> new LRU());
        valueMap.get(node.freq).addNode(node);
    }

    class LRU {
        Node head;
        Node tail;

        public LRU() {
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
        }

        public void addNode(Node node) {
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
            node.prev = head;
        }

        public Node removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            return node;
        }

        public boolean isEmpty() {
            return head.next == tail;
        }

        public Node removelast() {
            if (!isEmpty()) {
                return removeNode(tail.prev);
            }
            return null;
        }
    }

    class Node {
        int val;
        int key;
        Node next;
        Node prev;
        int freq;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.freq = 1;
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
