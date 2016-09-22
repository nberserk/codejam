package crackcode.tree;

import java.util.Arrays;

/**
 * Created by darren on 2016. 9. 22..
 */
public class UnionFind {

    int[] n;

    void init(int capacity){
        n = new int[capacity];
        Arrays.fill(n, -1); // -1 means not set
    }


    void makeSet(int i){
        n[i] = i;
    }

    void union(int i, int j){
        
    }

}
