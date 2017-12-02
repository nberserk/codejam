package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;


public class ArraySubtractionGame {

    List<List<Integer>> subtractionGame(int[] n){
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Set<Integer> exist = new HashSet<>();
        for (int i: n){
            list.add(i);
            exist.add(i);
        }
        sub(ret, list, exist, new HashSet<Integer>());
        return  ret;
    }

    private void sub(List<List<Integer>> ret, List<Integer> list, Set<Integer> exist, Set<Integer> next) {
        int last = list.get(list.size()-1);

        for (int i = 0; i < list.size()-1; i++) {
            int v = Math.abs(list.get(i)-last);
            if(v==0 || exist.contains(v) || next.contains(v)) continue;

            next.add(v);
        }

        if (next.size()==0)
            ret.add(new ArrayList<>(list));
        else{

            for (int n: next){
                list.add(n);
                exist.add(n);
                Set<Integer> nnext = new HashSet<>(next);
                nnext.remove(n);
                sub(ret, list, exist, nnext);
                list.remove(list.size()-1);
                exist.remove(n);
            }
        }
    }

    @org.junit.Test
    public void test(){
        assertEquals("[[2, 7, 5, 3, 1, 4, 6], [2, 7, 5, 3, 1, 6, 4], [2, 7, 5, 3, 4, 1, 6]]", subtractionGame(new int[]{2, 7}).toString());
    }
}
