package leetcode;

import org.junit.Assert;
import org.junit.Test;

public class LC990 {
    public boolean equationsPossible(String[] equations) {
        int[] parent = new int[30];
        for (int i = 0; i < 26; i++) {
            parent[i]=i;
        }

        // link
        for (String e: equations){
            if (e.charAt(1)=='='){
                int first = e.charAt(0)-'a';
                int second = e.charAt(3)-'a';
                int pf = getParent(parent, first);
                int ps = getParent(parent, second);
                parent[pf]=ps;
            }
        }

        // unlink
        for (String e: equations){
            if (e.charAt(1)=='!'){
                int first = e.charAt(0)-'a';
                int second = e.charAt(3)-'a';
                int pf = getParent(parent, first);
                int ps = getParent(parent, second);
                if(pf==ps) return false;
            }
        }

        return true;
    }

    int getParent(int[] p, int target){
        int org=target;
        while(p[target]!=target){
            target=p[target];
        }
        p[org]=target;
        return target;
    }

    @Test
    public void test(){

        Assert.assertEquals(false, equationsPossible(new String[]{"a==b","b!=a"}));
        Assert.assertEquals(true, equationsPossible(new String[]{"a==b","b==a"}));
        Assert.assertEquals(false, equationsPossible(new String[]{"a==b","b!=c","c==a"}));


    }
}
