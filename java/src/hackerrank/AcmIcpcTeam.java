package hackerrank;

import java.util.Scanner;

/**
 * Created by darren on 10/20/15.
 */
public class AcmIcpcTeam {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int M = s.nextInt();
        String m[] = new String[N];
        for (int i=0;i<N;i++){
            m[i] = s.next();
        }

        //
        int max = 0;
        int count[]  = new int[M];

        for (int i=0;i<N;i++){
            String f = m[i];
            for (int j=i+1;j<N;j++){
                String se = m[j];

                int c = 0;
                for(int k=0;k<M;k++){
                    if( f.charAt(k) ==1 || se.charAt(k)==1) {
                        c++;
                        continue;
                    }
                }
                count[c] ++;
                if(c>max) max = c;
            }
        }
        System.out.println(max);
        System.out.println(count[max]);
    }


}
