package main.java.codejam.lib.impl;

import java.util.HashMap;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/lru-cache/
 *
 */
public class LRUCache {

    private final int capacity;
    private HashMap<Integer, Integer> map = new HashMap<>();
    private LinkedList<Integer> keys = new LinkedList<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Integer r = map.get(key);
        if (r==null)
            return -1;
        keys.remove(Integer.valueOf(key));
        keys.addLast(key);
        return r;
    }

    public void set(int key, int value) {
        Integer r = map.get(key);
        if(r==null){
            if(map.size()==capacity){
                Integer first = keys.getFirst();
                map.remove(first);
                keys.removeFirst();
            }
        }else{
            keys.remove(Integer.valueOf(key));
        }
        keys.addLast(key);
        map.put(key, value);
    }

    public static void main(String[] args) {
        LRUCache lru = new LRUCache(3);
        lru.set(1, 1);
        lru.set(2, 2);
        lru.set(3, 3);
        lru.set(4,4);

        assertEquals(-1, lru.get(1));
        assertEquals(2, lru.get(2));
        assertEquals(3, lru.get(3));

        lru.set(2, 4);
        lru.set(5, 5);
        assertEquals(3, lru.get(3));



    }
}
