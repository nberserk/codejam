package main.java.codejam.lib;

import static org.junit.Assert.assertEquals;

/**
 * from: http://www.careercup.com/question?id=5080726124888064
 * Implement the power function with o(logN) time complexity and O(1) space
 */
public class Pow {

    static int [] cache = new int[100];

    static int pow(int base, int m){
        if(m==0)return 1;
        if(m==1) return base;

        int N=1;
        cache[1] = base;
        int count=2;
        while(N<m){
            N*=2;
            cache[count]=cache[count-1]*cache[count-1];
            count++;
        }
        if(N>m){
            N/=2;
            count--;
        }

        count--;
        int extra = m-N;
        int r = cache[count];
        while(extra>0){
            if(extra>=N){
                r*=cache[count];
                extra-=N;
            }else{
                N/=2;
                count--;
            }
        }

        return r;
    }

    static int pow2(int base, int power){
        int r =1;
        while(power>0){
            if((power&1)>0){
                r*=base;
            }
            base*=base;
            power = power >> 1;
        }
        return r;
    }


    static void test(){

        assertEquals(4,pow2(2,2));
        assertEquals(8,pow2(2,3));
        assertEquals(1024,pow2(2,10));
        assertEquals(16,pow2(4,2));

        assertEquals(4,pow(2,2));
        assertEquals(8,pow(2,3));
        assertEquals(1024,pow(2,10));
        assertEquals(16,pow(4,2));
    }

    public static void main(String[] args) {
        //test
        test();


    }
}
