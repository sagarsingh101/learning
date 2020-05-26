package com.sagar.learning.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sagarsingh on 2020-03-16
 */
public class NAryNode<V> {
    private V value;
    List<NAryNode<V>> children;

    public NAryNode(V value) {
        this.value = value;
        children = new ArrayList<>();
    }
}
