package codejam.lib;

public class DisjointSet {
    static final int MAX_N = 100000;
    int N;
    static class Node{
        int p;
        int rank;
        public Node(int p){
            this.p=p;
            rank=0;
        }
    }
    Node [] nodes;

    void init(int N){
        this.N = N;
        nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new Node(i);
        }
    }

    int find(int n){
        if (nodes[n].p ==  n)
            return n;
        return find(nodes[n].p);
    }


    int findWithPathCompression(int n){
        if (nodes[n].p ==  n)
            return n;
        nodes[n].p = findWithPathCompression(nodes[n].p);
        return nodes[n].p;
    }


    void merge(int a, int b){
        int p = find(a);
        int p2 = find(b);

        if (p==p2) return;

        nodes[a].p = b;
    }

    public static void main(String[] args) {
        DisjointSet set = new DisjointSet();
        set.init(1000);

        for (int i = 0; i < 1000; i++) {
            
        }
    }
}
