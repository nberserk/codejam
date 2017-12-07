package crackcode.tree;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class QuadTree {
	class QNode{
		int sx,sy,size;
		int val;
		QNode[] child;

		QNode(int[][] grid, int x, int y, int size){
		    sx=x;
		    sy=y;
		    this.size=size;

		    int v=grid[y][x];
		    boolean different=false;
            for (int i = y; i < y+size; i++) {
                for (int j = x; j < x+size; j++) {
                    if(v!=grid[i][j]){
                        different=true;
                        break;
                    }
                }
                if(different)break;
            }

            if (different){
                size/=2;
                int idx=0;
                child = new QNode[4];
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        int nx=x+j*size;
                        int ny=y+i*size;
                        child[idx++] = new QNode(grid, nx, ny, size);
                    }
                }
            }else{
                this.val = v;
            }
        }
	}


	@Test
    public void test(){
	    int[][] grid = {
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,0,0,0,1},
                {1,1,1,1,0,0,1,1},
                {0,0,0,0,0,0,1,1},
                {0,0,0,0,0,0,1,1},
                {0,0,0,0,1,1,1,1},
                {0,0,0,0,1,1,1,0}
        };
	    QNode root = new QNode(grid, 0,0, 8);
	    assertTrue(root.child!=null);
    }
}
