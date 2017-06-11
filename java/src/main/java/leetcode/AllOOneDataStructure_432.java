package leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 16/02/2017.
 */
public class AllOOneDataStructure_432 {

    HashMap<String, Integer> map = new HashMap<>();
    HashMap<Integer, Set<String>> keys = new HashMap<>();
    int min=Integer.MAX_VALUE;
    int max=Integer.MIN_VALUE;

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if(map.containsKey(key) ){
            int v = map.get(key)+1;
            map.put(key, v);

            keys.get(v-1).remove(key);
            if(keys.get(v-1).size()==0 ) keys.remove(v-1);
            if (keys.containsKey(v))
                keys.get(v).add(key);
            else{
                Set<String> s = new HashSet<>();
                s.add(key);
                keys.put(v, s);
            }
            if(max==v-1) max=v;
            if(min==v-1 && !keys.containsKey(v-1) ) min=v;
        }else{
            int v=1;
            map.put(key, 1);
            if (keys.containsKey(v))
                keys.get(v).add(key);
            else{
                Set<String> s = new HashSet<>();
                s.add(key);
                keys.put(v, s);
            }
            min=Math.min(min, v);
            max=Math.max(max, v);
        }
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if(!map.containsKey(key) ) return;

        int v = map.get(key);
        if(v==1){
            map.remove(v);
            keys.get(v).remove(key);
            if(keys.get(v).size()==0) keys.remove(v);

            if(max==v) max = Integer.MIN_VALUE;
            if(min==v && !keys.containsKey(v) ) min=Integer.MAX_VALUE;
        }else{
            v--;
            map.put(key, v);

            keys.get(v+1).remove(key);
            if(keys.get(v+1).size()==0) keys.remove(v);

            if(keys.containsKey(v))
                keys.get(v).add(key);
            else{
                Set<String> s = new HashSet<>();
                s.add(key);
                keys.put(v, s);
            }
            if(max==v+1 && keys.containsKey(v+1)) max=v;
            if(min==v+1) min=v;
        }

    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if(keys.containsKey(max) ){
            for(String key: keys.get(max) ){
                return key;
            }
        }
        return "";
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if(keys.containsKey(min) ){
            for(String k: keys.get(min))
                return k;
        }
        return "";
    }


    @Test
    public void test(){
        AllOOneDataStructure_432 o = new AllOOneDataStructure_432();

        assertEquals("", o.getMaxKey());
        assertEquals("", o.getMinKey());
        o.inc("h");
        assertEquals("h", o.getMaxKey());
        assertEquals("h", o.getMinKey());

        o.dec("h");
        assertEquals("", o.getMaxKey());
        assertEquals("", o.getMinKey());

        o.inc("h");
        o.inc("h");
        assertEquals("h", o.getMaxKey());
        assertEquals("h", o.getMinKey());


//        assertEquals(987, largestPalindrome(2));
//        assertEquals(475, largestPalindrome(8));
    }
}
