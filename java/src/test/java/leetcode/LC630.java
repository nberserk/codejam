package leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 6/23/2017.
 *
 *
 * There are n different online courses numbered from 1 to n. Each course has some duration(course length) t and closed on dth day. A course should be taken continuously for t days and must be finished before or on the dth day. You will start at the 1st day.

 Given n online courses represented by pairs (t,d), your task is to find the maximal number of courses that can be taken.

 Example:
 Input: [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
 Output: 3
 Explanation:
 There're totally 4 courses, but you can take 3 courses at most:
 First, take the 1st course, it costs 100 days so you will finish it on the 100th day, and ready to take the next course on the 101st day.
 Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day, and ready to take the next course on the 1101st day.
 Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day.
 The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.
 Note:
 The integer 1 <= d, t, n <= 10,000.
 You can't take two courses simultaneously.


 */
public class LC630 {

    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a,b) -> a[1]-b[1] );
        PriorityQueue<Integer> pq = new PriorityQueue<>(100, (a,b)->b-a);

        int day=0;
        for (int[] c: courses){
            day+=c[0];
            pq.add(c[0]);
            if (day>c[1]){
                day -= pq.poll();
            }
        }

        return pq.size();
    }

    @Test
    public void test2(){

        assertEquals(3, scheduleCourse(new int[][]{{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}}));
        assertEquals(2, scheduleCourse(new int[][]{{5, 5}, {4, 6}, {2, 6}}));
        assertEquals(5, scheduleCourse(new int[][]{{5,15},{3,19},{6,7},{2,10},{5,16},{8,14},{10,11},{2,19}}));


    }
}
