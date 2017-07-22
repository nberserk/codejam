package crackcode.design;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
  *
 * http://www.geeksforgeeks.org/stack-set-2-infix-to-postfix/
  *
 */
public class Calculator {


    int order(char ch){
        switch (ch){
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            case '(':
            case ')':
                return 4;

        }
        return -1;
    }

    int infixToPostfix(String expression){
        ArrayList<String> normalized = new ArrayList<>();

        String[] words = expression.split("(?=[-+()*/])");
        System.out.println(Arrays.toString(words));
        Stack<Character> stack = new Stack<>();
        for (String s: words){
            boolean hasOp = order(s.charAt(0))!=-1;
            if(hasOp){
                char op = s.charAt(0);
                String v = s.substring(1);

                if(op=='('){
                    stack.push(op);
                }else if(op==')'){
                    while(stack.peek()!='('){
                        normalized.add(String.valueOf(stack.pop()));
                    }
                    if(v.length()>0)
                        normalized.add(v);
                }else{
                    while(stack.size()>0 && order(op) <= order(stack.peek())){
                        normalized.add( String.valueOf(stack.pop()));
                    }
                    stack.push(op);

                    if(v.length()>0)
                        normalized.add(v);
                }
            }else{
                normalized.add(s);
            }
        }

        while(stack.size()>0)
            normalized.add(String.valueOf(stack.pop()));


        //String[] operators = {"*","/","+","-"};
        int re = 0;
        Stack<Integer> is = new Stack<>();
        for (String s: normalized){
            if(Character.isDigit(s.toCharArray()[0])){
                is.add(Integer.valueOf(s));
            }else{
                int a = is.pop();
                int b = is.pop();
                if ("*".equals(s)){
                    is.push(a*b);
                }else if("+".equals(s)){
                    is.push(a+b);
                }else if("-".equals(s))
                    is.push(a-b);
                else if("/".equals(s))
                    is.push(a/b);
            }
        }
        assert is.size()==1;

        return is.pop();
    }

    @Test
    public void test(){
        assertEquals("[4, +9, *10, +7]", Arrays.toString("4+9*10+7".split("(?=[-+()*/])")));
//        assertEquals("[4, +9, *10, +7]", Arrays.toString("4+9*(10+7)".split("(?=[-+()*/])")));
//        assertEquals("[4, +9, *10, +7]", Arrays.toString("(4+9)*10+7".split("(?=[-+()*/])")));
        assertEquals(101, infixToPostfix("4+9*10+7"));
    }
}
