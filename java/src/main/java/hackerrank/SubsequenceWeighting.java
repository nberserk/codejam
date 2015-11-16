package hackerrank;

import codejam.lib.CheckUtil;

import java.util.*;

/**
 * Created by darren on 2015. 11. 16..
 */
public class SubsequenceWeighting {

    static int N;
    static int MAX_N = 150001;
    static int[] x = new int[MAX_N];
    static int[] w = new int[MAX_N];

    static class Node{
        int x;
        long w;

        public Node(int x, long w){
            this.x = x;
            this.w = w;
        }
    }

    static long solve(){
        TreeSet<Node> set = new TreeSet<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                int d = o1.x - o2.x;
                if (d==0)
                    return int(o1.w - o2.w);
                return d;
            }
        });

        set.add(new Node(0,0));
        ArrayList<Node> erase = new ArrayList<>();

        for (int i = 0; i < N; i++) {

            Node nn = new Node(x[i], 0);
            Node before = set.lower(nn);
            nn.w = before.w + w[i];

            SortedSet<Node> nodes = set.subSet(before, false, set.last(), true);
            Iterator<Node> it =  nodes.iterator();
            boolean valid=true;

            while (it.hasNext()){
                Node cur = it.next();
                if (cur.x == x[i] && cur.w >= nn.w){
                    valid =false;
                    break;
                }else if (cur.w < nn.w){
                    erase.add(cur);
                }else{
                    break;
                }
            }

            for (int j = 0; j < erase.size(); j++) {
                set.remove(erase.get(j));
            }
            if(valid){
                set.add(nn);
            }
            erase.clear();
        }

        return set.last().w;
    }

    static void test(){
        N = 8;
        int[] xx = new int[]{1,2,3,4,1,2,3,4};
        int[] ww = new int[]{10, 20, 30, 40, 15, 15, 15, 50};

        for (int i = 0; i < N; i++) {
            x[i] = xx[i];
            w[i] = ww[i];
        }
        CheckUtil.check(110, solve());
    }

    public static void main(String[] args) {

        test();
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        for (int p=0;p<T;p++){
            N = s.nextInt();
            for(int i=0;i<N;i++){
                x[i] = s.nextInt();
            }
            for(int i=0;i<N;i++){
                w[i] = s.nextInt();
            }
            solve();
        }

    }
}
