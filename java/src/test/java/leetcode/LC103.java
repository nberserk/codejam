package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class LC103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        ArrayList<TreeNode> queue = new ArrayList<>();
        if(root!=null)
            queue.add(root);

        List<List<Integer>> ret = new ArrayList<>();
        boolean reverse=false;
        while(queue.size()>0){
            ArrayList<TreeNode> next = new ArrayList<>();
            ArrayList<Integer> row = new ArrayList<>();
            for(int i=0;i<queue.size();i++){
                TreeNode n = queue.get(i);
                if(n.left!=null)
                    next.add(n.left);
                if(n.right!=null)
                    next.add(n.right);
                row.add(n.val);
            }

            //
            if(reverse)
                Collections.reverse(row);
            ret.add(row);
            reverse=!reverse;
            queue=next;
        }
        return ret;
    }


}
