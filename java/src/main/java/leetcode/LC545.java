package leetcode;

import java.util.ArrayList;
import java.util.List;

public class _545 {
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<TreeNode> left = new ArrayList<>();
        List<TreeNode> right = new ArrayList<>();
        if (root == null) return new ArrayList<>();
        left.add(root);
        TreeNode l = root.left;
        while (l != null) {
            left.add(l);
            if (l.left != null)
                l = l.left;
            else l = l.right;
        }
        TreeNode r = root.right;
        while (r != null) {
            right.add(r);
            if (r.right != null)
                r = r.right;
            else r = r.left;
        }
        List<TreeNode> leaf = new ArrayList<>();
        traverse(root, leaf);

        if (left.size() > 0 && left.get(left.size() - 1) == leaf.get(0))
            leaf.remove(0);
        if (right.size() > 0 && right.get(right.size() - 1) == leaf.get(leaf.size() - 1))
            leaf.remove(leaf.size() - 1);

        List<Integer> ret = new ArrayList<>();
        for (TreeNode n : left) {
            ret.add(n.val);
        }
        for (TreeNode n : leaf) {
            ret.add(n.val);
        }
        for (int i = right.size() - 1; i >= 0; i--) {
            ret.add(right.get(i).val);
        }

        return ret;
    }

    void traverse(TreeNode n, List<TreeNode> leaf) {
        if (n == null) return;
        if (n.left == null && n.right == null) {
            leaf.add(n);
            return;
        }
        traverse(n.left, leaf);
        traverse(n.right, leaf);
    }


}
