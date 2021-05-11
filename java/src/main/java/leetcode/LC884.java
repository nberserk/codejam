package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class _884 {
    public String decodeAtIndex(String S, int K) {
        K--;
        String cur="";
        long mul=0;
        for(int i=0;i<S.length();i++){
            char c = S.charAt(i);
            if(i==S.length() || ('a'<=c &&c<='z')){
                if(mul>0){
                    if(mul*cur.length()>(long)K){
                        int offset = K%cur.length();
                        return String.valueOf(cur.charAt(offset));
                    }else{
                        StringBuilder sb = new StringBuilder();
                        for(int j=0;j<mul;j++)
                            sb.append(cur);
                        cur=sb.toString();
                    }
                    mul=0;
                }
                cur=cur+c;
                //System.out.println(cur);
            }else{
                mul=mul*10+(int)(c-'0');
                //System.out.println(mul);
            }
        }
        if(mul>0){
            if(mul*cur.length()>(long)K){
                int offset = K%cur.length();
                return String.valueOf(cur.charAt(offset));
            }else{
                StringBuilder sb = new StringBuilder();
                for(int j=0;j<mul;j++)
                    sb.append(cur);
                cur=sb.toString();
            }
            mul=0;
        }

        return String.valueOf(cur.charAt(K));
    }

    @Test
    public void test(){
        assertEquals("z", decodeAtIndex("vzpp636m8y", 2920));
        assertEquals("o", decodeAtIndex("leet2code3", 10));
    }
}
