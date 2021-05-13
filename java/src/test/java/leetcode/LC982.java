package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

public class LC982 {

    public int countTriplets(int[] A) {
        ArrayList<HashSet<Integer>> set = new ArrayList<>();
        for (int i = 0; i < 17; i++) {
            set.add(new HashSet<>());
        }
        int ret=0;

        int N=A.length;
        for (int i = 0; i < N; i++) {
            int t = A[i];
            if(t==0) ret++;
            for (int j = 0; j < 17; j++) {
                if( (t&1)==0) set.get(j).add(i);
                t = t>>1;
            }
        }

        for (int i = 0; i < N; i++) {
            // i,i
            int v = A[i];
            for (int j = i+1; j < N; j++) {
                if( (A[i]&A[j])==0)
                    ret +=6;
            }

            // i,j, k
            for (int j = i+1; j < N; j++) {
                for (int k = j+1; k < N; k++) {
                    if ( ((A[i]&A[j])&A[k])==0)
                        ret+=6;
                }
//                one = ones(A[i]&A[j]);
//                c = find(one, set, j);
//                ret += c*6;
            }
        }
        return ret;
    }

    private int find(ArrayList<Integer> one, ArrayList<HashSet<Integer>> set, int pivot) {
        int count=0;
        if(one.size()==0) return count;
        HashSet<Integer> candidate = set.get(one.get(0));
        for (int j = 0; j < one.size(); j++) {
            HashSet<Integer> cur = set.get(one.get(j));
            HashSet<Integer> merged = new HashSet<>();
            for (int c: cur){
                if(candidate.contains(c) && c > pivot)
                    merged.add(c);
            }
            candidate = merged;
            if(candidate.size()==0) return 0;
        }
        return candidate.size();
    }

    ArrayList<Integer> ones(int v){
        ArrayList<Integer> one = new ArrayList<>();
        int pos=0;
        while(v>0){
            if( (v&1)==1 ) one.add(pos);
            pos++;
            v=v>>1;
        }
        return one;
    }


    @Test
    public void test(){
        Assert.assertEquals(12 , countTriplets(new int[]{2,1,3}));
        Assert.assertEquals(132 , countTriplets(new int[]{2,1,3,6,7,8}));

    }
}
