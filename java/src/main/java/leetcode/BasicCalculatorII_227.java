package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 16/02/2017.
 */
public class BasicCalculatorII_227 {


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

        return r;
    }

    int order(char c){        
        switch(c) {
        case '*':
        case '/':
            return 5;
        default:
            return 1;
        }
        return -1;
    }

    int calculate(String s){
        List<String> token = tokenize(s);
        Stack<String> op = new Stack<>();
        Stack<Integer> n = new Stack<>();
        for (int i = 0; i < token.size(); i++){
            char[] c = token.get(i).toCharArray();
            boolean operator=false;
            if (c.length==1 && !Character.isDigit(c[0])){
                operator=true;
            }

            if (!op){
                n.add(String.valueOf(token.get(i)));
            }else{
                int or = order(c[0]);
                while (!op.isEmpty() ||  ){
                    
                }
            }
            
        }

    }

    @Test
    public void test(){
        assertEquals("PAHNAPLSIIGYIR", convert("PAYPALISHIRING", 3));
        

    }
}
