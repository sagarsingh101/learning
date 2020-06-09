package com.sagar.learning.sets;

/**
 * Created by sagarsingh on 2020-01-25
 */
public class SetNode {
    SetNode parent;
    int data;
    int rank;

    public SetNode(int data) {
        this.data = data;
        this.parent = this;
    }
}
