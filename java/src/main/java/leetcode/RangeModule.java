package leetcode;

import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class RangeModule {

    TreeSet<int[]> tree = new TreeSet<>();


    public void addRange(int left, int right) {
        int[] cur = new int[2];
        cur[0]=left;
        cur[1]=right;
        int[] l = tree.floor(cur);
        int[] r = tree.ceiling(cur);

        if(l!=null && right<=l[1]){}
        else if(l!=null && cur[0]<l[1] && r!=null && r[0]<cur[1]){
            l[1]=r[1];
            tree.remove(r);
        }else if(l!=null && cur[0]<l[1]){
            l[1]=cur[1];
        }else if(r!=null && r[0]<cur[1]){
            r[0]=cur[0];
        }else{
            tree.add(cur);
        }
    }

    public boolean queryRange(int left, int right) {
        int[] cur = new int[2];
        cur[0]=left;
        cur[1]=right;
        int[] l = tree.floor(cur);
        if(l!=null && right<=l[1])
            return true;
        return false;
    }

    public void removeRange(int left, int right) {
        int[] cur = new int[2];
        cur[0]=left;
        cur[1]=right;
        int[] l = tree.floor(cur);
        int[] r = tree.ceiling(cur);
        if(l!=null&& right<=l[1]){
            int prev = l[1];
            l[1]=left;
            if(right!=l[1]){
                tree.add(new int[]{right, prev});
            }
        }
        else if(l!=null && cur[0]<l[1] && r!=null && r[0]<cur[1]){
            l[1]=left;
            r[0]=right;
        }else if(l!=null && cur[0]<l[1]){
            l[1]=left;
        }else if(r!=null && r[0]<cur[1]){
            r[0]=right;
        }
    }

    @org.junit.Test
    public void test(){
        RangeModule rm = new RangeModule();
        rm.addRange(10, 20);
        rm.removeRange(14, 16);
        assertEquals(true, rm.queryRange(10, 14));
        assertEquals(false, rm.queryRange(13, 15));
        assertEquals(true, rm.queryRange(16, 17));
        rm.addRange(14, 16);
        assertEquals(true, rm.queryRange(14, 15));

    }


}
