package com.sagar.learning.math;

import org.omg.PortableInterceptor.INACTIVE;

public class Division {
    public static void main(String[] args) {
        Division div = new Division();
        System.out.println(div.divideBySubtraction(-2147483648,2));
    }




    public int divideBySubtraction(int dividend, int divisor) {
        if(divisor==1) return dividend;
        if(divisor==-1) {
            if(dividend == Integer.MIN_VALUE) return Integer.MAX_VALUE;
            return -dividend;
        }

        if(divisor==Integer.MIN_VALUE) {
            if(dividend==Integer.MIN_VALUE) return 1;
            return 0;
        }
        int result = divison(Math.abs(dividend),Math.abs(divisor));
        if((dividend<0 && divisor<0) || (dividend>=0 && divisor>=0)) {
            return result;
        }
        return -result;
    }

    private int divison(int dividend, int divisor ) {
        int i=0;
        boolean overflow = false;
        int remainder = 0;
        if(dividend==Integer.MIN_VALUE) {
            dividend = Math.abs(dividend+1);
            overflow = true;
        }
        while(divisor<=dividend) {
            dividend = dividend - divisor;
            if(i==Integer.MAX_VALUE) {
                break;
            }
            remainder = dividend;
            i++;
        }
        if(overflow) {
            if(remainder+1==divisor) return i+1;
        }
        return i;
    }
}
