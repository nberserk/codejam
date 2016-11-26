package main.java.crackcode.impl;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 11/25/16.
 */
public class LFUCacheOld {
    private int capacity;
    HashMap<Integer, Item> map = new HashMap<>();
    FreqItem freqHead, freqTail;

    class Item{
        Item prev,next;
        int key, value;
        FreqItem freqItem;
        Item(int k, int v){
            key=k;
            value=v;
        }
    }

    class FreqItem{
        int freq;
        FreqItem prev,next;
        Item head, tail;
        FreqItem(int freq){
            this.freq=freq;
        }
    }

    void setCapacity(int capcity){
        this.capacity=capcity;
    }

    public int get(int key) {
        Item item = map.get(key);
        if(item==null) return -1;

        // unlink
        if(item.prev==null){
            item.freqItem.head = item.next;
        }else
            item.prev.next = item.next;

        if(item.next==null)
            item.freqItem.tail = item.prev;
        else
            item.next.prev = item.prev;


        // link
        FreqItem next = item.freqItem.next;
        if (next==null){
            next = new FreqItem(item.freqItem.freq+1);
            next.prev = item.freqItem;
            item.freqItem.next = next;
        }
        if(next.tail==null){
            next.head = next.tail = item;
            item.prev = item.next = null;
        }else{
            next.tail.next = item;
            item.prev = next.tail;
            item.next = null;
            next.tail = item;
        }

        return item.value;
    }

    public void set(int key, int value) {
        if(map.containsKey(key)){
            Item i = map.get(key);
            i.value=value;
        }else{
            if(freqHead==null){
                freqHead = new FreqItem(1);
                freqTail = freqHead;
            }

            Item item = new Item(key, value);
            item.freqItem = freqHead;
            map.put(key, item);

            if (freqHead.tail==null){
                freqHead.head =item;
                freqHead.tail=item;
            }else{
                freqHead.tail.next = item;
                item.prev = freqHead.tail;
                freqHead.tail = item;
            }
        }

        if(map.size()>capacity){
            Item old = freqHead.head;
            freqHead.head = freqHead.head.next;
            if(freqHead.head!=null)
                freqHead.head.prev= null;
            map.remove(old.key);
        }
    }

    @Test
    public void test(){

        LFUCacheOld cache = new LFUCacheOld();
        cache.setCapacity(2);
        cache.set(1, 1);
        cache.set(2, 2);
        assertEquals(1, cache.get(1));
        cache.set(3, 3);
        assertEquals(-1, cache.get(2));
        assertEquals(3, cache.get(3));
        cache.set(4, 4);
        assertEquals(-1, cache.get(1));
        assertEquals(3, cache.get(3));
        assertEquals(4, cache.get(4));

    }
}
