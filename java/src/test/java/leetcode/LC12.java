package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class LC12 {
    char[] d1 = new char[] {'I', 'X', 'C', 'M'};
    char[] d5 = new char[] {'V', 'L', 'D'};

    public String intToRoman(int num) {
        String ret="";
        int idx=0;
        while(num>0){
            int v = num%10;
            String cur="";
            if(v==4){
                cur=String.format("%c%c", d1[idx],d5[idx]);
            }else if(v==9){
                cur=String.format("%c%c", d1[idx],d1[idx+1]);
            }else{
                if(v>=5){
                    cur+=d5[idx];
                    v-=5;
                }
                while(v>0){
                    cur+=d1[idx];
                    v--;
                }
            }
            ret=cur+ret;
            num/=10;
            idx++;
        }
        return ret;
    }

    @Test
    public void test(){
        Assert.assertEquals("MCMXCIV", intToRoman(1994));
    }
}
