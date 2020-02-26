package com.sagar.learning.sets;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sagarsingh on 2020-01-25
 */
public class DisjoinSets {
    Map<Integer,SetNode> dataMap = new HashMap<>();

    public SetNode makeSet(Integer data) {
        SetNode node= new SetNode();
        node.parent=node;
        node.data = data;
        node.rank=0;
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
        if(node==null) return null;
        SetNode parent = node.parent;
        if(parent==node) return parent;
        node.parent = findSet(parent.data);
        return node.parent;
    }

}
