package leetcode;


import static org.junit.Assert.assertEquals;

public class LC688 {
    int[][] move = {{-1,2},{1,2},{-2,-1},{-2,1},{-1,-2},{1,-2},{2,1},{2,-1}};
    double[][][] cache;
    int N;
    double calc(int k, double cur, int x, int y){
        if(k==0) return cur;
        if(cache[y][x][k]!=0)
            return cache[y][x][k];

        double ret =0;
        for(int i=0;i<8;i++){
            int nx=x+move[i][0];
            int ny=y+move[i][1];
            if(nx<0||ny<0||nx>=N||ny>=N) continue;
            ret += calc(k-1, cur*(1/8.0), nx, ny);
        }

        cache[y][x][k]=ret;
        return ret;
    }
    public double knightProbability(int N, int K, int r, int c) {
        this.N = N;
        cache = new double[N][N][K+1];
        double out = calc(K, 1.0, c, r);
        return out;
    }

    @org.junit.Test
    public void test() {
        assertEquals(0.0625, knightProbability(3, 2, 0,0), 0.00001);
        assertEquals(0.0012193746331114295, knightProbability(25, 100, 0,0), 0.00001);
    }
}
