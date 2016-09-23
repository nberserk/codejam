package crackcode.tree;

import org.junit.Test;


import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 2016. 9. 22..
 *
 * http://www.geeksforgeeks.org/union-find-algorithm-set-2-union-by-rank/
 */
public class UnionFind {
    int count;
    int[] parent;
    int[] rank;

    public void init(int n){
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i]=i;
        }
        count=n;
    }

    int find(int i){
        if(parent[i]==i)
            return i;
        parent[i] = find(parent[i]);
        return parent[i];
    }

    int findIterative(int i){
        while(parent[i]!=i){
            parent[i] = parent[parent[i]]; // path compression by half
            i = parent[i];
        }

        return i;
    }

    void union(int i, int j){
        int p1 = find(i);
        int p2 = find(j);
        if(p1==p2) return;

        // make root of smaller rank point to root of larger rank
        if      (rank[p1] < rank[p2]) parent[p1] = p2;
        else if (rank[p1] > rank[p2]) parent[p2] = p1;
        else {
            parent[p2] = p1;
            rank[p1]++;
        }
        count--;
    }

    boolean isConnected(int i, int j){
        return find(i) == find(j);
    }

    int count(){
        return count;
    }

    @Test
    public void test(){
        UnionFind uf = new UnionFind();
        uf.init(10);
        uf.union(0, 9);
        uf.union(8,7);
        uf.union(2,3);
        uf.union(0,2);

        assertEquals(true, uf.isConnected(0, 3));
        assertEquals(false, uf.isConnected(0, 8));
        assertEquals(true, uf.isConnected(7, 8));
        assertEquals(6, uf.count());
    }

}
