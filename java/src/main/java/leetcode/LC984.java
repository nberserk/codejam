package leetcode;

import org.junit.Assert;
import org.junit.Test;

public class LC984 {
    public String strWithout3a3b(int A, int B) {
        char[] ch = new char[A+B];
        int i=0;
        while(A>0&&B>0){
            if(A>=B+2){
                ch[i++]='a';
                ch[i++]='a';
                ch[i++]='b';
                A-=2;
                B--;
            }else if(B>=A+2){
                ch[i++]='b';
                ch[i++]='b';
                ch[i++]='a';
                B-=2;
                A--;
            }else {
                ch[i++]='a';
                ch[i++]='b';
                A--;
                B--;
            }
        }
        while(A>0){
            ch[i++]='a';
            A--;
        }
        while(B>0){
            ch[i++]='b';
            B--;
        }
        return new String(ch);
    }


        @Test
    public void test(){
        Assert.assertEquals("abb" , strWithout3a3b(1,2));
        Assert.assertEquals("aabaa" , strWithout3a3b(4,1));


    }
}
