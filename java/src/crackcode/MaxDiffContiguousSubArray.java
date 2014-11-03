package crackcode;

import codejam.lib.CheckUtil;

// http://www.careercup.com/question?id=19286747
public class MaxDiffContiguousSubArray {

    public static void main(String[] args) {
        int a[] = { 2, -1, -2, 1, -4, 2, 8 };

        CheckUtil.check(16, maxDiff(a));
    }

    public static int maxDiff(int[] a) {
        int len = a.length;
        int[] min = new int[len];
        int[] max = new int[len];
        int[] revMin = new int[len];
        int[] revMax = new int[len];

        //
        min[0] =max[0] = a[0];
        for (int i = 1; i < len; i++){
            max[i] = max[i-1] >0 ? max[i-1]+a[i] : a[i];
            min[i] = min[i - 1] > 0 ? a[i] : min[i - 1] + a[i];
        }

        //reverse
        revMin[len - 1] = revMax[len - 1] = a[len - 1];
        for (int i = len-2; i >=0; i--){
            revMax[i] = revMax[i + 1] > 0 ? revMax[i + 1] + a[i] : a[i];
            revMin[i] = revMin[i + 1] > 0 ? a[i] : revMin[i + 1] + a[i];
        }

        // find max diff
        int maxDiff = -1;
        int split = -1;
        for (int i = 0; i < len - 1; i++) {
            int cur = Math.abs(revMax[i+1]-min[i]);
            if (maxDiff<cur){
                maxDiff =cur;
                split=i;
            }
            cur = Math.abs( max[i]-revMin[i+1] );
            if (maxDiff<cur){
                maxDiff =cur;
                split=i;
            }
        }

        System.out.println(String.format("max=%d,split=%d", maxDiff, split));
        return maxDiff;
    }

}
