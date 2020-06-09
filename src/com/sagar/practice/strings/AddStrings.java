package com.sagar.practice.strings;

/**
 * https://leetcode.com/problems/add-strings/
 * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
 *
 * Note:
 *
 * The length of both num1 and num2 is < 5100.
 * Both num1 and num2 contains only digits 0-9.
 * Both num1 and num2 does not contain any leading zero.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
  */
public class AddStrings {

    public static void main(String[] args) {
        AddStrings adder = new AddStrings();
        System.out.println(adder.addStrings("132","92"));
    }
    public String addStrings(String num1, String num2) {
        if(num1.length()<num2.length()) {
            String temp = num2;
            num2=num1;
            num1=temp;
        }
       int i=num1.length()-1,j=num2.length()-1;
       if(i<0) return num2;
       if(j<0) return num1;
       StringBuilder result = new StringBuilder();
       int carry=0;
       while (i>=0 && j>=0) {
           int value = num1.charAt(i)-'0' + num2.charAt(j)-'0';
           value+=carry;
           carry=value/10;
           value=value%10;
           result.append(value);
           i--;
           j--;
       }
       while (i>=0) {
           int value=num1.charAt(i)-'0'+carry;
           carry = value/10;
           value = value%10;
           result.append(value);
           i--;
       }
       if(carry>0) result.append(carry);
       return result.reverse().toString();
    }
}
