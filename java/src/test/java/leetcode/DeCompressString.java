package leetcode;

import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 *
 *
 *
 *

 Given a compressed string in which a number followed by [] indicate how many times those characters occur, decompress the string
 Eg. : a3[b2[c1[d]]]e will be decompressed as abcdcdbcdcdbcdcde.
 Assume the string is well formed and number will always be followed by a [].

 */
public class DeCompressString {

    int countDigit(String in, int start){
        int N = in.length();
        int count=0;
        while(start<N && Character.isDigit(in.charAt(start))){
            start++;
            count++;
        }

        return count;
    }

    int countChar(String in, int start){
        int N = in.length();
        int count=0;
        while(start<N && Character.isLetter(in.charAt(start))){
            start++;
            count++;
        }

        return count;
    }

    class State{
        String str="";
        int multiple=-1;
    }


    String decompress(String in){

        State state = new State();
        Stack<State> stack = new Stack<>();

        for(int i=0;i<in.length();i++){
            int len = countChar(in, i);
            state.str += in.substring(i, i+len) ;
            i+=len;
            len = countDigit(in, i);
            if(len>0)
                state.multiple = Integer.valueOf(in.substring(i, i+len));
            i+=len;
            if(i>=in.length()) break;
            char c = in.charAt(i);
            if(c=='['){
                stack.push(state);
                state = new State();
            }else if(c==']'){
                State prev = stack.pop();
                String temp = state.str;
                state.str = prev.str;
                for (int j = 0; j < prev.multiple; j++) {
                    state.str +=  temp;
                }
            }
        }

        return state.str;
    }


    @Test
    public void test(){
        assertEquals("abbbe", decompress("a3[b]e"));
        assertEquals("abcdcdbcdcdbcdcde", decompress("a3[b2[c1[d]]]e"));
    }
}
