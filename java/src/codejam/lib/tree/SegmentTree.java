package codejam.lib.tree;

import codejam.lib.CheckUtil;

/**
 * Created by darren on 11/8/15.
 */


public class SegmentTree {
    static class Node{
        int sum;
        int l,r;
    }
    Node[] node;
    int build(int[]in ){
        node = new Node[in.length*4];
        return build(in, 0, in.length-1, 0);
    }
    int build(int[] in, int s, int e, int i){
        int m = (s+e)/2;
        node[i] = new Node();
        node[i].l = s;
        node[i].r = e;

        if(s==e){
            node[i].sum = in[s];
        }else{
            int left = 0;
            if (s<=m)
                left = build(in, s, m, 2*i+1);
            int right = 0;
            if (m+1<=e)
                right = build(in, m+1, e, 2*i+2);
            node[i].sum = left + right;
        }

        return node[i].sum;
    }

    int query(int cs,int ce, int s, int e, int i){
        if (ce <s || cs>e) return 0;

        int m = (s+e)/2;

        if(s==e)
            return node[i].sum;

        if(ce <=m)
            return query(cs, ce, s,m, 2*i+1);
        else if (cs >= m+1)
            return query(cs, ce, m+1, e, 2*i+2);

        int r = 0;
        r += query(cs, m, s, m, 2*i+1 );
        r += query(m+1, ce, m+1, e, 2*i+2);
        return r;
    }

    void update(int dest, int diff){
        update(dest, diff, 0);
    }

    void update(int dest, int diff, int i){
        
    }

    public static void main(String[] args) {
        SegmentTree tree = new SegmentTree();

        int[] a = new int[10];
        for (int i = 0; i < 10; i++) {
            a[i] = i+1;
        }

        tree.build(a);
        int v = tree.query(0,1, 0, 9, 0);
        CheckUtil.check(3,v);
        v = tree.query(0,9, 0, 9, 0);
        CheckUtil.check(55,v);

        v = tree.query(1,5, 0, 9, 0);
        CheckUtil.check(20,v);

        System.out.println("done");
    }


}
