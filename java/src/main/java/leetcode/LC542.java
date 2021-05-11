package main.java.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 3/26/17.
 */
public class Matrix01_542 {

    public List<List<Integer>> updateMatrix(List<List<Integer>> matrix) {
        int row = matrix.size();
        if(row==0) return matrix;
        int col = matrix.get(0).size();

        LinkedList<int[]> q = new LinkedList<>();

        for(int y=0;y<row;y++){
            for(int x=0;x<col;x++){
                if( matrix.get(y).get(x) ==0)
                    q.add(new int[]{x,y});
                else
                    matrix.get(y).set(x, Integer.MAX_VALUE);
            }
        }

        int [][] d= { {-1,0},{1,0}, {0,-1},{0,1} };

        while(q.size()>0 ){
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int v = matrix.get(y).get(x);

            for(int i=0;i<4;i++){
                int nx = d[i][0] + x;
                int ny = d[i][1] + y;
                if(nx<0||ny<0||nx>=col||ny>=row) continue;
                if(matrix.get(ny).get(nx)<=v+1 ) continue;
                matrix.get(ny).set(nx,v+1);
                q.add(new int[]{nx,ny}); // WARNING, q.push add element to head, so you have to be careful.
            }
        }

        return matrix;
    }

    @Test
    public void test(){
        List<List<Integer>> m = new ArrayList<>();
        List<Integer> t = new ArrayList<>();
        t.add(0); t.add(0); t.add(0);
        m.add(t);
        t = new ArrayList<>();
        t.add(0); t.add(1); t.add(0);
        m.add( t);
        t = new ArrayList<>();
        t.add(1); t.add(1); t.add(1);
        m.add( t);

        assertEquals("[[0, 0, 0], [0, 1, 0], [1, 2, 1]]", updateMatrix(m).toString());



    }
}
