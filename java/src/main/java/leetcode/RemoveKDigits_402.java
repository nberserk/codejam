package leetcode;

import java.util.Stack;

import static org.junit.Assert.assertEquals;


public class RemoveKDigits_402 {

    public String removeKdigits(String num, int k) {
        if(num.length()<=k)
            return "0";

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            while (stack.size()>0 && k>0 && stack.peek()>c){
                stack.pop();
                k--;
            }
            stack.push(c);
        }
        String ret = "";
        while(k>0 && stack.size()>0){
            stack.pop();
            k--;
        }
        if(k>0) return "0";


        while(stack.size()>0){
            ret = stack.pop()+ret;
        }

        // remove leading zero
        int i;
        for (i = 0; i < ret.length(); i++) {
            if (ret.charAt(i)!='0')
                break;
        }
        if(i>0)
            ret=ret.substring(i, ret.length());

        if(ret.length()==0)
            return "0";
        return ret;
    }



    @org.junit.Test
    public void test(){
        assertEquals("200", removeKdigits("10200", 1));
        assertEquals("1219", removeKdigits("1432219", 3));
        assertEquals("0", removeKdigits("12340", 4));

    }
}
