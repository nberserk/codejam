package hackerrank;

import codejam.lib.CheckUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * category: dp
 * from : https://www.hackerrank.com/challenges/direct-connections
 */
public class DirectConnections {
    static int M = 1000000007;

    static class Node{
        int c;
        long s;
    }

    static long build(int[]x, Integer[]a, Node[] st, int s, int e, int i){
        st[i] = new Node();
        if (s==e){
            st[i].s = x[a[s]];
            st[i].c = 1;
            return st[i].s;
        }

        int m = s + (e-s)/2;
        st[i].s = build(x, a, st, s, m, 2*i+1) + build(x, a, st, m+1, e, 2*i+2);
        st[i].c = e-s+1;
        return st[i].s;
    }

    static long query(Node[]st, int qs, int qe, int s, int e, int i){
        if (qe<s || qs > e || qs>qe)
            return 0;
        else if(qs<=s && e<=qe)
            return st[i].s;

        int m = s + (e-s)/2;
        return query(st, qs, qe, s, m, 2 * i + 1)
                + query(st, qs, qe, m + 1, e, 2 * i + 2);
    }

    static int queryCount(Node[]st, int qs, int qe, int s, int e, int i){
        if (qe<s || qs > e || qs>qe)
            return 0;
        else if(qs<=s && e<=qe)
            return st[i].c;

        int m = s + (e-s)/2;
        return queryCount(st, qs, qe, s, m, 2*i+1)
                + queryCount(st, qs, qe, m+1, e, 2*i+2);
    }

    static void update(int[]x, Node[]st, int dest, int nv){
        int diff = nv - x[dest];
        _update(st, dest, diff, 0, x.length-1, 0);
    }

    static void _update(Node[]st, int dest, int d, int s, int e, int i){
        if(dest<s || dest > e) return ;
        st[i].s += d;
        st[i].c --;

        if(s==e)
            return;
        int m = s + (e-s)/2;
        _update(st, dest,d,s  ,m,2*i+1);
        _update(st, dest,d,m+1,e,2*i+2);
    }

    static long solve(final int[] x, final int [] p){
        int N = x.length;
        Integer[] sx = new Integer[N]; // sorted x
        Integer[] sp = new Integer[N]; // sorted p

        for (int i = 0; i < N; i++) {
            sx[i] = i;
            sp[i] = i;
        }

        Arrays.sort(sx, new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return x[o1]-x[o2];
            }
        });

        //Arrays.sort
        Arrays.sort(sp, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -p[o1] + p[o2];
            }
        });

        Node[] st = new Node[4*N];
        build(x, sx, st, 0, N - 1, 0);

//        int s = query(st,0,3,0,N-1, 0);
//        update(x, st, 0, 0, 0);
//        s = query(st, 0, 3, 0, N-1, 0);

        long sum=0, t;
        for(int i=0;i<N-1;i++){
            int r = N-1-i;
            // left
            int dest = sp[i];
            int c = queryCount(st, 0, dest, 0, N-1, 0);
            if (c!=1){
                c--;
                t = (long)(c * x[dest]) - query(st, 0, dest - 1, 0, N - 1, 0);
                t *= p[dest];
                sum +=t;
            }
            // right
            c = queryCount(st, dest, N-1, 0, N-1, 0);
            if(c!=1){
                c--;
                t =  query(st, dest+1, N - 1, 0, N - 1, 0) - (long)(c * x[dest]) ;
                t *= p[dest];
                sum +=t;
            }
            sum %= M;
            update(x, st, dest, 0);
        }

        return sum;
    }

    static void test(){

        int [] x = new int[]{   5, 55, 555, 55555, 555555};
        int [] p = new int[]{3333,333, 333,    33, 35};
        long s = solve(x,p);
        CheckUtil.check(463055586, s);
    }

    public static void main(String[] args) {

        test();

        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        for(int i =0;i<T;i++){
            int N = s.nextInt();
             int[] x = new int[N];
             int[] p = new int[N];


            for(int j=0;j<N;j++){
                x[j] = s.nextInt();
            }
            for(int j=0;j<N;j++){
                p[j] = s.nextInt();
            }

            long sum = solve(x, p);
            System.out.println(sum);
        }
    }
}

