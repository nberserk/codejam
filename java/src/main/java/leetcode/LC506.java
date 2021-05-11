package main.java.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 2/11/17.
 * https://leetcode.com/problems/relative-ranks/
 *
 *
 */
public class RelativeRanks_506 {

    public String[] findRelativeRanks(final int[] nums) {
        int N = nums.length;
        Integer[] n = new Integer[N];
        for(int i=0;i<N;i++){
            n[i]=i;
        }

        Arrays.sort(n, new Comparator<Integer>() {
            public int compare(Integer m, Integer o) {
                return -nums[m] + nums[o];
            }
        });

        String[] ret = new String[N];
        // int idx =0;
        for(int i=0;i<N;i++){
            String cur;
            if(i==0)
                cur="Gold Medal";
            else if(i==1 )
                cur="Silver Medal";
            else if(i==2 )
                cur="Bronze Medal";
            else
                cur = String.valueOf(i+1);

            ret[n[i]]=cur;
        }
        //System.out.println(n[0]);
        return ret;
    }



    @Test
    public void test(){
        assertEquals("[Gold Medal, Silver Medal, Bronze Medal, 4, 5]", Arrays.toString(findRelativeRanks(new int[]{5,4,3,2,1})));
        assertEquals("[5, Gold Medal, Silver Medal, Bronze Medal, 4]", Arrays.toString(findRelativeRanks(new int[]{-1,7,3,2,1})));
    }


}
