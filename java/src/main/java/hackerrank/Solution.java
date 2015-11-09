package hackerrank;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    public static void main(String[] args) {

        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
//        Scanner scanner = new Scanner(System.in);
//        canner(System.in);
//        int N = scanner.nextInt(); // # city
//        int B = scanner.nextInt(); // # clinic
//
//        int[] populations = new int[N];
//        for (int i=0;i<N;i++){
//            populations[i] = scanner.nextInt();
//        }
//
//        minKits(N, B, populations);


        test();
    }

    public static void check(int expected, int actual){
        if (expected!= actual){
            System.out.println(String.format("wrong anaswer. expected=%d, actual=%d", expected, actual));
        }
    }

    public static void test(){
//        int v = minKits(2, 7, new int[]{200000,500000});
//        check(100000, v);

        int v = minKits(2, 7, new int[]{2,14});
        check(2, v);

        v = minKits(2, 7, new int[]{20000,8});
        check(2859, v);
    }

    static class City{
        double ratio;
        int city;
    }


    public static int minKits(int N, int B, int[] populations){
        long sum = 0;
        for(int i=0;i<N;i++){
            sum += populations[i];
        }

        City [] city = new City[N];
        for(int i=0;i<N;i++){
            city[i] = new City();
            city[i].city = i;
            city[i].ratio = (double)populations[i]/(double)sum;
        }

        int[] clinic = new int[N];

        int remain = B;
        for(int i=0;i<N;i++){
            clinic[i] = (int)(city[i].ratio*B);
            if(clinic[i]==0)
                clinic[i] = 1;
            remain -= clinic[i];
        }

        Arrays.sort(city, new Comparator<City>() {
                    public int compare(City c1, City c2) {
                        return Double.compare(c1.ratio ,c2.ratio);
                    }
            }
        );

        int idx = N-1;
        for(int i=0;i<remain;i++){
            clinic[city[idx].city] ++;
            idx--;
        }

        int r = Integer.MIN_VALUE;
        for(int i=0;i<N;i++){
            int kit = populations[i]/clinic[i];
            long mod = sum %B;
            if (mod!=0) kit ++;
            if(kit>r)
                r = kit;
        }
        return r;
    }
}
