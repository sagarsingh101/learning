package com.sagar.twitter;

/**
 * Created by sagarsingh on 2020-07-15
 */
public class HashTag {
    String value;
    int count;

    public HashTag(String value) {
        this.value = value;
        this.count = 1;
    }

    public void incrementCount() {
        this.count++;
    }

    public String toString() {
        return value + " has been used " + count + " times";
    }
}
