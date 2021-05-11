package leetcode;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class StudentAttendanceRecordII_552 {

    int MOD = (int)Math.pow(10,9)+7;

    int checkRecord(int n){
        int[][][] cache = new int[n+1][2][3];
        Arrays.fill(cache[n][0], 1);
        Arrays.fill(cache[n][1], 1);        

        for (int i = n-1; i >=0; i--){
            for (int a = 0; a < 2; a++){
                for (int l = 0; l < 3; l++){
                    int v = 0;
                    if (a==1){
                        v += cache[i+1][0][2];
                        v %= MOD;
                    }
                    if (l!=0){
                        v += cache[i+1][a][l-1];
                        v%=MOD;
                    }
                    v+= cache[i+1][a][2];
                    v%=MOD;
                    cache[i][a][l] = v;
                }
            }
        }
        return  cache[0][1][2];
    }


    @org.junit.Test
    public void test(){
        assertEquals(8, checkRecord(2));

    }
}
