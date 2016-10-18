package main.java.crackcode.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 10/17/16.
 *
 * problem:
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 *
 * explanation:
 * https://discuss.leetcode.com/topic/31405/9ms-short-java-bst-solution-get-answer-when-building-bst
 *
 * idea:
 * make tree with
 */
public class CountSmallerNumberAfterSelf {
    static class Node{
        Node left, right;
        int sum; // # smaller number under children
        int duplicate, val;
        Node(int val){
            this.val = val;
            duplicate =1;
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        Node root=null;

        List<Integer> ret = new ArrayList<>();

        for (int i = nums.length-1; i >=0 ; i--) {
            root = insert(nums[i], root, 0, ret);
        }

        List<Integer> reverse = new ArrayList<>();
        for (int i = ret.size()-1; i >=0 ; i--) {
            reverse.add(ret.get(i));
        }
        return reverse;
    }

    private Node insert(int num, Node node, int sum, List<Integer> ret) {
        if(node==null){
            node = new Node(num);
            ret.add(sum);
        }else if(node.val > num){
            node.sum++;
            node.left = insert(num, node.left, sum, ret);
        }else if (node.val < num){
            node.right = insert(num, node.right, sum+node.sum+node.duplicate, ret);
        }else{
            node.duplicate++;
            ret.add( sum + node.sum);
        }
        return node;
    }

    @Test
    public void test(){
        int[] a = {5, 2, 6, 1};
        assertEquals("[2, 1, 1, 0]", countSmaller(a).toString());
    }
}
