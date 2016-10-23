package crackcode.design;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 2016. 10. 23..
 *
 * https://leetcode.com/problems/insert-delete-getrandom-o1/
 *
 * idea : hashmap x arrayList
 */
public class RandomizedSet {
    ArrayList<Integer> array = new ArrayList<>();
    HashMap<Integer, Integer> map = new HashMap<>();
    Random random = new Random();;


    boolean insert(int val){
        Integer loc = map.get(val);
        if(loc!=null) return false;

        map.put(val, array.size());
        array.add(val);
        return true;
    }

    boolean remove(int val){
        Integer loc = map.get(val);
        if(loc==null) return false;
        if(loc != array.size()-1){
            int nv = array.get(array.size()-1);
            array.set(loc, nv);
            map.put(nv, loc);
        }
        map.remove(val);
        array.remove(array.size()-1);
        return true;
    }

    int getRandom(){
        return array.get( random.nextInt(array.size()));
    }

    @Test
    public void test(){
        RandomizedSet set = new RandomizedSet();
        assertEquals(false, set.remove(5));
        assertEquals(true, set.insert(5));
        assertEquals(false, set.insert(5));
        assertEquals(5, set.getRandom());
    }

}
