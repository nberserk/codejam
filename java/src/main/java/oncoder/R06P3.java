package oncoder;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

public class P3 {

    int[] answer;
    int max=Integer.MAX_VALUE;

    public int[] solution(String parts){
        char[] ch = parts.toCharArray();
        Arrays.sort(ch);

        find(ch, new LinkedList<Integer>(), 0,0);

        return answer;
    }

    void swap(char[] c, int i, int j){
        char t = c[i];
        c[i]=c[j];
        c[j]=t;
    }

    boolean isValid(int n){
        for (int i = 2; i < n; i++) {
            if(n%i==0)
                return false;
        }
        return true;
    }

    private void find(char[] ch, LinkedList<Integer> made, int prev, int start) {
        if(start==ch.length){
            if(prev!=0 && isValid(prev) && made.size()==2){
                made.addLast(prev);
                System.out.println(made);
                made.removeLast();
            }else             if(prev==0 && made.size()==3){
                made.addLast(prev);
                System.out.println(made);
                made.removeLast();
            }
            return;
        }

        for (int i = start; i < ch.length; i++) {
            swap(ch, start, i);
            int cur = ch[i]-'0';
            // block 0
            // append to prev
            find(ch, made, prev*10+cur, i+1);

            if(prev>0 && isValid(prev)){// add number & reset
                made.addLast(prev);
                find(ch, made, cur, start+1);
                made.removeLast();
            }
            swap(ch, start, i);
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(true, isValid(2));
        Assert.assertEquals(true, isValid(199));
        Assert.assertEquals(false, isValid(4));

        Assert.assertEquals(null, solution("791192"));

    }



}
