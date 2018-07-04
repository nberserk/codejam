package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AllNodesDistanceKInBinaryTree_863 {

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        // fill parent
        HashMap<TreeNode,TreeNode> parent = new HashMap<>();
        fillParent(root, parent, null);


        // child
        List<Integer> ret = new ArrayList<>();
        find(target, K, ret);

        // upper
        TreeNode cur = target;
        while(true){
            TreeNode p = parent.get(cur);
            if(p==null) break;
            K--;
            if(K==0){
                ret.add(p.val);
                break;
            }
            if(p.left==cur)
                find(p.right,K-1, ret);
            else
                find(p.left, K-1, ret);
            cur=p;
        }
        return ret;
    }

    private void find(TreeNode target, int k, List<Integer> ret) {
        if(target==null) return;
        if(k==0) {
            ret.add(target.val);
            return;
        }
        find(target.left,k-1, ret);
        find(target.right,k-1, ret);
    }

    private void fillParent(TreeNode root, HashMap<TreeNode, TreeNode> map, TreeNode parent) {
        if(root==null) return;

        if(parent!=null) map.put(root, parent);
        fillParent(root.left,map, root);
        fillParent(root.right,map, root);
    }

    @org.junit.Test
    public void test(){

//        assertEquals(true, isOneBitCharacter(new int[]{1,0,0}));
//        assertEquals(false, isOneBitCharacter(new int[]{1,1,1,0}));
    }
}
