package crackcode.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/lru-cache/
 *
 */
public class LRUCacheEasy<K,V> extends LinkedHashMap<K,V> {

    int MAX_ENTRY;

    public LRUCacheEasy(int max_capacity){
        super(max_capacity, 0.75f, true);
        this.MAX_ENTRY = max_capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size()>MAX_ENTRY;
    }

    public static void main(String[] args) {
        LRUCacheEasy<Integer, Integer> lru = new LRUCacheEasy<>(5);
        for (int i = 0; i < 5; i++) {
            lru.put(i,i);
        }

        Set<Integer> set = lru.keySet();
        assertEquals("[0, 1, 2, 3, 4]", set.toString());

        lru.get(1);
        assertEquals("[0, 2, 3, 4, 1]", set.toString());

        lru.get(4);
        assertEquals("[0, 2, 3, 1, 4]", set.toString());

        lru.put(5, 5);
        assertEquals("[2, 3, 1, 4, 5]", set.toString());
    }

//    public void test() {
//        LinkedHashMap<Integer, String> map = new LinkedHashMap<>(3, 0.75f, true);
//        map.put(1, "1");
//        map.put(2, "2");
//        map.put(3, "3");
//        map.put(4, "4");
//        map.put(5, "5");
//
//        Set<Integer> set = map.keySet();
//        assertEquals("[1, 2, 3, 4, 5]", set.toString());
//
//        map.get(1);
//        assertEquals("[2, 3, 4, 5, 1]", set.toString());
//
//        map.get(4);
//        assertEquals("[2, 3, 5, 1, 4]", set.toString());
//
//        map.put(5, "6");
//        assertEquals("[2, 3, 5, 1, 4]", set.toString());
//
//
//
//    }

}
