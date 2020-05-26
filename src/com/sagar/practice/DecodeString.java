package com.sagar.practice;

import java.util.Stack;

/**
 * Created by sagarsingh on 2020-03-08
 */
public class DecodeString {

    public static void main(String[] args) {
        DecodeString decodeString = new DecodeString();
        System.out.println(decodeString.decode("30[a]"));
    }
        public String decode(String s) {
            if(s.length()==0) return s;
            Stack<String> st= new Stack<>();
            int i=0;
            for(char c:s.toCharArray()) {
                if(st.isEmpty() || c!=']') {
                    st.push(Character.toString(c));
                } else {
                    StringBuilder sb = new StringBuilder();
                    while(!st.peek().equals("[")) {
                        sb.append(st.pop());
                    }
                    st.pop();
                    String num = "";
                    while(!st.isEmpty() && Character.isDigit(st.peek().charAt(0))) num = st.pop()+num;
                    int multiplier = Integer.parseInt(num);
                    st.push(multiply(sb.toString(),multiplier));
                }
            }
            StringBuilder result= new StringBuilder();
            while(!st.isEmpty()) {
                result.append(st.pop());
            }
            return result.reverse().toString();
        }

        private String multiply(String s, int factor) {
        String result = "";
            while(factor-->0) {
                result+=s;
            }
            return result;
        }
    }
