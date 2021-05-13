package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class LC241 {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> ret = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if(c=='+'||c=='-'||c=='*'){
                List<Integer> prev = diffWaysToCompute(input.substring(0, i));
                List<Integer> next = diffWaysToCompute(input.substring(i+1, input.length()));


                for (int p:prev){
                    for(int n:next){
                        ret.add(calc(p,n,c));
                    }
                }
                //return ret;
            }
        }

        if(ret.size()==0)
            ret.add(Integer.valueOf(input));
        Collections.sort(ret);
        return ret;
    }

    int calc(int f, int s, char op){
        switch (op){
            case '+':
                return f+s;
            case '-':
                return f-s;
            case '*':
                return f*s;
        }
        return 0;
    }




    @Test
    public void test(){
        assertEquals("[0, 2]", diffWaysToCompute("2-1-1").toString());
        assertEquals("[-34, -14, -10, -10, 10]", diffWaysToCompute("2*3-4*5").toString());

    }
}
