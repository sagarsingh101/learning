package com.sagar.learning.sets;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by sagarsingh on 2020-01-25
 */
public class NodeDisjointSet {
    Map<Integer,SetNode> dataMap;

    public NodeDisjointSet() {
        dataMap = new HashMap<>();
    }

    public SetNode makeSet(Integer data) {
        if(dataMap.containsKey(data)) return dataMap.get(data);
        SetNode node= new SetNode(data);
        dataMap.put(data,node);
        return node;
    }

    public void union(Integer x,Integer y) {
        SetNode parentx = findSet(x);
        SetNode parenty = findSet(y);
        if(parentx==parenty) return;
        if(parentx.rank>=parenty.rank) {
            parenty.parent=parentx;
            parentx.rank++;
            parenty.rank = 0;
        } else {
            parentx.parent = parenty;
            parenty.rank++;
            parentx.rank=0;
        }
    }

    public SetNode findSet(Integer data) {
        SetNode node = dataMap.get(data);
        if(node==null)  {
            node = makeSet(data);
        }
        if(node.parent==node) return node;
        node.parent = findSet(node.parent.data);
        return node.parent;
    }

    public List<Integer> countSets() {
        return dataMap.entrySet().stream()
                .filter(entry->entry.getValue().parent==entry.getValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
