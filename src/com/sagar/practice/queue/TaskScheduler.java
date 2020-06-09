package com.sagar.practice.queue;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TaskScheduler {
    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();
        System.out.println(scheduler.leastInterval(new char[]{'A','A','A','B','B','B'},2));
    }
    public int leastInterval(char[] tasks, int n) {
        Map<Character,Task> taskMap=new HashMap<>();
        for(char id:tasks) {
            taskMap.put(id,taskMap.getOrDefault(id,new Task(id)).incrementCount());
        }
        PriorityQueue<Task> pq = new PriorityQueue<>((t1, t2)->t2.count-t1.count);
        taskMap.forEach((k,v)->pq.add(v));
        int totalTime = 0;
        while(!pq.isEmpty()) {
            int i = 0;

            List<Task> temp = new ArrayList<>();
            while(!pq.isEmpty() && i<=n) {
                totalTime++;
                Task t = pq.poll();
                t.count--;
                if(t.count>0)
                    temp.add(t);
                i++;
            }
            if(!temp.isEmpty()) {
                pq.addAll(temp);
                totalTime += n - i + 1;
            }
        }
        return totalTime;
    }

    class Task {
        char id;
        int count;
        public Task(char id) {
            this.id = id;
        }
        public Task incrementCount() {
            count++;
            return this;
        }
    }
}
