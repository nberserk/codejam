package main.java.crackcode.stack;

import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 11/30/16.
 *
 * https://leetcode.com/problems/decode-string/
 *
 */
public class DecodeString {

    public String decodeString(String s) {
        Stack<Integer> count = new Stack<>();
        Stack<String> result = new Stack<>();

        String cur="";
        int repeat=0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(Character.isDigit(ch)){
                repeat*=10;
                repeat+=ch-'0';
            }else if('['==ch){
                count.push(repeat);
                result.push(cur);
                repeat=0;
                cur="";
            }else if(']'==ch){
                int times = count.pop();
                String temp ="";
                for (int j = 0; j < times; j++) {
                    temp+=cur;
                }
                cur = result.pop()+temp;
            }else{
                cur+=ch;
            }
        }

        return cur;
    }

    @Test
    public void test(){
        assertEquals("aaabcbc", decodeString("3[a]2[bc]"));
        assertEquals("accaccacc", decodeString("3[a2[c]]"));
        assertEquals("abcabccdcdcdef", decodeString("2[abc]3[cd]ef"));
    }
}
