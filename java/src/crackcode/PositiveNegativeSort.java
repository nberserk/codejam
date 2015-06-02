package crackcode;

import java.util.Arrays;

public class PositiveNegativeSort {

	static boolean sDebug = true;

	public static void main(String[] args) {
        int[] n = {-1, 1, 3, -2, 2, 8};
        
		int[] n2 = { -1, 1, 4, -3, 7, 2, 9, -11 };
		customSort(n2);
	}

	static void customSort(int[] n){
		int N = n.length;
		int[] org = Arrays.copyOf(n, N);
        
		int d = N - 1;
		for (int i = N - 1; i >= 0; i--) {
			if (org[i] >= 0) {
				n[d] = org[i];
				d--;
			}
        }
		d = 0;
		for (int i = 0; i < N; i++) {
			if (org[i] >= 0) {
			} else {
				n[d] = org[i];
				d++;
			}
		}

		print(Arrays.toString(n));
    }

    static void print(String s){
		if (sDebug) {
			System.out.println(s);
		}
    }
}
