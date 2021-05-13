package leetcode;

import static org.junit.Assert.assertEquals;

public class LC856 {

    public int scoreOfParentheses(String S) {
        int ret=0;
        int depth=0;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (c=='('){
                depth++;
            }else {
                if (S.charAt(i-1)=='('){
                    ret += (int)Math.pow(2, depth-1);
                }
                depth--;
            }
        }

        return ret;
    }


    @org.junit.Test
    public void test(){

        assertEquals(3, scoreOfParentheses("()()()"));
        assertEquals(1, scoreOfParentheses("()"));
        assertEquals(2, scoreOfParentheses("(())"));
        assertEquals(2, scoreOfParentheses("()()"));
        assertEquals(6, scoreOfParentheses("(()(()))"));

    }
}
