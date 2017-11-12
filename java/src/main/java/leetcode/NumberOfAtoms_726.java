package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

public class NumberOfAtoms_726 {

    int lenDigit(String f, int s){
        int len=0;
        while(s<f.length()&&Character.isDigit(f.charAt(s))){
            len++;
            s++;
        }
        return len;
    }

    void add(Map<String,Integer> map, String key, int delta ){
        if(key.length()>0){
            map.putIfAbsent(key, 0);
            map.put(key, map.get(key)+delta);
        }
    }

    class Result{
        Map<String, Integer> r = new HashMap<>();
        int len;
    }

    Result atom(String formula, int start){
        Result r = new Result();

        for (int i = start; i < formula.length(); i++) {
            char c = formula.charAt(i);
            if(c=='('){

            }else if(c==')'){
                
            }
        }

        return r;
    }


    public String countOfAtoms(String formula) {
        Result r  = atom(formula, 0);
    }

    public String countOfAtoms1(String formula) {
        Map<String, Integer> map = new TreeMap<>();
        String cur="";
        Stack<Map<String,Integer>> s = new Stack<>();

        for(int i=0;i<formula.length()+1;i++)        {
            if(i==formula.length()){
                add(map, cur, 1);
                break;
            }
            char c = formula.charAt(i);
            if(Character.isDigit(c)){
                int lenDigit = lenDigit(formula, i);
                int count=Integer.valueOf(formula.substring(i, i+lenDigit));
                i+=lenDigit-1;
                add(map, cur, count);
                cur="";
            }else if( c=='('){
                add(map, cur,1);
                cur="";
                s.push(map);
                map = new TreeMap<>();
            }else if (c==')'){
                add(map, cur,1);
                cur="";

                Map<String,Integer> prev = s.pop();
                int lenDigit = lenDigit(formula, i+1);
                int mul = 1;
                if(lenDigit>0){
                    mul =  Integer.valueOf(formula.substring(i+1, i+1+lenDigit));
                    i+=lenDigit-1;
                }

                for(String key: map.keySet()){
                    add(prev, key, map.get(key)*mul);
                }
                map = prev;
            }else if(c >= 'A' && c<='Z'){
                add(map, cur, 1);
                cur = ""+c;
            }else{
                cur += c;
            }
        }

        String ret = "";
        for(String key: map.keySet()){
            int c = map.get(key);
            if(c==1)
                ret += key ;
            else ret += key + String.valueOf(c);
        }
        return ret;
    }


    @org.junit.Test
    public void test(){
        assertEquals("H2MgO2", countOfAtoms("Mg(OH)2"));
        assertEquals("K4N2O14S4", countOfAtoms("K4(ON(SO3)2)2"));


    }


}
