package crackcode.graph;

import java.util.Arrays;

public class DFS {
    public static final int N = 4;
    int[][] mMat = new int[N][N];
    int[] mVisited = new int[N];

    DFS(){
        mMat[0][1]=1;
        mMat[1][2]=1;
        mMat[2][3]=1;
        // mMat[3][0]=1;
    }

    boolean dfs(int cur, int dest){
        if (dest==cur){
            return true;
        }

        if (mVisited[cur] == 1) {
            return false;
        }
        mVisited[cur] = 1;

        for (int i = 0; i < N; i++){
            if (mMat[cur][i]==1){
                boolean reach = dfs(i, dest);
                if (reach){
                    return true;
                }
            }
        }

        return false;
    }

    boolean isConnected(int start, int dest){
        Arrays.fill(mVisited,0);

        return dfs(start, dest);
    }

    public static void main(String[] args) {
        DFS d = new DFS();
        boolean ret = d.isConnected(0,3);
        System.out.println(ret);

    }

}
