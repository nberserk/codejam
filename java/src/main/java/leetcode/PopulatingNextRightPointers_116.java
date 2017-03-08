package main.java.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by darren on 9/28/16.
 */
public class PopulatingNextRightPointers_116 {
    public class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;
        TreeLinkNode(int x) {
            val = x;
        }
    }

    class Node{
        TreeLinkNode node;
        int depth;
        Node(TreeLinkNode node, int d){
            this.node=node; depth=d;
        }
    }
    List<List<TreeLinkNode>> traverse(TreeLinkNode root){
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(root,0));
        List<List<TreeLinkNode>> list = new ArrayList<>();

        while(q.size()>0){
            Node cur = q.pollFirst();
            if (cur.node==null) continue;
            if(list.size()<=cur.depth ){
                List<TreeLinkNode> level = new ArrayList<>();
                list.add(level);
            }
            List<TreeLinkNode> thisLevel = list.get(cur.depth);
            thisLevel.add(cur.node);

            q.addLast(new Node(cur.node.left, cur.depth+1));
            q.addLast(new Node(cur.node.right, cur.depth+1));
        }
        return list;
    }
    public void connect_old(TreeLinkNode root) {
        if(root==null) return;
        List<List<TreeLinkNode>> list = traverse(root);
        for(List<TreeLinkNode> l: list){
            for(int i=0;i<l.size()-1;i++){
                l.get(i).next = l.get(i+1);
            }
        }
    }

    public void connect(TreeLinkNode root) {
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

    @Test
    public void test(){
        ArrayList<Integer> a = new ArrayList<>();
        a.ensureCapacity(11);
        a.set(10, 1);

        int t = a.get(2);
        t = a.get(10);

    }
}
