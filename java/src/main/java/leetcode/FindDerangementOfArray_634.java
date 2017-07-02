package leetcode;

import static org.junit.Assert.assertEquals;

/**

 *
 */
public class FindDerangementOfArray_634 {
    public int findDerangement(int n) {
        int mod = (int)Math.pow(10,9)+7;
        long[] dp = new long[n+2];
        dp[1]=0;
        dp[2]=1;
        for (int i = 3; i <= n; i++) {
            dp[i] = ((i-1)*(dp[i-1]+dp[i-2]))%mod;
            // assume n[0] is i : (n-1) possibility
            // case1> n[i]==n[0] : dp[n-2]
            // case2> n[i]!=n[0] : dp[n-1], choose n-1 hat with n-1 person
        }
        return (int)dp[n];
    }


    @org.junit.Test
    public void test(){

        assertEquals(2, findDerangement(3));
        assertEquals(9, findDerangement(4));

//        assertEquals(true, judgeSquareSum(5));
//        assertEquals(false, judgeSquareSum(3));


    }
}
