package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 */
public class LC888 {
    public String[] uncommonFromSentences(String A, String B) {
        String[] str = A.split(" ");
        HashMap<String,Integer> map = new HashMap<>();
        for(String s:str){
            map.putIfAbsent(s,0);
            map.put(s, map.get(s)+1);
        }
        str = B.split(" ");
        for(String s:str){
            map.putIfAbsent(s,0);
            map.put(s, map.get(s)+1);
        }
        List<String> uncommon = new ArrayList<>();
        for(String k: map.keySet()){
            if(map.get(k)==1)
                uncommon.add(k);
        }
        return uncommon.toArray(new String[uncommon.size()]);
    }

    @Test
    public void test(){



        
    }
}
