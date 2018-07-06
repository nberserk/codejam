package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class RandomPickWithBlacklist_864 {

    int N;
    int[] b;

    public void init(int N, int[] blacklist) {
        this.N=N;
        Arrays.sort(blacklist);
        b = new int[blacklist.length+1];
        int prev=0;
        for (int i = 0; i < blacklist.length; i++) {
            if(i==0)
                b[i] = blacklist[i]-prev;
            else
                b[i] = b[i-1]+blacklist[i]-prev-1;
            prev=blacklist[i];
        }
        b[blacklist.length]=b[blacklist.length-1]+1;
    }

    public int pick() {
        int target = (int)(Math.random()*(N-b.length));
        target++;

        int low = 0;
        int high = b.length-1;
        while(low<high){
            int mid = (low+high)/2;
            if(target>b[mid]){
                low=mid+1;
            }else if(target<b[mid]){
                high=mid-1;
            }else{
                high=mid;
            }
        }
        if(b[low]==target){
            return b[low]+low
        }

        return b[target]-1;
    }

    public void init_1(int N, int[] blacklist) {
        this.N=N;
        Arrays.sort(blacklist);
        b=blacklist;
    }

    public int pick_1() {
        int step = (int)(Math.random()*(N-b.length));
        int r=0;

        for(int i=0;i<b.length;i++){
            if(b[i]==r){
                r++;
                continue;
            }
            if(step==0) return r;
            int consume = b[i]-r;
            if(step>=consume){
                step-=consume;
                r+=consume+1;
            }else {
                return r+step;
            }
        }

        return r+step;
    }

    @Test
    public void test() {
        RandomPickWithBlacklist_864 r = new RandomPickWithBlacklist_864();
        r.init(4, new int[]{1,2});
        for (int i = 0; i < 5; i++) {
            int p = r.pick();
            Assert.assertTrue(p!= 1);
            Assert.assertTrue(p!= 2);
        }

    }
}
