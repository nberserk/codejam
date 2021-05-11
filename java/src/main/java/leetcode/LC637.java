package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class AverageLevelsInBinaryTree_637 {

    int traverse(TreeNode node, long[] v,int[] c, int depth){
        if(node==null) return 0;

        v[depth] += node.val;
        c[depth] ++;

        return 1 + traverse(node.left, v,c, depth+1) + traverse(node.right, v,c,depth+1);
    }

    public List<Double> averageOfLevels(TreeNode root) {
        int N = 10000;
        long[] v = new long[N];
        int[] c = new int[N];

        int n = traverse(root, v, c, 0);
        List<Double> r = new ArrayList<>();
        for(int i=0;i<n;i++){
            double d = (double)v[i]/c[i];
            r.add(d);
        }
        return r;
    }


    @org.junit.Test
    public void test(){
//        assertEquals(167, );
    }
}
