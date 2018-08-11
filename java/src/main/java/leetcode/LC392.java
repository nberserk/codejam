package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class LC392 {
    public boolean isSubsequence(String s, String t) {
        if(s.length()>t.length()) return false;
        HashMap<Character, List<Integer>> map = new HashMap<>();

        for(int i=0;i<t.length();i++){
            char c = t.charAt(i);
            map.putIfAbsent(c, new ArrayList<>());
            map.get(c).add(i);
        }

        int prev=-1;
        for(char c:s.toCharArray()){
            if(!map.containsKey(c)) return false;
            List<Integer> pos = map.get(c);
            int np = Arrays.binarySearch(pos.toArray(new Integer[pos.size()]), 0,pos.size(), prev+1);
            if(np<0){
                prev = -np-1;
                if(prev>=pos.size())
                    return false;
                prev = pos.get(prev);
            }else
                prev=pos.get(np);
        }

        return true;
    }
    public boolean isSubsequence_1st(String s, String t) {
        if(s.length()>t.length()) return false;
        int si=0;
        int ti=0;
        while(si<s.length() && ti<t.length()){
            if(s.charAt(si)==t.charAt(ti)){
                si++;
                ti++;
            }else{
                ti++;
            }
        }
        if(si==s.length())
            return true;

        return false;
    }

    @Test
    public void test(){
        assertEquals(true, isSubsequence("abc","ahbgdc"));
        assertEquals(false, isSubsequence("axc","ahbgdc"));
        assertEquals(true, isSubsequence("affdfa","ahbgdcjfkdalfdklajfkdlsa"));
        assertEquals(true, isSubsequence_1st("affdfa","ahbgdcjfkdalfdklajfkdlsa"));
    }
}
