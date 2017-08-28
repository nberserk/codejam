package leetcode;

public class SplitArrayIntoConsecutiveSubsequences_659 {

    public boolean isPossible(int[] nums) {
        int N = nums.length;
        if(N==0) return false;
        int max = nums[N-1];
        int min = nums[0];

        int[] f = new int[max+1];
        for (int v: nums){
            f[v]++;
        }

        int start=min;
        int end = 0;

        for (int i = min; i <= max; i++) {

        }


        return true;
    }

    @org.junit.Test
    public void test(){
//        assertEquals(8, (zeroflip(new int[]{1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1}, 2)));
    }
}
