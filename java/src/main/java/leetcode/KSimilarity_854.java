package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class KSimilarity_854 {

    int sub(String A, String B, HashMap<Character, List<Integer>> map){
        if(A.equals(B)) return 0;
        int ret = Integer.MAX_VALUE;

        ArrayList<Integer> mismatch = new ArrayList<>();
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) != B.charAt(i)) mismatch.add(i);
        }
        if (mismatch.size()==2) return 1;

        // search 2 sub
        for (int i = 0; i < mismatch.size(); i++) {
            int pos = mismatch.get(i);

            for (int idx: map.get(A.charAt(pos))){
                if (A.charAt(idx)==B.charAt(pos)){
                    char[] a = A.toCharArray();
                    char t = a[pos];
                    a[pos]=a[idx];
                    a[idx]=t;
                    //System.out.println("before:"+A+", after:"+ Arrays.toString(a));
                    ret = Math.min(ret, 1+sub(new String(a),B,map));
                    return ret;
                }
            }
        }

        for (int i = 0; i < mismatch.size(); i++) {
            int pos = mismatch.get(i);
            char tobe = B.charAt(pos);

            for (int j = 0; j < A.length(); j++) {
                if (A.charAt(j)!= B.charAt(j)
                        && A.charAt(j)==tobe){
                    char[] a = A.toCharArray();
                    char t = a[pos];
                    a[pos]=a[j];
                    a[j]=t;
                    //System.out.println("before:"+A+", after:"+ Arrays.toString(a));
                    ret = Math.min(ret, 1+sub(new String(a), B, map) );
                }
            }
        }

        return ret;
    }

    public int kSimilarity(String A, String B) {

        HashMap<Character, List<Integer>> map = new HashMap<>();
        for (int i=0;i<B.length();i++){
            char c = B.charAt(i);
            map.putIfAbsent(c, new ArrayList<>());
            map.get(c).add(i);
        }

        return sub(A,B,map);
    }



    @org.junit.Test
    public void test(){
        assertEquals(1, kSimilarity("ab", "ba"));
        assertEquals(2, kSimilarity("abc", "bca"));
        assertEquals(2, kSimilarity("aabc", "abca"));
    }
}
