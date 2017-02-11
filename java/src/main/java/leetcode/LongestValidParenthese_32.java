package main.java.leetcode;

import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 2/11/17.
 *
 * https://leetcode.com/problems/longest-valid-parentheses/?tab=Description
 * tags: stack, impl
 */
public class LongestValidParenthese_32 {

    public int longestValidParentheses(String s) {
        int N = s.length();
        if(N==0) return 0;

        char[] ch = s.toCharArray();
        Stack<Integer> st = new Stack<>();
        boolean[] valid = new boolean[N];
        int max =0;
        for(int i=0;i<N;i++){
            boolean open = ch[i]=='(' ? true: false;
            if(open){
                st.push(i);
            }else{
                if(st.size()>0 && ch[st.peek()]=='('){
                    valid[i]=true;
                    valid[st.pop()]=true;
                }
            }
        }

        int c=0;
        for(int i=0;i<N;i++){
            if(valid[i]){
                c++;
                max=Math.max(max, c);
            }else{
                c=0;
            }
        }

        return max;
    }

    @Test
    public void test(){
        assertEquals(2, longestValidParentheses("(()"));
        assertEquals(4, longestValidParentheses(")()())"));
    }


}
