package leetcode;

import leetcode.common.ListNode;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class LC817 {

    public int numComponents(ListNode head, int[] G) {
        HashSet<Integer> g = new HashSet<>();
        for(int i:G){
            g.add(i);
        }

        ListNode cur = head;
        int ret=0;
        while(cur!=null){
            if(g.contains(cur.val)&& (cur.next==null || !g.contains(cur.next.val) )){
                ret++;
            }
            cur=cur.next;
        }
        return ret;
    }

    void dfs(HashMap<Integer,Set<Integer>> map, int n, Set<Integer> v, Set<Integer> p){
        if(!p.contains(n)) return;
        p.remove(n);
        if(map.containsKey(n)){
            for(int next: map.get(n)){
                dfs(map, next, v,p);
            }
        }
    }
    public int numComponents_1st(ListNode head, int[] G) {
        HashMap<Integer,Set<Integer>> map = new HashMap<>();
        ListNode prev=head;
        ListNode n=head.next;
        while(n!=null){
            map.putIfAbsent(prev.val, new HashSet<>());
            map.putIfAbsent(n.val, new HashSet<>());
            map.get(prev.val).add(n.val);
            map.get(n.val).add(prev.val);

            prev=n;
            n=n.next;
        }

        HashSet<Integer> v = new HashSet<>();
        int ret=0;
        HashSet<Integer> possi=new HashSet<>();
        for(int i : G){
            possi.add(i);
        }
        for(int i : G){
            if(possi.contains(i)){
                dfs(map, i,v, possi);
                ret++;
            }
        }

        return ret;
    }

    @Test
    public void test(){
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        assertEquals(2, numComponents(head, new int[]{0,1,3}));
    }
}
