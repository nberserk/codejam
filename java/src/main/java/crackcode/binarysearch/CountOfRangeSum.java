package crackcode.binarysearch;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 2016. 11. 11..
 */
public class CountOfRangeSum {
    int firstGreater(int[] s, int start, int end, int target){
        int l=start;
        int h=end;
        while (l<h){
            int m = l+(h-l)/2;
            if (s[m]<target){
                l=m+1;
            }else h=m;
        }
        if (s[l]>=target) return l;
        return -1;
    }

    int lastSmaller(int[] s, int start, int end, int target){
        int l=start;
        int h=end;
        while (l<h){
            int m = l+(h-l+1)/2;
            if (s[m]>target){
                h=m-1;
            }else l=m;
        }
        if (s[l]<=target) return l;
        return -1;
    }

    public int countRangeSum(int[] nums, int lower, int upper) {
        int N = nums.length;
        if(N==0) return 0;
        int[] s = new int[N];
        int sum =0;


        for (int i = 0; i < N; i++){
            sum += nums[i];
            s[i]=sum;
        }
        int count=0;
        if(lower<=sum && sum<=upper) count++;
        Arrays.sort(s);
        for (int i = 0; i < N; i++){
            int start = firstGreater(s, i, N-1, lower+s[i]);
            if(start==-1) continue;
            int end = lastSmaller(s, start, N-1, upper+s[i]);
            if(end==-1) continue;
            count += end-start+1;
            System.out.println(i+":"+start +","+end);
        }
        return count;
    }

    @Test
    public void test(){
        assertEquals(3, countRangeSum(new int[]{-2,5,-1}, -2, 2));
        assertEquals(4, countRangeSum(new int[]{-2,3,2}, -2, 2));
        assertEquals(0, countRangeSum(new int[]{}, 1,2));
        assertEquals(1, countRangeSum(new int[]{0}, 0,0));

    }
}
