package leetcode;

import org.junit.Test;

/**
 *
 */
public class LC776 {
    public TreeNode[] splitBST(TreeNode root, int V) {
        if(root==null) return new TreeNode[]{null,null};
        TreeNode[] r = new TreeNode[2];
        if(root.val<=V){
            r = splitBST(root.right, V);
            root.right=r[0];
            r[0]=root;
        }else {
            r = splitBST(root.left, V);
            root.left=r[1];
            r[1]=root;
        }
        return r;
    }

    @Test
    public void test(){

    }
}
