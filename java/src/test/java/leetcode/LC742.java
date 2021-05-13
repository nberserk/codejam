package leetcode;

import static org.junit.Assert.assertEquals;

public class LC742 {


    int[] minLeaf(TreeNode n, int d){
        if(n==null) return new int[]{Integer.MAX_VALUE,0};
        if(n.left==null&&n.right==null) return new int[]{d, n.val};

        int[] l = minLeaf(n.left, d+1);
        int[] r = minLeaf(n.right, d+1);

        if(l[0]<r[0])
            return l;
        else return r;
    }

    int ans;
    int distance;
    void traverse(TreeNode n, int k, int parent_min, int parent_v){
        if(n==null) return;
        int[] v = minLeaf(n,0);
        if(n.val==k){
            if(v[0]<parent_min){
                distance=v[0];
                ans=v[1];
            }else{
                ans=parent_v;
            }
        }else{
            traverse(n.left, k, v[0]+1, v[1]);
            traverse(n.right, k, v[0]+1,v[1]);
        }
    }

    public int findClosestLeaf(TreeNode root, int k) {
        distance=Integer.MAX_VALUE;
        traverse(root, k, Integer.MAX_VALUE, -1);
        return ans;
    }



    @org.junit.Test
    public void test(){
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.left.left = new TreeNode(5);
        root.left.left.left.left = new TreeNode(6);

        assertEquals(3, findClosestLeaf(root, 2));
        assertEquals(1, findClosestLeaf(new TreeNode(1), 1));
//        assertEquals(false, isOneBitCharacter(new int[]{1,1,1,0}));
    }
}
