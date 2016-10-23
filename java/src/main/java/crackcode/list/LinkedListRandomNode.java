package crackcode.list;

import org.junit.Test;

import java.util.Random;

/**
 * Created by darren on 2016. 10. 23..
 */
public class LinkedListRandomNode {
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val=x;
        }
    }

    ListNode head;
    Random random = new Random();
    /** @param head The linked list's head.
    Note that the head is guaranteed to be not null, so it contains at least one node. */
    public LinkedListRandomNode(ListNode head) {
        this.head=head;
    }

    /** Returns a random node's value. */
    public int getRandom() {
        ListNode result=null;
        ListNode cur=head;
        for(int i=1;cur!=null;i++){
            /*
            P(choosing first element) : 1/1 * (2-1)/2 * (3-1)/3 * (4-1)/4 * ... (N-1)/N = 1/N
            P(choosing ith element): 1/i * i/(i+1)* ... * (N-1)/N = 1/N
             */
            if(random.nextInt(i)==0 )
                result=cur;
            cur=cur.next;
        }
        return result.val;
    }

    @Test
    public void test(){


    }
}
