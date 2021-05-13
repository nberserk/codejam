package leetcode;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class LC135 {
    public int candy(int[] ratings) {
        int N = ratings.length;
        int[] c = new int[N];
        Arrays.fill(c,1);
        int inc=0;
        for(int i=0;i<N-1;i++){
            if(ratings[i]<ratings[i+1]){
                inc++;
            }else if(inc>0){
                int add=2;
                for(int j=i-inc+1;j<=i;j++){
                    c[j]=add++;
                }
                inc=0;
            }
        }
        if(inc>0){
            int add=2;
            for(int j=N-1-inc+1;j<=N-1;j++){
                c[j]=add++;
            }
            inc=0;
        }
        for(int i=N-1;i>0;i--){
            if(ratings[i]<ratings[i-1]){
                inc++;
            }else if(inc>0){
                int add=2;
                for(int j=i+inc-1;j>=i;j--){
                    c[j]=Math.max(c[j], add);
                    add++;
                }
                inc=0;
            }
        }
        if(inc>0){
            int add=2;
            for(int j=0+inc-1;j>=0;j--){
                c[j]=Math.max(c[j], add);
                add++;
            }
        }
        int sum=0;
        for(int i=0;i<N;i++){
            sum+=c[i];
        }
        return sum;
    }


    @Test
    public void test(){
        assertEquals(5, candy(new int[]{1,0,2}));
        assertEquals(4, candy(new int[]{1,2,2}));
    }
}
