package leetcode;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class LC687 {

    int ans;
    public int longestUnivaluePath(TreeNode root) {
        ans=0;
        arrowLength(root);
        return ans;
    }

    int arrowLength(TreeNode node) {
        if(node==null) return 0;

        int left = arrowLength(node.left);
        int right = arrowLength(node.right);
        int L =0, R=0;
        if(node.left!=null && node.left.val == node.val)
            L = left+1;
        if(node.right!=null && node.right.val == node.val)
            R = right+1;

        ans=Math.max(ans, L+R);
        return Math.max(L, R);
    }

    public int longestUnivaluePath_1(TreeNode root) {
        HashSet<TreeNode> v= new HashSet<>();
        return traverse(root, v);
    }

    private int traverse(TreeNode root, HashSet<TreeNode> visited) {
        if(root==null) return 0;

        int ret=0;
        if (!visited.contains(root)){
            int left = maxDepth(root.left, root.val, visited);
            int right = maxDepth(root.right, root.val, visited);
            ret = Math.max(ret,  left+right);
        }

        ret = Math.max(ret, traverse(root.left, visited));
        ret = Math.max(ret, traverse(root.right, visited));
        return ret;
    }

    int maxDepth(TreeNode root, int target, Set<TreeNode> visited) {
        if(root==null || root.val!=target) return 0;
        visited.add(root);

        int left = maxDepth(root.left, target, visited);
        int right = maxDepth(root.right, target, visited);
        return  1+ Math.max(left,right);
    }

    @org.junit.Test
    public void test(){
        TreeNode root = new TreeNode(5);
        root.right = new TreeNode(5);
        root.left = new TreeNode(5);
        assertEquals(2, longestUnivaluePath(root));
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(4);
        root.right.right.right.right = new TreeNode(4);
        root.right.right.right.right.right = new TreeNode(4);
        root.right.right.right.right.right.right = new TreeNode(4);

        assertEquals(4, longestUnivaluePath(root));
    }
}
