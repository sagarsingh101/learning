package com.sagar.practice.stack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/exclusive-time-of-functions/
 * On a single threaded CPU, we execute some functions.  Each function has a unique id between 0 and N-1.
 *
 * We store logs in timestamp order that describe when a function is entered or exited.
 *
 * Each log is a string with this format: "{function_id}:{"start" | "end"}:{timestamp}".  For example, "0:start:3" means
 * the function with id 0 started at the beginning of timestamp 3.  "1:end:2" means the function with id 1 ended at the
 * end of timestamp 2.
 *
 * A function's exclusive time is the number of units of time spent in this function.  Note that this does not include any recursive calls to child functions.
 *
 * The CPU is single threaded which means that only one function is being executed at a given time unit.
 *
 * Return the exclusive time of each function, sorted by their function id.
 */
public class ExclusiveTimeOfFunctions {
    public static void main(String[] args) {
        List<String> logs = Arrays.asList("0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7");
        ExclusiveTimeOfFunctions functions = new ExclusiveTimeOfFunctions();
        System.out.println(Arrays.toString(functions.exclusiveTime(
                1,logs)));

    }
    public int[] exclusiveTime(int n, List<String> logs) {
            Stack<Integer> processStack = new Stack<>();
            int [] result = new int[n];
            String [] log = logs.get(0).split(":");
            processStack.push(Integer.parseInt(log[0]));
            int prev = Integer.parseInt(log[2]);
            int i=1;
            while(i<logs.size()) {
                log = logs.get(i++).split(":");
                if(log[1].equals("start")) {
                    if(!processStack.isEmpty()) {
                        result[processStack.peek()]+=Integer.parseInt(log[2])-prev;
                    }
                    processStack.push(Integer.parseInt(log[0]));
                    prev = Integer.parseInt(log[2]);
                } else {
                    result[processStack.peek()]+=Integer.parseInt(log[2])-prev+1;
                    processStack.pop();
                    prev = Integer.parseInt(log[2]) + 1;
                }
            }
            return result;
    }
}
