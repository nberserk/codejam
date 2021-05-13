package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 *
 *

 Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

 Each element is either an integer, or a list -- whose elements may also be integers or other lists.

 Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

 Example 1:
 Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)

 Example 2:
 Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)

 *
 */

public class LC364 {

    class NestedInteger{
        List<NestedInteger> child = new ArrayList<>();
        int value;

        NestedInteger(int v){
            value=v;
        }

        NestedInteger(int[] array){
            for(int v: array){
                child.add(new NestedInteger(v));
            }
        }

        public boolean isInteger(){
            if (child.size()==0) return true;
            return false;
        }

        public List<NestedInteger> getList(){
            return child;
        }

        public int getInteger(){
            return value;
        }

    }

    public int depthSum(List<NestedInteger> nestedList) {
        List<int[]> list = new ArrayList<>();
        int maxdepth = traverse(nestedList, 0, list);

        int sum=0;
        for (int[] v: list){
            sum += v[0]*(maxdepth+1-v[1]);
        }
        return sum;
    }


    // return maxdepth
    int traverse(List<NestedInteger> list, int depth, List<int[]> collect){
        int maxdepth = 1;

        for (NestedInteger ni: list){
            if (ni.isInteger()){
                collect.add(new int[]{ni.getInteger(), depth});
            }else{
                maxdepth = Math.max(maxdepth, traverse(ni.getList(), depth + 1, collect));
            }
        }

        return maxdepth;
    }

    @Test
    public void test(){

        NestedInteger n1 = new NestedInteger(new int[]{1,1});
        NestedInteger n2 = new NestedInteger(new int[]{1,1});
        List<NestedInteger> n = new ArrayList<>();
        n.add(n1);
        n.add(new NestedInteger(2));
        n.add(n2);

        assertEquals(8, depthSum(n));
    }

    @Test
    public void test2(){
        List<NestedInteger> root = new ArrayList<>();
        root.add(new NestedInteger(1));

        NestedInteger leaf = new NestedInteger(6);
        NestedInteger mid = new NestedInteger(4);
        mid.getList().add(leaf);
        root.add(mid);

        assertEquals(8, depthSum(root));
    }
}
