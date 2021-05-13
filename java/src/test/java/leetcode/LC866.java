package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class LC866 {

    ArrayList<TreeNode> leaf = new ArrayList<>();
    HashMap<TreeNode, Integer> depth = new HashMap<>();
    HashMap<TreeNode, TreeNode> pMap = new HashMap<>();
    int maxDepth = -1;

    void traverse(TreeNode n, HashMap<TreeNode, TreeNode> map, TreeNode parent, int d) {
        if (n == null) return;
        map.put(n, parent);
        depth.put(n, d);
        if (n.left == null && n.right == null) leaf.add(n);
        traverse(n.left, map, n, d + 1);
        traverse(n.right, map, n, d + 1);
    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {

        traverse(root, pMap, null, 0);

        if (pMap.size() == 1) return root;

        ArrayList<TreeNode> deep = new ArrayList<>();

        for (TreeNode n : leaf) {
            if (depth.get(n) > maxDepth) {
                deep.clear();
                maxDepth = depth.get(n);
                deep.add(n);
            } else if (depth.get(n) == maxDepth) {
                deep.add(n);
            }
        }
        if (deep.size() == 1) return deep.get(0);


        return commonAncestor_refined(root);
    }

    TreeNode commonAncestor_refined(TreeNode node) {
        if (node == null) return null;
        if (depth.get(node) == maxDepth) return node;

        TreeNode l = commonAncestor_refined(node.left);
        TreeNode r = commonAncestor_refined(node.right);

        if (l != null && r != null) return node;
        if (l != null) return l;
        if (r != null) return r;
        return null;
    }

    TreeNode commonAncestor(List<TreeNode> deep) {
        LinkedList<TreeNode> upper = new LinkedList<>();
        int N = deep.size();
        for (TreeNode tn : deep) {
            upper.addLast(tn);
        }
        while (true) {
            List<TreeNode> temp = new ArrayList<>();
            for (TreeNode tn : upper) {
                temp.add(pMap.get(tn));
            }
            upper.addAll(temp);
            for (int i = 0; i < N; i++) {
                upper.removeFirst();
            }

            boolean same = true;
            for (int i = 0; i < N - 1; i++) {
                if (upper.get(i) != upper.get(i + 1)) {
                    same = false;
                    break;
                }
            }
            if (same) return upper.getLast();
        }
    }


    @Test
    public void test() {
//        Assert.assertEquals(true, makesquare(new int[]{1,2,3,4,5,6,7,8,9,10,5}));
    }
}
