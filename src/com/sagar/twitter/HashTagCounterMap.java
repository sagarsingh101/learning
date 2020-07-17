package com.sagar.twitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * This maintains all the hashTags recieved , and uses an auxillary TreeSet which internally maintains a BST to provide
 * maintain the hashTags in sorted order of their count.
 */
public class HashTagCounterMap implements HashTagCounter {
    private final Map<String, HashTag> hashTagMap;
    private final TreeSet<HashTag> hashTagCounter;

    public HashTagCounterMap() {
        this.hashTagCounter = new TreeSet<>((h1, h2) -> h1.count == h2.count ? h1.value.compareTo(h2.value) : h2.count - h1.count);
        this.hashTagMap = new HashMap<>();
    }

    public void addHashTag(String value) {
        if (hashTagMap.containsKey(value)) {
            HashTag hashTag = hashTagMap.get(value);
            // We need to remove and add so that tree set can maintain the sort order
            hashTagCounter.remove(hashTag);
            hashTag.incrementCount();
            hashTagCounter.add(hashTag);
        } else {
            HashTag tag = new HashTag(value);
            hashTagMap.put(value, tag);
            hashTagCounter.add(tag);
        }
    }

    public List<HashTag> getTopHashTags(int count) {
        int counter = 0;
        List<HashTag> hashTags = new ArrayList<>();
        for (HashTag tag : hashTagCounter) {
            hashTags.add(tag);
            counter++;
            if (counter >= count) break;
        }
        return hashTags;
    }
}
