package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Stack;


public class LC1249 {
    public String minRemoveToMakeValid(String str) {

        String out = "";
        Stack<String> s = new Stack<>();
        for(char c: str.toCharArray()){
            if(c=='('){
                s.push(out);
                out="";
            }
            else if(c==')'){
                if( !s.isEmpty() ){
                    out = s.pop() +"(" + out + ")";
                    //System.out.println(out);
                }
            }else{
                out=out+c;
            }
        }
        while(!s.isEmpty()){
            out = s.pop()+out;
        }
        return out;
    }

    @Test
    public void test(){
        Assert.assertEquals("lee(t(c)o)de", minRemoveToMakeValid("lee(t(c)o)de)"));
        Assert.assertEquals("", minRemoveToMakeValid("))(("));
        Assert.assertEquals("ab(c)d", minRemoveToMakeValid("a)b(c)d"));
        Assert.assertEquals("a(b(c)d)", minRemoveToMakeValid("(a(b(c)d)"));
    }
}
