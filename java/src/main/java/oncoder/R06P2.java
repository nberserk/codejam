package oncoder;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class R06P2 {

    public int solution(String[] board){
        int R =   board.length;
        int C= board[0].length();
        int[][] m = new int[R][C];
        for(int i=0;i<R;i++){
            char p = 'a';
            int count=0;
            for(int j=board[i].length()-1;j>=0;j-- ){
                char c =board[i].charAt(j);
                if(p== c){
                    count++;
                    m[i][j]=count;
                }else{
                    count=1;
                    m[i][j]=count;
                    p=c;
                }
            }
            System.out.println(Arrays.toString(m[i]) );
        }

        int ret = 1;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(m[i][j]<=ret) continue;
                //looking down
                if(i+ret>=R) continue;
                int k=i+1;
                for (; k < R ; k++) {
                    if(m[k][j]<m[i][j])break;
                }
                int h = k-i;
                if(h>=m[i][j])
                    ret = m[i][j];
            }
        }

        return ret*ret;
    }

    @Test
    public void test() {
        Assert.assertEquals(9, solution(new String[]{"WBBB","WBBB","WWWW"}));
        Assert.assertEquals(4, solution(new String[]{"BB","WW"}));
        Assert.assertEquals(1, solution(new String[]{"W","B","W","W","W"}));


    }



}
