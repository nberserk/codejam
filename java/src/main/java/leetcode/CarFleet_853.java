package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static org.junit.Assert.assertEquals;

public class CarFleet_853 {

    static class Fleet{
        double time;
        int p,s;
    }

    // O(NlogN)
    public int carFleet(int target, int[] position, int[] speed) {
        int N = position.length;
        if(N==0) return 0;
        if(N==1) return 1;

        Fleet[] fleets = new Fleet[N];
        for (int i = 0; i < N; i++) {
            Fleet f = new Fleet();
            f.p = position[i];
            f.s = speed[i];
            f.time = (target-position[i])/(double)f.s;
            fleets[i]=f;
        }
        Arrays.sort(fleets, (a,b)->a.p-b.p);
        int ret=0;

        int i=N-2;
        int iFront=N-1;
        while(i>=0){
            if (fleets[i].time>fleets[iFront].time){//i and iFront can't meet. becuase i is much slower
                ret++;
                iFront=i;
            }
            i--;
        }

        return ret + 1;
    }

    // O(N^2) bad
    public int carFleet_try1(int target, int[] position, int[] speed) {
        int N = position.length;
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++) {
            a[i]=i;
        }

        Arrays.sort(a, (x,b)->position[x]-position[b]);
        boolean[] dead = new boolean[N];
        for (int i = N-2; i >=0; i--) {
            int ii = a[i];
            for (int j = i+1; j < N; j++) {
                int jj = a[j];
                if (dead[jj] || speed[ii]<=speed[jj])continue;
                double cross = (position[jj]-position[ii])/(double)(speed[ii]-speed[jj]);
                if(cross<=0) continue;
                double y = position[jj]+cross*speed[jj];
                if(y>=0 && y<=target){
                    if (speed[ii]<speed[jj])
                        dead[jj]=true;
                    else
                        dead[ii]=true;
                    break;
                }
            }
        }

        int ret=0;
        for (int i = 0; i < N; i++) {
            if(!dead[i]) ret++;
        }
        return ret;
    }


    @org.junit.Test
    public void test(){
        assertEquals(3, carFleet(12, new int[]{10, 8, 0,5,3}, new int[]{2,4,1,1,3}));
        assertEquals(2, carFleet(10, new int[]{6,8}, new int[]{3,2}));
        assertEquals(1, carFleet(10, new int[]{0,4,2}, new int[]{2,1,3}));
//        assertEquals(true, isOneBitCharacter(new int[]{1,0,0}));
//        assertEquals(false, isOneBitCharacter(new int[]{1,1,1,0}));
    }
}
