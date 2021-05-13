package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class LC351 {
    int m,n;
    int count(int[][]p, int count, int x, int y){
        int ret=0;
        if(count>=m && count<=n) ret++;
        if(count>n) return 0;

        int[][] dir={{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,-2},{-1,-2},{-1,2},{1,2},{2,-1},{-2,-1},{-2,1},{2,1} };
        for(int i=0;i<dir.length;i++){
            int nx=x+dir[i][0];
            int ny=y+dir[i][1];
            if(nx<0||ny<0||nx>=3||ny>=3) continue;
            if(p[ny][nx]==1){
                nx+=dir[i][0];
                ny+=dir[i][1];
                if(nx<0||ny<0||nx>=3||ny>=3||p[ny][nx]==1){
                    continue;
                }else{
                    p[ny][nx]=1;
                    ret+=count(p, count+1, nx,ny);
                    p[ny][nx]=0;
                }
            }else{
                p[ny][nx]=1;
                ret+=count(p, count+1, nx,ny);
                p[ny][nx]=0;
            }

        }
        return ret;
    }
    public int numberOfPatterns(int m, int n) {
        this.m=m;
        this.n=n;

        int[][] p = new int[3][3];
        p[0][0]=1;
        int ret=0;
        int v = count(p, 1,0,0);
        ret+=v*4;
        p[0][0]=0;
        p[0][1]=1;
        v= count(p, 1, 1,0);
        ret+=v*4;
        p[0][1]=0;
        p[1][1]=1;
        ret += count(p, 1,1,1);
        return ret;
    }


    @Test
    public void test(){
        assertEquals(56, numberOfPatterns(2,2));
    }
}
