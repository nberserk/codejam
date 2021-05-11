package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class _378 {
    int N;
    int getLessOrEqual(int[][] m, int target){
        int count=0;
        int c=N-1;
        for(int r=0;r<N;r++){
            while (c>=0 && m[r][c]>target)
                c--;
            count+=c+1;
        }
        return count;
    }
    public int kthSmallest(int[][] matrix, int k) {
        N =matrix.length;
        int lo=matrix[0][0];
        int hi=matrix[N-1][N-1];
        while(lo<hi){
            int mid=lo+(hi-lo)/2;
            int count = getLessOrEqual(matrix, mid);
            System.out.println(String.format("(%d,%d)%d-%d",lo,hi,mid,count));
            if(count<k)
                lo=mid+1;
            else hi=mid;
        }
        return lo;
    }

    @Test
    public void test(){
        assertEquals(13, kthSmallest(new int[][]{{1,5,9},{10,11,13},{12,13,15}}, 8));

    }
}
