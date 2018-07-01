package leetcode;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;

public class ShortEncodingOfWords_820 {
    public int minimumLengthEncoding(String[] words) {
        Arrays.sort(words, (a,b)->b.length()-a.length());

        ArrayList<String> ret = new ArrayList<>();
        for(String s: words){
            String reverse = new StringBuilder().append(s).reverse().toString();
            boolean merged=false;
            for(String org: ret){
                if(org.startsWith(reverse)){
                    merged=true;
                    break;
                }
            }
            if(!merged)
                ret.add(reverse);
        }

        int count = 0;
        for (String s: ret){
            count+= s.length()+1;
        }
        return count;
    }

    @org.junit.Test
    public void test(){
        Assert.assertEquals(10, minimumLengthEncoding(new String[]{"time", "me", "bell"}));

    }
}
