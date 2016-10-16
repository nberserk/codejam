package main.java.crackcode.stack;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 10/16/16.
 */
public class RemoveDuplicateLetters {

    public String removeDuplicateLetters(String s) {
        HashMap<Character, Integer> last = new HashMap<>();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            last.put(c, i);
        }

        Stack<Character> stack = new Stack<>();
        HashSet<Character> used = new HashSet<>();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(used.contains(c)) continue;

            while(stack.size()>0 && stack.peek()>c && last.get(stack.peek() )>i ){
                char top = stack.pop();
                used.remove(top);
            }
            if(!used.contains(c)){
                used.add(c);
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while(stack.size()>0){
            sb.append(stack.pop() );
        }
        return sb.reverse().toString();
    }

    @Test
    public void test(){
        assertEquals("abc", removeDuplicateLetters("bcabc"));
        assertEquals("acdb", removeDuplicateLetters("cbacdcbc"));
    }

}
