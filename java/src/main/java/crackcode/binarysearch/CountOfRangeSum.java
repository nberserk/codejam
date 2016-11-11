package crackcode.binarysearch;

import java.util.Arrays;

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
            int m = l+(h-l)/2;
            if (s[m]>target){
                h=m-1;
            }else l=m;
        }
        if (s[l]>=target) return l;
        return -1;
    }

    public int countRangeSum(int[] nums, int lower, int upper) {
        int N = nums.length;
        int[] s = new int[N];
        s[0]=nums[0];
        for (int i = 1; i < N; i++){
            s[i]=s[i-1]+nums[i];
        }

        Arrays.sort(s);
        int count=0;
        for (int i = 0; i < N-1; i++){
            int start = firstGreater(s, i+1, N-1, lower+s[0]);
            int end = lastSmaller(s, start+1, N-1, upper+s[0]);
        }


    }
}
