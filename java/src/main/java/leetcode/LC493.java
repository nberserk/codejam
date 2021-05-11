package main.java.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 2/12/17.
 * https://leetcode.com/contest/leetcode-weekly-contest-19/problems/reverse-pairs/
 *
 * tags: bst
 *
 * Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].

 You need to return the number of important reverse pairs in the given array.

 Example1:

 Input: [1,3,2,3,1]
 Output: 2
 Example2:

 Input: [2,4,3,5,1]
 Output: 3
 Note:
 The length of the given array will not exceed 50,000.
 All the numbers in the input array are in the range of 32-bit integer.

 */
public class ReversePairs_493 {

    public int reversePairs(int[] nums) {
        Node root = null;
        int r=0;
        for(int i = nums.length-1; i>=0; i--){
            r+=search(root, nums[i]/2.0);//search and count the partially built tree
            root = build(nums[i], root);//add nums[i] to BST
        }
        return r;
    }

    private int search(Node node, double target){
        if(node==null) return 0;
        else if(target == node.val) return node.less;
        else if(target < node.val) return search(node.left, target);
        else{
            return node.less + node.same +    search( node.right, target);
        }
    }

    private Node build(int val, Node n){
        if(n==null) return new Node(val);
        else if(val == n.val) n.same+=1;
        else if(val > n.val) n.right = build(val, n.right);
        else{
            n.less += 1;
            n.left = build(val, n.left);
        }
        return n;
    }

    class Node{
        int val, less = 0, same = 1;//less: number of nodes that less than this node.val
        Node left, right;
        public Node(int v){
            this.val = v;
        }
    }

    @Test
    public void test(){
        assertEquals(2, reversePairs(new int[]{1, 3,2,3,1}));
        assertEquals(9, reversePairs(new int[]{2147483647,2147483647,-2147483647,-2147483647,-2147483647,2147483647}));
    }
}
