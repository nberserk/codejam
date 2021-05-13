package leetcode;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class LC681 {

    int nextGreater(int[] n, int v){
        for(int i=0;i<n.length;i++){
            if(n[i]>v) return n[i];
        }
        return n[0];
    }
    public String nextClosestTime(String time) {
        int[] n = new int[4];
        int[] org= new int[4];
        n[0]=time.charAt(0)-'0';
        n[1]=time.charAt(1)-'0';
        n[2]=time.charAt(3)-'0';
        n[3]=time.charAt(4)-'0';
        for(int i=0;i<4;i++)
            org[i]=n[i];
        Arrays.sort(n);

        int[] max={2,9,5,9};
        for(int i=3;i>=0;i--){
            int greater = nextGreater(n, org[i]);
            if(org[i]<greater && max[i]>=greater){
                if(i==1&&org[0]==2&&greater>4){
                    org[i]=n[0]; continue;
                }
                org[i]=greater;
                return String.format("%d%d:%d%d",org[0],org[1],org[2],org[3]);
            }else
                org[i]=n[0];
        }

        return String.format("%d%d:%d%d",org[0],org[1],org[2],org[3]);
    }


    @org.junit.Test
    public void test(){
        assertEquals("15:11", nextClosestTime("13:55"));
        assertEquals("19:39", nextClosestTime("19:34"));
        assertEquals("22:22", nextClosestTime("23:59"));
    }


}
