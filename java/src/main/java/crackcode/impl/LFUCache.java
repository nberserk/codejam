package main.java.crackcode.impl;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashSet;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 11/25/16.
 */
public class LFUCache {
    class Node{
        LinkedHashSet<Integer> keys = new LinkedHashSet<>();
        Node prev, next;
        int frequency;
        Node(int f){
            frequency=f;
        }
    }
    private int capacity;
    HashMap<Integer, Integer> values = new HashMap<>();
    HashMap<Integer, Node> nodes = new HashMap<>();
    Node head;

    void setCapacity(int capcity){
        this.capacity=capcity;
    }

    public int get(int key){
        if(!values.containsKey(key)) return -1;

        Node node = nodes.get(key);
        node.keys.remove(key);

        if (node.next==null){
            node.next = new Node(node.frequency+1);
            node.next.prev = node;

            node.next.keys.add(key);
        }else if (node.next.frequency == node.frequency+1){
            node.next.keys.add(key);
        }else{
            Node n = new Node(node.frequency+1);
            n.keys.add(key);
            Node nextnext = node.next;
            n.prev = node;
            n.next = nextnext;

            nextnext.prev = n;
            node.next = n;
        }

        nodes.put(key, node.next);
        if(0==node.keys.size()) {
            removeNode(node);
        }
        return values.get(key);
    }

    void removeOld(){
        if(head==null) return;
        int oldest = 0;
        for(int k:head.keys){
            oldest=k;
            break;
        }
        head.keys.remove(oldest);
        values.remove(oldest);
        nodes.remove(oldest);
        if(head.keys.size()==0){
            removeNode(head);
        }
    }

    private void removeNode(Node node) {
        if(node.prev!=null){
            node.prev.next = node.next;
        }else
            head = node.next; // do not forget update head

        if(node.next!=null)
            node.next.prev = node.prev;
    }

    public void set(int key, int value) {
        if(capacity==0) return;

        if(values.containsKey(key)){
            values.put(key, value);
            Node node = nodes.get(key);
            node.keys.remove(key);
            node.keys.add(key);
        }else{
            if(head==null){
                head = new Node(1);
            }
            head.keys.add(key);
            values.put(key, value);
            nodes.put(key, head);

            if(values.size()>capacity){
                removeOld();
            }
        }
    }

    @Test
    public void test(){
        LFUCache cache = new LFUCache();
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
