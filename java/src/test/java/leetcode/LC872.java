package leetcode;


import java.util.ArrayList;
import java.util.List;

public class LC872 {

    void traverse(TreeNode n, List<Integer> leaf) {
        if (n == null) return;
        if (n.left == null && n.right == null) {
            leaf.add(n.val);
            return;
        }
        traverse(n.left, leaf);
        traverse(n.right, leaf);
    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leaf1 = new ArrayList<>();
        traverse(root1, leaf1);
        List<Integer> leaf2 = new ArrayList<>();
        traverse(root2, leaf2);
        if (leaf1.size() != leaf2.size()) return false;
        for (int i = 0; i < leaf1.size(); i++) {
            if (leaf1.get(i) != leaf2.get(i)) return false;
        }
        return true;
    }

    @org.junit.Test
    public void test() {
    }
}
