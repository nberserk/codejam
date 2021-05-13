package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 *
 *
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 *
 * Example 1:
 * Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)
 *
 */

public class LC339 {

    class NestedInteger{
        List<NestedInteger> list = new ArrayList<>();
        int value;

        NestedInteger(){}

        NestedInteger(int v){
            value=v;
        }

        NestedInteger(List<NestedInteger> initial){
            list.addAll(initial);
        }

        NestedInteger(int[] array){
            for(int v: array){
                list.add( new NestedInteger(v));
            }
        }

        public boolean isInteger(){
            if (list.size()==0) return true;
            return false;
        }

        public List<NestedInteger> getList(){
            return list;
        }

        public int getInteger(){
            return value;
        }

    }

    public int depthSum(List<NestedInteger> nestedList) {
        return traverse( nestedList, 1);
    }

    int traverse(List<NestedInteger> list, int depth){
        int sum = 0;

        for (NestedInteger ni: list){
            if (ni.isInteger()){
                sum += depth*ni.getInteger();
            }else{
                sum += traverse(ni.getList(), depth+1);
            }
        }
        return sum;
    }

    @Test
    public void test(){

        NestedInteger n1 = new NestedInteger(new int[]{1,1});
        NestedInteger n2 = new NestedInteger(new int[]{1,1});
        List<NestedInteger> n = new ArrayList<>();
        n.add(n1);
        n.add(new NestedInteger(2));
        n.add(n2);

        assertEquals(10, depthSum(n));


    }
}
