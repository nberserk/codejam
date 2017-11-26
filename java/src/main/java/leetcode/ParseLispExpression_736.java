package leetcode;

import java.util.HashMap;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class ParseLispExpression_736 {


    class State{
        State parent;
        HashMap<String, Integer> map = new HashMap<>();
        int val;
        int pos;
    }

    public int evaluate(String expression) {

        return eval(expression, 0,null).val;
    }

    String token(String in, int start){
        String cur="";
        int N = in.length();
        if(in.charAt(start)==')') return ")";
        while(start<N && in.charAt(start)!=' ' && in.charAt(start)!=')'){
            cur+=in.charAt(start);
            start++;
        }
        return cur;
    }

    boolean isNumber(String s){
        try{
            int v = Integer.valueOf(s);
        }catch (NumberFormatException ne){
            return false;
        }

        return true;
    }

    State eval(String ex, int start, State parent){
        State ret = new State();
        ret.parent=parent;

        int pos=start+1;
        int N = ex.length();
        String cmd = token(ex, pos);
        if(cmd.equals("let")){
            pos += 4;
            int count=0;

            LinkedList<String> pair = new LinkedList<>();
            while(pos<N){
                if(ex.charAt(pos)==' ') pos++;
                String t = token(ex, pos);
                pos += t.length();
                if(t.equals(")")) {
                    if(pair.size()>0)
                        ret.val = resolve(ret, pair.getFirst());
                    ret.pos=pos;
                    return ret;
                }
                if(t.contains("(")){
                    State tmp = eval(ex, pos-t.length(), ret);
                    if(pair.size()>0) {
                        ret.map.put(pair.getFirst(), tmp.val);
                        pair.removeFirst();
                    }else
                        ret.val=tmp.val;
                    pos=tmp.pos;
                }else{
                    if (pair.size()==0){
                        pair.add(t);
                    }else{
                        ret.map.put(pair.getFirst(), resolve(ret, t));
                        pair.removeFirst();
                    }
                }
            }
        }else if(cmd.equals("add")){
            pos +=4;
            int v=0;
            for (int i = 0; i < 3; i++) { // including token
                if(ex.charAt(pos)==' ') pos++;
                String t = token(ex, pos);
                pos+=t.length();
                if(t.contains("(")){
                    State tmp = eval(ex, pos-t.length(), ret);
                    v+=tmp.val;
                    pos = tmp.pos;
                }else if (t.equals(")")){
                    ret.val=v;
                    ret.pos=pos;
                    return ret;
                }else{
                    if(isNumber(t))
                        v+=Integer.valueOf(t);
                    else
                        v+= resolve(ret, t);
                }
            }
        }else if(cmd.equals("mult")){
            pos +=5;
            int v=0;
            for (int i = 0; i < 3; i++) { // including token
                if(ex.charAt(pos)==' ') pos++;
                String t = token(ex, pos);
                pos+=t.length();
                if(t.contains("(")){
                    State tmp = eval(ex, pos-t.length(), ret);
                    if(i==0)
                        v=tmp.val;
                    else v*=tmp.val;
                    pos = tmp.pos;
                }else if (t.equals(")")){
                    ret.val=v;
                    ret.pos=pos;
                    return ret;
                }else {
                    if(i==0)
                        v= resolve(ret, t);
                    else v*=resolve(ret, t);
                }
            }
            return ret;
        }

        return ret;
    }

    int resolve(State s, String key){
        if(isNumber(key))
            return Integer.valueOf(key);
        while(s!=null){
            if(s.map.containsKey(key))
                return s.map.get(key);
            s=s.parent;
        }
        return -1;
    }


    @org.junit.Test
    public void test(){

        assertEquals(-12, evaluate("(let x 7 -12)"));
        assertEquals(6, evaluate("(let x 2 (add (let x 3 (let x 4 x)) x))"));
        assertEquals(5, evaluate("(let x 1 y 2 x (add x y) (add x y))"));
        assertEquals(14, evaluate("(let x 2 (mult x (let x 3 y 4 (add x y))))"));
        assertEquals(15, evaluate("(mult 3 (add 2 3))"));
        assertEquals(6, evaluate("(let x 2 (add (let x 3 (let x 4 x)) x))"));
        assertEquals(4, evaluate("(let a1 3 b2 (add a1 1) b2)"));
        assertEquals(4, evaluate("(let x 2 y 2 x (add x y) x)"));
        assertEquals(5, evaluate("(let x 2 y (add 2 3) y)"));
        assertEquals(3, evaluate("(let x 2 y (let x 3 x) y)"));
        assertEquals(2, evaluate("(let x 2 x)"));
    }
}
