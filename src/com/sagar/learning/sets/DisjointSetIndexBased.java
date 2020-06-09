package com.sagar.learning.sets;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DisjointSetIndexBased {
    private IndexBasedNode[] dataMap;

    public DisjointSetIndexBased(int size) {
        this.dataMap = new IndexBasedNode[size];
    }

    public IndexBasedNode makeSet(Integer data) {
        if(dataMap[data]!=null) return dataMap[data];
        IndexBasedNode node= new IndexBasedNode(data,data);
        dataMap[data]= node;
        return node;
    }

    public void union(Integer x,Integer y) {
        IndexBasedNode parentx = findSet(x);
        IndexBasedNode parenty = findSet(y);
        if(parentx==parenty) return;
        if(parentx.rank>=parenty.rank) {
            parenty.parent=parentx.data;
            parentx.rank++;
            parenty.rank = 0;
        } else {
            parentx.parent = parenty.data;
            parenty.rank++;
            parentx.rank=0;
        }
    }

    public IndexBasedNode findSet(Integer data) {
        IndexBasedNode node = dataMap[data];
        if(node==null)  {
            node = makeSet(data);
        }
        if(node.parent==node.data) return node;
        node.parent = findSet(node.parent).data;
        return dataMap[node.parent];
    }

    public List<Integer> countSets() {
        return Arrays.stream(dataMap)
                .filter(entry->entry.data==entry.parent)
                .map(node->node.data)
                .collect(Collectors.toList());
    }


    public class IndexBasedNode {
         public int data;
         public int parent;
         public int rank;

        public IndexBasedNode(int data, int parent) {
            this.data = data;
            this.parent = parent;
        }
    }
}
