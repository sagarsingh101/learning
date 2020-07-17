package com.sagar.practice.queue;

import java.util.PriorityQueue;

/**
 * Created by sagarsingh on 2020-07-12
 */
public class CourseScheduleIII {
    public static void main(String[] args) {
        CourseScheduleIII schedule = new CourseScheduleIII();
        schedule.scheduleCourse(new int[][]{{5, 15}, {3, 19}, {6, 7}, {2, 10}, {5, 16}, {8, 14}, {10, 11}, {2, 19}});
    }

    /*
        sort by increasing completion deadline
        then process each course , if at any point i am unable to take next couse due
        t1 + t2..ti > di , then remove the course in my sequence with highest time.
    */
    public int scheduleCourse(int[][] courses) {
        PriorityQueue<Course> processing = new PriorityQueue<>((c1, c2) -> {
            if (c1.endDay == c2.endDay) {
                return c1.duration - c2.duration;
            }
            return c1.endDay - c2.endDay;
        });
        for (int[] course : courses) {
            processing.add(new Course(course[0], course[1]));
        }
        PriorityQueue<Course> processed = new PriorityQueue<>((c1, c2) -> {
            if (c2.duration == c1.duration) {
                return c1.endDay - c2.endDay;
            }
            return c2.duration - c1.duration;
        });
        int maxCoursesTaken = 0, currTime = 0;
        while (!processing.isEmpty()) {
            Course curr = processing.poll();
            currTime += curr.duration;
            if (currTime > curr.endDay) {
                while (!processed.isEmpty() && currTime > curr.endDay) {
                    currTime -= processed.poll().duration;
                }
            }
            processed.add(curr);
            maxCoursesTaken = Math.max(maxCoursesTaken, processed.size());
        }
        return maxCoursesTaken;
    }

    public int[] sortedSquares(int[] A) {
        for (int i = 0; i < A.length; i++) {
            A[i] = A[i] * A[i];
        }
        return A;
    }

    class Course {
        int duration;
        int endDay;

        public Course(int t, int d) {
            this.duration = t;
            this.endDay = d;
        }
    }
}
