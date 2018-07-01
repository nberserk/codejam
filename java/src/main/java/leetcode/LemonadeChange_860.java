package leetcode;

import org.junit.Assert;

public class LemonadeChange_860 {

    public boolean lemonadeChange(int[] bills) {
        int[] change = new int[3];
        for (int b: bills){
            if(b==5){
                change[0]++;
            }else if(b==10){
                if(change[0]<=0) return false;
                change[0]--;
                change[1]++;
            }else if(b==20){
                if(change[1]>0 && change[0]>0){
                    change[1]--;
                    change[0]--;
                }else if(change[0]>=3){
                    change[0]-=3;
                }else
                    return false;
            }
        }
        return true;
    }

    @org.junit.Test
    public void test(){

        Assert.assertEquals(false, lemonadeChange(new int[]{10,10}));
        Assert.assertEquals(false, lemonadeChange(new int[]{5,5,10,10,20}));
        Assert.assertEquals(true, lemonadeChange(new int[]{5,5,5, 10,20}));
//        assertEquals(false, isOneBitCharacter(new int[]{1,1,1,0}));
    }
}
