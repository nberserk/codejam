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
    int N;
    int build(int[]in ){
        N = in.length;
        node = new Node[N*4];

        return build(in, 0, N-1, 0);
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

    int query(int s, int e){
        return _query(s,e,0, N-1, 0);
    }

    /**
     * 3 cases; out or range , in range, overlapping
     * @param cs querying start index
     * @param ce querying end index
     * @param s start index of current node
     * @param e end index of current node
     * @param i node index
     * @return
     */
    int _query(int cs,int ce, int s, int e, int i){
        // out of range
        if (ce <s || cs>e)
            return 0;

        // in range
        if (cs <=s && e<=ce)
            return node[i].sum;

        // overlapping
        int m = (s+e)/2;
        int r = 0;
        r += _query(cs, ce, s, m, 2 * i + 1);
        r += _query(cs, ce, m + 1, e, 2 * i + 2);
        return r;
    }

    void update(int dest, int newValue)    {
        int v = query(dest, dest);
        int diff = newValue - v;
        _update(dest, diff, 0, N - 1, 0);
    }

    private void _update(int dest, int diff, int s, int e, int i){
        if (dest<s || dest>e) return;

        node[i].sum += diff;

        if(s==e) return;

        int m = (s+e)/2;
        _update(dest, diff, s, m, 2*i+1);
        _update(dest, diff, m+1, e, 2*i+2);
    }

    public static void main(String[] args) {
        SegmentTree tree = new SegmentTree();

        int[] a = new int[10];
        for (int i = 0; i < 10; i++) {
            a[i] = i+1;
        }

        tree.build(a);
        int v = tree.query(0, 1);
        CheckUtil.check(3,v);
        v = tree.query(0, 9);
        CheckUtil.check(55,v);

        v = tree.query(1, 5);
        CheckUtil.check(20,v);

        tree.update(0, 0);
        v = tree.query(0, 9);
        CheckUtil.check(54, v);

        System.out.println("done");
    }


}
