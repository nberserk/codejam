package leetcode;

import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class LC653 {

    public boolean findTarget(TreeNode root, int k) {
        HashSet<Integer> set = new HashSet<>();
        return traverse(root, set, k);
    }

    private boolean traverse(TreeNode n, HashSet<Integer> set, int k) {
        if(n==null ) return false;
        int target = k-n.val;
        if(set.contains(target)) return true;
        set.add(n.val);

        return traverse(n.left,set,k) || traverse(n.right,set,k);
    }

    @Test
    public void test(){
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(6);
        root.right.right = new TreeNode(7);

        assertEquals(true, findTarget(root,7));
    }
}
