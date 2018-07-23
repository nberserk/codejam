package leetcode;


import static org.junit.Assert.assertTrue;

public class _814 {
    boolean hasOne(TreeNode n){
        if(n==null)return false;
        boolean l=hasOne(n.left);
        boolean r=hasOne(n.right);
        if(!l) n.left=null;
        if(!r) n.right=null;

        return l || r || n.val==1;
    }
    public TreeNode pruneTree(TreeNode root) {
        hasOne(root);
        return root;
    }

    @org.junit.Test
    public void test() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(0);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(1);

        pruneTree(root);
        assertTrue(root.right.left==null);
    }
}
