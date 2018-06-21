package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static org.junit.Assert.assertEquals;

public class CarFleet_853 {

    static class Fleet{
        int time;
        int live, dead;
    }

    public int carFleet(int target, int[] position, int[] speed) {
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
                if (dead[jj] || speed[ii]==speed[jj])continue;
                double cross = (position[jj]-position[ii])/(double)(speed[ii]-speed[jj]);
                double y = position
                if(cross>=0 && cross<=target){
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
//        assertEquals(true, isOneBitCharacter(new int[]{1,0,0}));
//        assertEquals(false, isOneBitCharacter(new int[]{1,1,1,0}));
    }
}
