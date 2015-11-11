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

    static class Country{
        long x;
        int p;
        int ix, ip;
    }

    static long build(Country[] c, Integer[]a, Node[] st, int s, int e, int i){
        st[i] = new Node();
        if (s==e){
            st[i].s = c[a[s]].x;
            st[i].c = 1;
            return st[i].s;
        }

        int m = s + (e-s)/2;
        st[i].s = build(c, a, st, s, m, 2*i+1)
                + build(c, a, st, m+1, e, 2*i+2);
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

    static void update(Node[]st, int dest, long diff, int N){
        _update(st, dest, diff, 0, N-1, 0);
    }

    static void _update(Node[]st, int dest, long d, int s, int e, int i){
        if(dest<s || dest > e) return ;
        st[i].s += d;
        st[i].c --;

        if(s==e)
            return;
        int m = s + (e-s)/2;
        _update(st, dest,d,s  ,m,2*i+1);
        _update(st, dest,d,m+1,e,2*i+2);
    }

    static long solve(final Country [] country){
        int N = country.length;
        Integer[] sx = new Integer[N]; // sorted x
        Integer[] sp = new Integer[N]; // sorted p

        for (int i = 0; i < N; i++) {
            sx[i] = i;
            sp[i] = i;
        }

        Arrays.sort(sx, new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                long t = country[o1].x-country[o2].x;
                if (t<0)
                    return -1;
                else if (t>0)
                    return 1;
                return 0;
            }
        });

        for (int i = 0; i < N; i++) {
            country[sx[i]].ix = i;
        }

        //Arrays.sort
        Arrays.sort(sp, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -country[o1].p + country[o2].p;
            }
        });

        Node[] st = new Node[4*N];
        build(country, sx, st, 0, N - 1, 0);

//        int s = query(st,0,3,0,N-1, 0);
//        update(x, st, 0, 0, 0);
//        s = query(st, 0, 3, 0, N-1, 0);

        long sum=0, t;
        for(int i=0;i<N-1;i++){
            int r = N-1-i;
            // left
            int countryIdx = sp[i];
            int idxSegmentTree = country[countryIdx].ix;
            long c = queryCount(st, 0, idxSegmentTree, 0, N-1, 0);
            if (c>1){
                c--;
                t = c * country[countryIdx].x - query(st, 0, idxSegmentTree - 1, 0, N - 1, 0);
                t *= country[countryIdx].p;
                sum +=t;
            }

            // right
            c = queryCount(st, idxSegmentTree, N-1, 0, N-1, 0);
            if(c!=1){
                c--;
                t =  query(st, idxSegmentTree+1, N - 1, 0, N - 1, 0) - c * country[countryIdx].x ;
                t *= country[countryIdx].p;
                sum +=t;
            }
            sum %= M;
            update(st, idxSegmentTree, -country[countryIdx].x, N);
        }

        return sum;
    }

    static void test(){

        Country[] c = new Country[5];
        int [] x = new int[]{   555, 5, 55,  55555, 555555};
        int [] p = new int[]{333, 3333,333,   33, 35};
        for (int i = 0; i <5; i++) {
            c[i] = new Country();
            c[i].x = x[i];
            c[i].p = p[i];
        }
        long s = solve(c);
        CheckUtil.check(463055586, s);

        c = new Country[3];
        x = new int[] {1,3,6};
        p = new int[] {10,20,30};
        for (int i = 0; i <3; i++) {
            c[i] = new Country();
            c[i].x = x[i];
            c[i].p = p[i];
        }
        s= solve(c);
        CheckUtil.check(280, s);
    }

    public static void main(String[] args) {

        //int t = 1000000000;
        //long temp = 1000000000 + (long)t*1000;

        test();

        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        for(int i =0;i<T;i++){
            int N = s.nextInt();

            Country []c = new Country[N];

            for(int j=0;j<N;j++){
                c[j] = new Country();
                c[j].x = s.nextInt();
            }
            for(int j=0;j<N;j++){
                c[j].p = s.nextInt();
            }

            long sum = solve(c);
            System.out.println(sum);
        }
    }
}

