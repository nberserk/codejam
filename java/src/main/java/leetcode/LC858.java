package leetcode;

import static org.junit.Assert.assertEquals;

public class MirrorReflection_858 {


    public int mirrorReflection(int p, int q) {
        boolean left=false;
        int y=0;
        while (true){
            y+=q;
            if(y%p==0){
                if (left) return 2;
                else{
                    if(y%(2*p)==0) return 0;
                    else return 1;
                }
            }
            left=!left;
        }
    }


    @org.junit.Test
    public void test(){
        assertEquals(1, mirrorReflection(3,3));
        assertEquals(2, mirrorReflection(2,1));
        assertEquals(2, mirrorReflection(4,1));
        assertEquals(1, mirrorReflection(3,1));
//        assertEquals(false, isOneBitCharacter(new int[]{1,1,1,0}));
    }
}
