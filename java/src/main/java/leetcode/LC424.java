package main.java.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 1/31/17.
 */
public class LongestRepeatingCharacterReplacement_424 {
    int maxFreq(int[] f){
        int frequent=-1;
        int maxFreq=0;
        for(int i=0;i<26;i++){
            if(f[i]>maxFreq){
                frequent=i;
                maxFreq=f[i];
            }
        }
        return frequent;
    }

    public int characterReplacement(String s, int k) {
        int N = s.length();
        if(k>=N) return N;

        int[] freq = new int[26];
        int max=1;
        int start=0;
        int end=0;
        for(;end<N;end++){
            int e = s.charAt(end)-'A';
            int maxf = maxFreq(freq);

            if(maxf==-1 || maxf==e || freq[maxf]==freq[e]){
                freq[e]++;
            }else if (k>0){
                if( freq[maxf]==freq[e] ){ // no dec k

                }else{
                    k--;
                    freq[e]++;
                }
            }else{
                freq[e]++;
                while(true){
                    int cur = s.charAt(start)-'A';
                    freq[cur]--;
                    maxf = maxFreq(freq);
                    if(freq[cur]==freq[maxf]){
                        start++;
                    }else{
                        start++;
                        break;
                    }
                }
            }
            System.out.println("start:"+start+","+end);
            max=Math.max(max, end-start+1);
        }

        return max;
    }


    @Test
    public void test(){
        assertEquals(4, characterReplacement("AABABBA", 1));
    }
}
