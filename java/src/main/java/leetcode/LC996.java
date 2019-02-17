package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

/**
 *
 */
public class LC996 {
    private boolean isSquare(int a, int b){
        int sqr = (int)Math.sqrt(a+b);
        return sqr*sqr==a+b;
    }

    public int numSquarefulPerms(int[] A) {
        visited.clear();

        permutate(A, 0);

        return visited.size();
    }

    void swap(int[] c, int i, int j){
        int t = c[i];
        c[i]=c[j];
        c[j]=t;
    }

    HashSet<String> visited = new HashSet<>();
    private void permutate(int[] a, int start) {
        int N=a.length;
        if(start==N){
            String result=Arrays.toString(a);
            visited.add(result);
            //System.out.println(Arrays.toString(a));
            return;
        }

        for (int i = start; i < N; i++) {
            if(i>start && a[i]==a[start]) continue;
            if(start!=0){
                if(!isSquare(a[start-1],a[i])) continue;
            }
            swap(a, start, i);
            permutate(a, start+1);
            swap(a, start, i);
        }
    }

    @Test
    public void test(){
        Assert.assertEquals(2, numSquarefulPerms(new int[]{1,17,8}));
        Assert.assertEquals(1, numSquarefulPerms(new int[]{2,2,2}));
        Assert.assertEquals(1, numSquarefulPerms(new int[]{2,2,2,2,2,2,2,2,2,2,2,2}));
    }
}
