package leetcode;


import static org.junit.Assert.assertEquals;

public class LC549 {

    class Range{
        int prev;
        int from,to;
        int max;
    }

    Range longest(TreeNode n){
        if(n==null) return null;
        Range r = new Range();
        r.prev=n.val;
        r.from=r.to=n.val;
        r.max=1;
        Range left= longest(n.left);
        Range right=longest(n.right);

        Range cur=left;
        if(cur!=null){
            if(cur.prev+1==n.val){
                r.from=cur.from;
            }else if(n.val+1==cur.prev){
                r.to=cur.to;
            }
            r.max=Math.max(r.max, cur.max);
        }
        cur=right;
        if(cur!=null){
            if(cur.prev+1==n.val){
                r.from=Math.min(r.from,cur.from);
            }else if(n.val+1==cur.prev){
                r.to=Math.max(r.to, cur.to);
            }
            r.max=Math.max(r.max, cur.max);
        }
        r.max=Math.max(r.max, r.to-r.from+1);
        return r;
    }
    public int longestConsecutive(TreeNode root) {
        if(root==null) return 0;
        Range r =  longest(root);
        return r.max;
    }

    @org.junit.Test
    public void test() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(1);

        assertEquals(2, longestConsecutive(root));
    }
}
