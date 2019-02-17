package leetcode;

import org.junit.Test;

/**
 *
 */
public class LC993 {

    class Ret{
        TreeNode parent;
        int depth;
        Ret(TreeNode p, int d){
            parent=p;
            depth=d;
        }
    }
    public boolean isCousins(TreeNode root, int x, int y) {
        Ret r = traverse(root, null, x, 0);
        Ret r1 = traverse(root, null, y, 0);
        if(r==null || r1==null) return false;
        if(r.depth==r1.depth && r.parent!=r1.parent) return true;
        else return false;
    }
    Ret traverse(TreeNode node,TreeNode p, int target, int depth){
        if(node==null) return null;
        if(node.val==target){
            return new Ret(p, depth);
        }
        Ret r = traverse(node.left, node, target, depth+1);
        if(r==null)
            r = traverse(node.right, node, target, depth+1);
        return r;
    }

    @Test
    public void test(){

    }
}
