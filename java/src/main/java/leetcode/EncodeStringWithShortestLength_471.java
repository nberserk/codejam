package leetcode;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class EncodeStringWithShortestLength_471 {

    int repeat(String s, int from, int test){
        int r=0;
        int N = s.length();
        int org=from;
        int len =test-from;
        for (int i = 1; i < len; i++) {
            if (s.charAt(from+i)!= s.charAt(test+i))
                return 0;
        }
        r=2;

        while(true){
            boolean match=true;
            for (int i = 0; i < len; i++) {
                if (test+i+len*(r-1)>=N || s.charAt(from+i)!= s.charAt(test+i+len*(r-1))) {
                    match=false;
                    break;
                }
            }
            if(!match)break;
            r++;
        }
        return r;
    }

    String encodeInternal(String s){
        int N = s.length();
        HashMap<Character, List<Integer>> map = new HashMap<>();
        for (int i=0;i<N;i++){
            char ch = s.charAt(i);
            map.putIfAbsent(ch, new ArrayList<>());
            map.get(ch).add(i);
        }

        String min = new String(s);
        for (int i = 0; i < N; i++) {
            char ch = s.charAt(i);
            List<Integer> list = map.get(ch);
            if(list.size()<2) continue;

            for (int j = 0; j < list.size(); j++) {
                int pos = list.get(j);
                if(pos<=i) continue;
                int len = pos-i;
                if (pos+len>N) continue;
                int r = repeat(s, i, pos);
                if(r==0) continue;
                int gain = r*len - (String.valueOf(r).length()+2+len);
                if(gain>0){
                    String repeat = s.substring(i, pos);
                    String before = s.substring(0, i);
                    String after="";
                    if(pos+len*(r-1)<N)
                        after = s.substring(pos+len*(r-1), N);
                    String encoded = String.format("%s%d[%s]%s",before, r, repeat, after);
                    String more = encodeInternal(encoded);
                    if(min.length()>more.length())
                        min=more;
                }

            }
        }

        return min;
    }

    public String encode(String s) {
        return encodeInternal(s);
    }

    @org.junit.Test
    public void test(){
        assertEquals(6, repeat("aaaaaabfda", 0,1));
        assertEquals(2, repeat("dddabcdeabcdeddd", 3,8));

        assertEquals("5[a]", encode("aaaaa"));
        assertEquals("8[a]", encode("aaaaaaaa"));
        assertEquals("2[aabc]d", encode("aabcaabcd"));
        assertEquals("2[2[abbb]c]", encode("abbbabbbcabbbabbbc"));
        assertEquals("2[abaab]", encode("abaababaab"));
        assertEquals("5[a]2[5[a]bbb]", encode("aaaaaaaaaabbbaaaaabbb"));
    }
}
