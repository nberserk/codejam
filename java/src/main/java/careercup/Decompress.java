package careercup;

import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class Decompress {
    public String decompress(String input) {
        int i=0;
        Stack<String> s = new Stack<>();
        String cur="";
        while(i<input.length()){
            char c = input.charAt(i++);
            if(c=='('){
                s.push(cur);
                cur="";
            }else if(c==')'){
                String prev = s.pop();
                int end = input.indexOf('}',i+1);
                int mul = Integer.valueOf(input.substring(i+1, end));
                StringBuilder sb = new StringBuilder();
                sb.append(prev);
                for (int j = 0; j < mul; j++) {
                    sb.append(cur);
                }
                cur=sb.toString();
                i=end+1;
            }else{
                cur+=c;
            }
        }
        return cur;
    }

    @Test
    public void test(){
        assertEquals("abccbccd", decompress("a(b(c){2}){2}d"));
        assertEquals("xxxyyzxxxyyz", decompress("((x){3}(y){2}z){2}"));


    }
}
