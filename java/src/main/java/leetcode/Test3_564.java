package leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 6/23/2017.
 */
public class Test3_564 {

    int max(int[][] course, int day){
        return 0;
    }

    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1]==o2[1])
                    return o1[0]-o2[0];
                return o1[1]-o2[1];
            }
        });

        return max(courses, 0);

//        int count=0;
//        int day=0;
//        for (int[] c: courses){
//            if (day+c[0]<=c[1]){
//                day+=c[0];
//                count++;
//            }
//        }

//        return 1;
    }

    @Test
    public void test2(){

        assertEquals(3, scheduleCourse(new int[][]{{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}}));
        assertEquals(2, scheduleCourse(new int[][]{{5, 5}, {4, 6}, {2, 6}}));
        assertEquals(5, scheduleCourse(new int[][]{{5,15},{3,19},{6,7},{2,10},{5,16},{8,14},{10,11},{2,19}}));


    }
}
