package leetcode;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LC524 {

    boolean isSub(String d, String s){
        int si=0;
        for (int i = 0; i < d.length(); i++) {
            while(si<s.length() && d.charAt(i) != s.charAt(si))
                si++;
            if(si==s.length()) return false;

        }
        return true;
    }

    String findLongestWord(String s, List<String> d) {

        String ret ="";

        for (String dic: d){
            if(isSub(dic, s)){
                if(dic.length()> ret.length() || (dic.length()==ret.length()&& dic.compareTo(ret)<0))
                    ret=dic;
            }
        }
        return ret;
    }



    @org.junit.Test
    public void test(){
        assertEquals(true, isSub("apple", "addpzzpddlje"));
        assertEquals(true, isSub("d", "abd"));
        assertEquals(false, isSub("z", "abd"));

        List<String> list = new ArrayList<>();
        list.add("ale");
        list.add("apple");
        list.add("monkey");
        list.add("plea");
        assertEquals("apple", findLongestWord("abpcplea", list));
    }
}
