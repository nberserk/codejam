package leetcode;


import static org.junit.Assert.assertEquals;

public class LC878 {

    long mod=1000000007;
    public int nthMagicalNumber_1st(int N, int A, int B) {
        long gcd = gcd(A,B);
        gcd=A*(B/gcd);//6
        long count=0;
        long i=1;
        count +=gcd/A+gcd/B-1;
        if(N < count){
            return (int)((gcd/A+gcd/B)%mod);
        }

        long multiple=N/count; // m=2
        i=i*multiple;//i=8
        count*=multiple;//count=4
        while(count<N){
            i++;
            if(i%A==0||i%B==0) count++;
        }
        return (int)(i%mod);
    }

    public int nthMagicalNumber(int N, int A, int B) {
        long gcd = gcd(A,B);
        gcd=A*(B/gcd);//6

        long lo=1;
        long hi=Long.MAX_VALUE-100;
        while(lo<hi){
            long mid=(lo+hi)/2;
            long count = mid/A+mid/B-mid/(gcd);
            if(count>N){
                hi=mid-1;
            }else if(count<N){
                lo=mid+1;
            }else hi=mid;
        }
        return (int)(lo%mod);
    }

    int gcd(int a,int b){
        if(b>a) return gcd(b,a);
        if(a%b==0)
            return b;
        return gcd(a-b,b);
    }

    @org.junit.Test
    public void test() {
        assertEquals(999860007, nthMagicalNumber(1000000000, 40000, 39999));
        assertEquals(10, nthMagicalNumber(5, 2, 4));
        assertEquals(2, nthMagicalNumber(1, 2, 3));
        assertEquals(6, nthMagicalNumber(4, 2, 3));
    }
}
