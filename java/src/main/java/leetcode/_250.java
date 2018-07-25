package leetcode;


import static org.junit.Assert.assertEquals;

/*

Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

Example :

Input:  root = [5,1,5,5,5,null,5]

              5
             / \
            1   5
           / \   \
          5   5   5

Output: 4


 */
public class _250 {
    int count=0;
    boolean count(TreeNode n){
        if(n==null) return true;
        boolean left = count(n.left);
        boolean right = count(n.right);
        if(left&&right){
            if(n.left!=null && n.left.val!=n.val)
                return false;
            if(n.right!=null && n.right.val!=n.val)
                return false;
            count++;
            return true;
        }
        return false;
    }
    public int countUnivalSubtrees(TreeNode root) {
        count(root);
        return this.count;
    }

    @org.junit.Test
    public void test() {
        TreeNode root = new TreeNode(5);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(5);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(5);

        assertEquals(4, countUnivalSubtrees(root));



    }


}
