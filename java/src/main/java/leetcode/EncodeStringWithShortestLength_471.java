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

    String encode_1(String s){
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
                    String more = encode_1(encoded);
                    if(min.length()>more.length())
                        min=more;
                }

            }
        }

        return min;
    }

    //dp(i,j)=dp(i,k)+dp(k,j)
    public String encode(String s) {
        int N = s.length();
        String[][] dp = new String[N][N];
        for (int l = 0; l < N; l++) {
            for (int i = 0; i < N - l; i++) {
                int to = i+l;
                String org = s.substring(i, to+1);
                dp[i][to] = org;
                if (dp[i][to].length() <=4) continue;

                String best=org;
                for (int k = i+1; k < to; k++) {
                    String temp = dp[i][k] + dp[k+1][to];
                    if(temp.length()<best.length())
                        best=temp;
                }

                // encode ?
                int len = org.length();

                for (int r = 0; r < org.length(); r++) {
                    //System.out.println(dp[i][to] + r);
                    String re = org.substring(0,r+1);
                    int repeat = len/re.length();
                    if (len%re.length()==0
//                            && r*repeat>4
                            && dp[i][to].replaceAll(re,"").length()==0
                            ){
                        String temp = String.format("%d[%s]", repeat, dp[i][i+r]);
                        if(temp.length()<best.length())
                            best=temp;
                    }
                }

                dp[i][to]=best;
            }
        }

        return dp[0][N-1];
    }

    @org.junit.Test
    public void test(){
        assertEquals(6, repeat("aaaaaabfda", 0,1));
        assertEquals(2, repeat("dddabcdeabcdeddd", 3,8));

//        assertEquals("5[a]", encode("aaaaa"));
        assertEquals("8[a]", encode("aaaaaaaa"));
        assertEquals("2[aabc]d", encode("aabcaabcd"));
        assertEquals("2[2[abbb]c]", encode("abbbabbbcabbbabbbc"));
        assertEquals("2[abaab]", encode("abaababaab"));
        assertEquals("5[a]2[5[a]bbb]", encode("aaaaaaaaaabbbaaaaabbb"));
    }
}
