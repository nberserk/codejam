package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class LC864 {


    int N;
    int[] b;

    public void init(int N, int[] blacklist) {
        this.N=N;
        Arrays.sort(blacklist);
        b = new int[blacklist.length+1];
        int prev=0;
        b[0]=0;
        for (int i = 0; i < blacklist.length; i++) {
            if(i==0)
                b[i+1] = blacklist[i]-prev;
            else
                b[i+1] = b[i]+blacklist[i]-prev-1;
            prev=blacklist[i];
        }
    }

    public int pick() {
        int target = (int)(Math.random()*(N-b.length+1)); //0 or 1

        int low = 0;
        int high = b.length-1; // 1 , target=1
        int repeat=0;
        while(low<high){
            int mid = (low+high)/2; //mid=1
            if(high-low==1){
                repeat++;
                if(repeat>5){
                    if(target>=b[high])
                        low=high;
                    break;
                }
            }else
                repeat=0;
            if(target>b[mid]){
                low=mid;//low= 0
            }else if(target<b[mid]){
                high=mid-1; //high=0
            }else{
                low=mid; // high=1
            }
        }
        int start = b[low]+low;//0
        return start + (target-b[low]);//0
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
        LC864 r = new LC864();
        r.init(4, new int[]{1,2});
        for (int i = 0; i < 5; i++) {
            int p = r.pick();
            Assert.assertTrue(p!= 1);
            Assert.assertTrue(p!= 2);
        }

    }

    @Test
    public void test2() {
        LC864 r = new LC864();
        r.init(4, new int[]{2});
        for (int i = 0; i < 10; i++) {
            int p = r.pick();
            System.out.println(p);
            Assert.assertTrue(p!= 2);
        }

    }
}
