package leetcode;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class ShoppingOffers_638 {

    int curMin;
    int max = 1000000;

    int sub(List < Integer > price, List < List < Integer >> special, List<Integer> need){
        int N = need.size();
        int zeroCount=0;
        for (int n: need){
            if(n==0) zeroCount++;
        }
        if(zeroCount==N) return 0;


        int r = Integer.MAX_VALUE;
        for (int i = 0; i < special.size(); i++) {
            boolean valid=true;
            for (int c = 0; c < N; c++) {
                if(special.get(i).get(c) > need.get(c) ) {
                    valid = false;
                    break;
                }
            }
            if(valid){
                for (int j = 0; j < N; j++) {
                    need.set(j, need.get(j)-special.get(i).get(j));
                }
                r = Math.min(r, special.get(i).get(N) + sub(price,special, need ));
                for (int j = 0; j < N; j++) {
                    need.set(j, need.get(j)+special.get(i).get(j));
                }
            }
        }

        int v = 0;
        for (int i = 0; i <N; i++) {
            v += need.get(i) * price.get(i);
        }
        r = Math.min(r, v);

        return r;
    }
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {

        return sub( price,  special, needs);
    }

    @org.junit.Test
    public void test(){
        List<Integer> price = Arrays.asList(2, 5);
        List<Integer> s1 = Arrays.asList(3,0,5);
        List<Integer> s2 = Arrays.asList(1,2,10);
        List<List<Integer>> s = Arrays.asList(s1, s2);
        List<Integer> need = Arrays.asList(3,2);

        assertEquals(14, shoppingOffers(price,s,need));
    }
}
