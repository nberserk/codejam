package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 16/02/2017.
 */
public class LC227 {


    List<String> tokenize(String s){
        List<String> r = new ArrayList<>();
        
        int start=-1;
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (c=='+' || c=='-' || c=='*' || c=='/'  ){
                if (start!=-1){
                    r.add(s.substring(start, i));
                    start=-1;
                }
                r.add(String.valueOf(c));
                
            }else if (c==' '){
                if (start!=-1){
                    r.add(s.substring(start, i));
                    start=-1;
                }                
            }else{
                if (start==-1){
                    start=i;
                }                
            }                
        }
        if(start!=-1)
            r.add(s.substring(start, s.length()));

        return r;
    }

    List<String> tokenize_regex(String s){
        s=s.replace(" " , "");
        List<String> r = new ArrayList<>();
        String[] words = s.split("(?=[-+*/])");
        for (String w: words){
            r.add(w);
        }
        return r;
    }

    int order(char c){        
        switch(c) {
            case '*':
            case '/':
                return 5;
            case '+':
            case '-':
                return 4;
            default:
                return -1;
        }
    }

    int calculate(String s){
        List<String> token = tokenize(s);
        Stack<Character> operator = new Stack<>();
        ArrayList<String> normalized = new ArrayList<>();
        for (int i = 0; i < token.size(); i++){
            String cur = token.get(i);
            int o = order(cur.charAt(0));
            if(o==-1)
                normalized.add(cur);
            else{
                while (!operator.isEmpty() &&  order(operator.peek())>= o ) {
                    normalized.add(String.valueOf(operator.pop()));
                }
                operator.push(cur.charAt(0));
            }
        }
        while(operator.size()>0){
            normalized.add(String.valueOf(operator.pop()));
        }

        Stack<Integer> stack = new Stack<>();
        for(String cur: normalized){
            int o = order(cur.charAt(0));
            if(o==-1) stack.push(Integer.valueOf(cur));
            else{
                int v=stack.pop();
                int v2 = stack.pop();
                switch (cur.charAt(0)){
                    case '+':
                        v2+=v;
                        break;
                    case '-':
                        v2-=v;
                        break;
                    case '*':
                        v2*=v;
                        break;
                    case '/':
                        v2/=v;
                        break;
                }
                stack.push(v2);
            }
        }
        return stack.peek();
    }

    @Test
    public void test(){

        assertSame(tokenize(" 3+ 4 * 7 -11"), tokenize_regex(" 3+ 4 * 7 -11"));
        assertEquals(27, calculate("100000000/1/2/3/4/5/6/7/8/9/10"));
        assertEquals(1, calculate(" 3/2"));
        assertEquals(7, calculate(" 3+2*2"));
        assertEquals(20, calculate(" 3+ 4 * 7 -11"));
    }
}
