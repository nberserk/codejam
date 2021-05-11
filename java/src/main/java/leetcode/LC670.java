package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;



public class MaximumSwap_670 {
    public int maximumSwap(int num) {
        int backup = num;
        List<Integer> n = new ArrayList<>();
        while (num > 0) {
            int v = num % 10;
            n.add(v);
            num /= 10;
        }
        int N = n.size();

        int largest = 0;
        int src=-1;
        int target =-1;

        for (int i = 1; i <N; i++) {
            if(n.get(i) > n.get(largest))
                largest=i;
            else if (n.get(i) < n.get(largest)){
                src=largest;
                target=i;
            }
        }

        if(target==-1) return backup;
        int r =0;
        for(int i=N-1 ;i>=0;i--){
            int v = n.get(i);
            if(i==src)
                v = n.get(target);
            else if(i==target)
                v = n.get(src);
            r = r*10+v;
        }
        return r;
    }

    @Test
    public void test(){
        assertEquals(7236, maximumSwap(2736));
        assertEquals(9973, maximumSwap(9973));
    }
}
