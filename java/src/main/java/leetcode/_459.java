package leetcode;


import org.junit.Assert;

import java.util.HashSet;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class _459 {

    public boolean repeatedSubstringPattern(String s) {
        int N=s.length();
        for(int l=1;l<=N/2;l++){
            if(N%l!=0) continue;
            boolean valid=true;
            for(int i=1;i<N/l;i++){
                for(int k=0;k<l;k++){
                    if(s.charAt(k)!=s.charAt(i*l+k)){
                        valid=false;
                        break;
                    }
                }
                if(!valid) break;
            }
            if(valid) return true;
        }
        return false;
    }

    @org.junit.Test
    public void test() {
        Assert.assertEquals(true, repeatedSubstringPattern("abab"));
        Assert.assertEquals(false, repeatedSubstringPattern("abababc"));

    }
}
