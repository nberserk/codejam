package leetcode;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 16/02/2017.
 *
 *


 Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

 However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

 You need to return the least number of intervals the CPU will take to finish all the given tasks.

 Example 1:
 Input: tasks = ['A','A','A','B','B','B'], n = 2
 Output: 8
 Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.

 */
public class LC621 {

    public int leastInterval(char[] tasks, int n) {
        int N = tasks.length;
        if(n==0) return N;
        int[] f = new int[26];

        int max=0;
        for(char c: tasks){
            f[c-'A']++;
            max=Math.max(max, f[c-'A'] );
        }
        Arrays.sort(f);
        int tie = 0;
        for(int i=24;i>=0;i--){
            if(f[i]==f[25]){
                tie++;
            }else break;
        }

        //System.out.println(Arrays.toString(f));
        int r = (max-1)*(n+1)+1;
        r+=tie;

        if(r<N){
            r=N;
        }

        return r;
    }


    @Test
    public void test(){
        assertEquals(8, leastInterval(new char[]{'A','A','A','B','B','B'}, 2));


    }
}
