package leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class _880 {
    int[] p;
    Random r = new Random();
    public void init(int[] w) {
        p = new int[w.length];
        for(int i=0;i<w.length;i++){
            if(i!=0) p[i]=p[i-1];
            p[i]+=w[i];
        }
        System.out.println(Arrays.toString(p));
    }

    public int pickIndex() {
        int target = new Random().nextInt(p[p.length-1]+1);
        int lo=0;
        int hi=p.length-1;
        while(lo<hi){
            int mid = (hi+lo)/2;
            if(p[mid]>target)
                hi=mid;
            else if(p[mid]<target)
                lo=mid+1;
            else return mid;
        }
        return lo;
    }

    public int pickIndex_ref() {
        int len = p.length;
        int idx = r.nextInt(p[len-1]) + 1;
        int left = 0, right = len - 1;
        // search position
        while(left < right){
            int mid = left + (right-left)/2;
            if(p[mid] == idx)
                return mid;
            else if(p[mid] < idx)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }

    @Test
    public void test() {
        _880 sol = new _880();
        sol.init(new int[]{1,100});
        for (int i = 0; i < 100; i++) {
            System.out.println(sol.pickIndex());
        }
    }
}
