package main.java.leetcode;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 9/28/16.
 *
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node
 *
 */
public class PopulatingNextRightPointers_116 {
    public class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;
        TreeLinkNode(int x) {
            val = x;
        }
    }

    public void connect_second(TreeLinkNode root) {
        if(root==null) return;
        LinkedList<TreeLinkNode> q = new LinkedList<>();
        q.add(root);

        while(q.size()>0){
            int size = q.size();
            TreeLinkNode prev=null;
            for(int i=0;i<size;i++){
                TreeLinkNode cur = q.pollFirst();
                if(prev!=null)
                    prev.next = cur;
                if(cur.left!=null)
                    q.addLast(cur.left);
                if(cur.right!=null)
                    q.addLast(cur.right);
                prev=cur;
            }
        }
    }

    public void connect(TreeLinkNode root) {
        if(root==null || root.left==null) return;
        if(root.left!=null){
            root.left.next=root.right;
        }
        if(root.next!=null){
            root.right.next = root.next.left;
        }
        connect(root.left);
        connect(root.right);
    }

    @Test
    public void test(){
        TreeLinkNode root = new TreeLinkNode(4);
        root.left = new TreeLinkNode(6);
        root.right = new TreeLinkNode(9);

        connect(root);

        assertEquals(root.right, root.left.next);
    }
}
