package leetcode;


import static org.junit.Assert.assertEquals;

public class _769 {
    public int maxChunksToSorted(int[] arr) {
        int split=0;
        int max=-1;
        for(int i=0;i<arr.length;i++){
            max=Math.max(max, arr[i]);
            if(i==max){
                split++;
                max=-1;
            }
        }
        return split;
    }

    @org.junit.Test
    public void test() {
        assertEquals(1, maxChunksToSorted(new int[]{4,3,2,1,0}));
        assertEquals(4, maxChunksToSorted(new int[]{1,0,2,3,4}));
    }
}
