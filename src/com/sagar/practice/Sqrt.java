package com.sagar.practice;

/**
 * Created by sagarsingh on 2020-03-21
 */
public class Sqrt {
    public static void main(String[] args) {
        Sqrt sqrt = new Sqrt();
        System.out.println(sqrt.mySqrt(2147483647));
    }
    int MAX = 46340;
    public int mySqrt(int x) {
        if(x==0) return 0;
        return search(1,x,x);
    }

    public int search(int st,int end,int x) {
        if(st<=end) {
            int mid = (end-st)/2 + st;
            if(mid>MAX) {
                return search(st,MAX,x);
            }
            if(mid+1>MAX) return MAX;
            if(mid*mid==x) return mid;
            if(mid*mid>x) {
                if((mid-1)*(mid-1)<=x) return mid-1;
                return search(st,mid-1,x);
            } else {
                if((mid+1)*(mid+1)>x) return mid;
                return search(mid+1,end,x);
            }
        }
        return 1;
    }
}
