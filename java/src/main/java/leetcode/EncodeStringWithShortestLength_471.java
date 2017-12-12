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

        int[] best = new int[4];
        best[0] = Integer.MIN_VALUE;

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
                if(len*r<=4)
                    continue;

                int gain = r*len - (String.valueOf(r).length()+2+len);

                if (best[0]<gain){
                    best[0]=gain;
                    best[1]= i;
                    best[2]=pos;
                    best[3]=r;
                }
            }
        }

        if(best[0]!=Integer.MIN_VALUE){
            String repeat = s.substring(best[1],best[2]);
            String before = s.substring(0, best[1]);
            int pos = best[2];
            int len = best[2]-best[1];
            int r=best[3];
            String after="";
            if(pos+len*(r-1)<N)
                after = s.substring(pos+len*(r-1), N);
            return String.format("%s%d[%s]%s",before, r, repeat, after);
        }



        return s;
    }

    public String encode(String s) {
        while(true){
            String after = encodeInternal(s);
            if(after.equals(s)) return s;
            s=after;
        }
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
