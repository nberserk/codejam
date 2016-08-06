package main.java.codejam.lib;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 7/2/16.
 * https://leetcode.com/problems/palindrome-linked-list/
 */
public class PalindromeLinkedList {

    static class ListNode {

        int val;

        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    static public boolean isPalindrome(ListNode head) { // 1->2->2->1
        if (head==null) return true;

        ArrayList<Integer> a = new ArrayList<Integer>();
        ListNode cur = head;
        while(cur!=null){
            a.add(cur.val); // 1,2,2,1
            cur = cur.next;
        }

        int h = 0; // 0
        int t = a.size() -1; // 3
        while(h<t){
            if(a.get(h).intValue() != a.get(t).intValue() )
                return false;
            h++; //1 2
            t--; // 2 1
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode l1= new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(2);
        ListNode l4 = new ListNode(1);

        l1.next=l2;
        l2.next=l3;

        assertEquals(isPalindrome(l1), false);
        l3.next=l4;
        assertEquals(isPalindrome(l1), true);


        int[] a={-16557,-8725,-29125,28873,-21702,15483,-28441,-17845,-4317,-10914,-10914,-4317,-17845,-28441,15483,-21702,28873,-29125,-8725,-16557};
        ListNode l = makeList(a);

        assertEquals(isPalindrome(l), true);

    }

    public static ListNode makeList(int[] a){
        ListNode head = new ListNode(a[0]);
        ListNode prev = head, cur;

        for (int i = 1; i < a.length; i++) {
            cur = new ListNode(a[i]);
            prev.next=cur;
            prev=cur;
        }
        return head;
    }
}
