package leetcode;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class Test1 {

    static class Fleet{
        int time;
        int live, dead;
    }

    public int carFleet(int target, int[] position, int[] speed) {

        ArrayList<Fleet> cars = new ArrayList<>();
        int N = position.length;
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                if (speed[i]==speed[j]) continue;
                double x = Math.floor( (position[j]-position[i])/(double)(speed[i]-speed[j] ));

                if(x<=0||x>target) continue;

                Fleet f = new Fleet();
                f.time= (int) x;
                if (speed[i]>speed[j]){
                    f.live=j;
                    f.dead=i;
                }else {
                    f.live=i;
                    f.dead=j;
                }
                cars.add(f);
            }
        }
        Collections.sort(cars, (a,b)->a.time-b.time);

        boolean[] dead = new boolean[N];
        for (int i = 0; i < cars.size(); i++) {
            Fleet f = cars.get(i);
            dead[f.dead] = true;
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
//        assertEquals(true, isOneBitCharacter(new int[]{1,0,0}));
//        assertEquals(false, isOneBitCharacter(new int[]{1,1,1,0}));
    }
}
