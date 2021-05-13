package leetcode;


import static org.junit.Assert.assertEquals;


public class LC110 {
    int balance(TreeNode n, int depth){
        if(n==null) return depth;

        int l = balance(n.left, depth+1);
        int r = balance(n.right, depth+1);
        if(l==-1 || r==-1 || Math.abs(l-r)>1) return -1;
        return Math.max(l,r);
    }

    public boolean isBalanced(TreeNode root) {
        int r = balance(root, 0 );
        return r==-1?false:true;
    }

    @org.junit.Test
    public void test() {
        TreeNode root = new TreeNode(5);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(5);

        assertEquals(false, isBalanced(root));

        root.left = new TreeNode(1);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(5);

        assertEquals(true, isBalanced(root));
    }


}
