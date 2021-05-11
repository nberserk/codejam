package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class FindDuplicateSubtrees_652 {

    List<TreeNode> result = new ArrayList<>();
    List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        HashMap<String, Integer> id = new HashMap<>();

        serialize(root, id);

        return result;
    }

    private String serialize(TreeNode node, HashMap<String, Integer> id) {
        if(node==null) return ".";

        String key = String.format("%s|%d|%s", serialize(node.left,id), node.val, serialize(node.right, id));

        id.putIfAbsent(key, 0);
        id.put(key, id.get(key)+1);
        if(id.get(key)==2)
            result.add(node);
        return key;
    }

    List<TreeNode> findDuplicateSubtrees_1(TreeNode root) {
        List<TreeNode> ret = new ArrayList<>();
        HashMap<Integer, List<TreeNode>> map = new HashMap<>();
        traverse( root, map);

        for (int d : map.keySet()){
            List<TreeNode> list = map.get(d);
            HashSet<Integer> visited = new HashSet<>();
            for (int i = 0; i < list.size(); i++) {
                TreeNode org = list.get(i);
                if(visited.contains(i)) continue;
                boolean found =false;
                for (int j = i+1; j < list.size(); j++) {
                    if(visited.contains(j)) continue;
                    TreeNode n = list.get(j);
                    if(org.val==n.val && isSame(org, n)){
                        visited.add(j);
                        if(!found)
                            ret.add(org);
                        found=true;
                    }
                }
            }
        }

        return ret;
    }

    private int traverse(TreeNode cur, HashMap<Integer, List<TreeNode>> map) {
        if(cur==null) return 0;

        int depth = Math.max(traverse(cur.left, map), traverse(cur.right, map));

        depth++;
        map.putIfAbsent(depth, new ArrayList<>());
        map.get(depth).add(cur);

        return depth;
    }


    boolean isSame(TreeNode org, TreeNode n){
        if(org==null && n==null) return true;
        if(org==n) return false;
        if(org==null || n==null || org.val!=n.val) return false;

        return isSame(org.left, n.left) && isSame(org.right, n.right);
    }


    @Test
    public void test(){

        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(1);
        //assertEquals( com.sun.tools.javac.util.List.of(root.left) , findDuplicateSubtrees(root).get(0));

    }
}
