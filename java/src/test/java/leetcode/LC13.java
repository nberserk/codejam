package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

/**
 *
 */
public class LC13 {
    public int romanToInt(String s) {
        HashMap<Character,Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int ret=0;
        char prev=' ';
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(i!=0&& map.get(prev)<map.get(c)){
                ret+=-map.get(prev)*2+map.get(c);
            }else
                ret+=map.get(c);
            prev=c;
        }
        return ret;
    }

    @Test
    public void test(){
        Assert.assertEquals(1994,  romanToInt("MCMXCIV"));
    }
}
