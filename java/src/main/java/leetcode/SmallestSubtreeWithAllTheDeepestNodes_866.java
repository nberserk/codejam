package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class SmallestSubtreeWithAllTheDeepestNodes_866 {

    ArrayList<TreeNode> leaf = new ArrayList<>();
    HashMap<TreeNode, Integer> depth = new HashMap<>();

    void traverse(TreeNode n, HashMap<TreeNode, TreeNode> map, TreeNode parent, int d) {
        if (n == null) return;
        map.put(n, parent);
        depth.put(n, d);
        if (n.left == null && n.right == null) leaf.add(n);
        traverse(n.left, map, n, d + 1);
        traverse(n.right, map, n, d + 1);
    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        HashMap<TreeNode, TreeNode> pMap = new HashMap<>();
        traverse(root, pMap, null, 0);

        if (pMap.size() == 1) return root;

        ArrayList<TreeNode> deep = new ArrayList<>();
        int max = -1;
        for (TreeNode n : leaf) {
            if (depth.get(n) > max) {
                deep.clear();
                max = depth.get(n);
                deep.add(n);
            } else if (depth.get(n) == max) {
                deep.add(n);
            }
        }
        if (deep.size() == 1) return deep.get(0);

        ArrayList<TreeNode> parents = new ArrayList<>(); //first :deepest, last, root
        int deepest = 0;
        for (TreeNode n : deep) {
            TreeNode p = pMap.get(n);
            if (parents.size() == 0) {
                while (p != null) {
                    parents.add(p);
                    p = pMap.get(p);
                }
            } else {
                while (p != null) {
                    if (parents.contains(p)) {
                        int c = parents.indexOf(p);
                        deepest = Math.max(c, deepest);
                        break;
                    }
                    p = pMap.get(p);
                }
            }
        }

        return parents.get(deepest);
    }


    @Test
    public void test() {
//        Assert.assertEquals(true, makesquare(new int[]{1,2,3,4,5,6,7,8,9,10,5}));
    }
}
