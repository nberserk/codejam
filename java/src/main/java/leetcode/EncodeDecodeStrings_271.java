package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class EncodeDecodeStrings_271 {

    String encode(List<String> strs){

        StringBuilder sb = new StringBuilder();
        for (String s : strs){
            sb.append(s.length());
            sb.append('#');
            sb.append(s);
        }
        return sb.toString();
    }

    List<String> decode(String str){
        List<String> r = new ArrayList<>();

        int i=0;
        while(i<str.length()){
            int len = 0;
            while(str.charAt(i)!='#'){
                len = len*10 + (str.charAt(i++)-'0');
            }
            r.add(str.substring(i+1, i+1+len));
            i+=len+1;
        }

        return r;
    }

    @Test
    public void test(){

        List<String> list = Arrays.asList("hello", "there", "i", "am");
        assertEquals("5#hello5#there1#i2#am", encode(list));
        assertEquals("[hello, there, i, am]", decode("5#hello5#there1#i2#am").toString());
    }
}
