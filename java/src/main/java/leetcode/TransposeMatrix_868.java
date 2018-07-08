package leetcode;

public class TransposeMatrix_868 {
    public int[][] transpose(int[][] A) {
        int R = A.length;
        int C = A[0].length;
        int[][] r = new int[C][R];
        for (int i = 0; i < C; i++) {
            for (int j = 0; j < R; j++) {
                r[i][j] = A[j][i];
            }
        }
        return r;
    }
}
