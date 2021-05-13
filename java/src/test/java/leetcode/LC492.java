package leetcode;

import static org.junit.Assert.assertArrayEquals;

public class LC492 {

    void findMin(int[] r, int area, int first, int second){
        if (area==1){
            int diff=Math.abs(first-second);
            if(diff<Math.abs(r[0]- r[1])){
                if (first>second){
                    r[0]=first;
                    r[1]=second;
                }else{
                    r[0]=second;
                    r[1]=first;
                }
            }
            return;
        }
        for (int i = 2; i <= area; i++){
            if (area%i==0){
                findMin(r, area/i, first*i, second);
                findMin(r, area/i, first, second*i);
                break;
            }
        }
    }
    int[] constructRectangle_1(int area) {

        int[]r = new int[2];
        r[1]=Integer.MAX_VALUE;

        findMin(r, area, 1, 1);
        return r;
    }

    int[] constructRectangle(int area) {
        int[] r = new int[2];
        r[1]=Integer.MAX_VALUE;
        int mid = (int)Math.sqrt(area);
        while(area%mid!=0)
            mid--;
        int b = area/mid;
        r[0]=b;
        r[1]=mid;
        return r;
    }


    @org.junit.Test
    public void test(){
        assertArrayEquals(new int[]{4, 3}, constructRectangle(12));
        assertArrayEquals(new int[]{10, 10}, constructRectangle(100));
    }
}
