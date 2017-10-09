package leetcode;

import static org.junit.Assert.assertEquals;

public class NextClosestTime_681 {

    // find greater but smallest target
    int findNext(int[] n, int idx){
        int target=-1;
        for (int i = 0; i < n.length; i++) {
            if(i==idx) continue;
            if(n[i]>n[idx]){
                if(target==-1) target=i;
                else if( n[i] < n[target]) target =i;
            }
        }
        return target;
    }
    public String nextClosestTime(String time) {

        int[] n = new int[4];
        n[0] = time.charAt(0)-'0';
        n[1] = time.charAt(1)-'0';
        n[2] = time.charAt(3)-'0';
        n[3] = time.charAt(4)-'0';

        // find min
        int min = n[0];
        for(int i=3;i>=0;i--){
            min = Math.min(min, n[i]);
        }

        for(int i=3;i>=0;i--){
            int t = findNext(n, i);
            if(t!=-1){
                if( i==1 && n[t]>4 && n[0]==2 ) {
                }else if (i==0 && n[t]>2){
                }else if (i==2 && n[t]>5){

                }
                else {
                    n[i] = n[t];
                    for (int j = i+1; j < 4; j++) {
                        n[j]=min;
                    }
                    return String.format("%d%d:%d%d", n[0], n[1], n[2], n[3]);
                }
            }
        }


        return String.format("%d%d:%d%d", min,min, min,min);
    }


    @org.junit.Test
    public void test(){
        assertEquals("15:11", nextClosestTime("13:55"));
        assertEquals("19:39", nextClosestTime("19:34"));
        assertEquals("22:22", nextClosestTime("23:59"));
    }


}
