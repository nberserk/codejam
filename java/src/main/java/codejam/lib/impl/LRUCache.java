package main.java.codejam.lib.impl;

import java.util.HashMap;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/lru-cache/
 *
 */
public class LRUCache {

    static class Node{
        int key,value;
        Node prev,next;
    }

    private final int capacity;
    private HashMap<Integer, Node> map = new HashMap<>();
    Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)){
            Node node = map.get(key);
            unlinkeNode(node);
            appendLast(node);
            return node.value;
        }else
            return -1;
    }

    private void appendLast(Node node) {
        if(tail==null){
            head=tail=node;
            node.prev=node.next=null;
        }else{
            tail.next=node;
            node.prev=tail;
            node.next=null;
            tail=node;
        }
    }

    private void unlinkeNode(Node node) {
        if(node.prev!=null)
            node.prev.next = node.next;
        else
            head = node.next;

        if(node.next==null)
            tail = node.prev;
        else
            node.next.prev = node.prev;
    }

    public void set(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);

            unlinkeNode(node);
            node.value = value;
            appendLast(node);
        }else{
            if(map.size()==capacity){
                // remove head
                map.remove(head.key);
                unlinkeNode(head);
                //head = head.next;
            }
            Node node = new Node();
            node.key = key;
            node.value = value;

            appendLast(node);
            map.put(key, node);
        }
    }

    public static void main(String[] args) {
        LRUCache lru = new LRUCache(3);
        lru.set(1, 1);
        lru.set(2, 2);
        lru.set(3, 3);
        lru.set(4, 4);

        assertEquals(-1, lru.get(1));
        assertEquals(2, lru.get(2));
        assertEquals(3, lru.get(3));
        lru.set(2, 4);
        lru.set(5, 5);
        assertEquals(3, lru.get(3));

    }
}
