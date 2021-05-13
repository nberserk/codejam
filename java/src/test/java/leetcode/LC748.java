package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class LC748 {

    public String shortestCompletingWord(String licensePlate, String[] words) {
        HashMap<Character, Integer> map = new HashMap<>();
        for(char c: licensePlate.toCharArray()){
            if( c>='A' && c<='Z')
                c= (char) ('a' + (c-'A'));
            if(c>='a' && c<='z'){
                map.putIfAbsent(c, 0);
                map.put(c, map.get(c)+1);
            }
        }

        String ret = "";
        HashMap<Character, Integer> cur = new HashMap<>();
        for(int i=0;i<words.length;i++){
            for (char c : words[i].toCharArray()){
                if(map.containsKey(c)){
                    cur.putIfAbsent(c, 0);
                    cur.put(c, cur.get(c)+1);
                }
            }
            boolean same=true;
            for(char key : map.keySet()){
                if (!cur.containsKey(key)){
                    same=false;
                    break;
                }
                if(map.get(key)>cur.get(key)){
                    same=false;
                    break;
                }
            }
            if(same){
                if (ret.length()==0 || words[i].length()<ret.length()){
                    ret=words[i];
                }
            }
            cur.clear();
        }

        return ret;
    }

    @Test
    public void test() {
        Assert.assertEquals("according", shortestCompletingWord("GrC8950", new String[]{"measure","other","every","base","according","level","meeting","none","marriage","rest"}) );
        Assert.assertEquals("steps", shortestCompletingWord("1s3 PSt", new String[]{"step", "steps", "stripe", "stepple"}) );

    }
}
