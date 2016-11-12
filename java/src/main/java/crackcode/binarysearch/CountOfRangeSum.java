package crackcode.binarysearch;

import org.junit.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 2016. 11. 11..
 */
public class CountOfRangeSum {
    int firstGreater(int[] s, int start, int end, int target){
        int l=start;
        int h=end;
        while (l<h){
            int m = l+(h-l)/2;
            if (s[m]<target){
                l=m+1;
            }else h=m;
        }
        if (s[l]>=target) return l;
        return -1;
    }

    int lastSmaller(int[] s, int start, int end, int target){
        int l=start;
        int h=end;
        while (l<h){
            int m = l+(h-l+1)/2;
            if (s[m]>target){
                h=m-1;
            }else l=m;
        }
        if (s[l]<=target) return l;
        return -1;
    }

    // not finished
    public int countRangeSum_bsearch(int[] nums, int lower, int upper) {
        int N = nums.length;
        if(N==0) return 0;
        int[] s = new int[N];
        int sum =0;


        for (int i = 0; i < N; i++){
            sum += nums[i];
            s[i]=sum;
        }
        int count=0;
        if(lower<=sum && sum<=upper) count++;
        Arrays.sort(s);
        for (int i = 0; i < N; i++){
            int start = firstGreater(s, i, N-1, lower+s[i]);
            if(start==-1) continue;
            int end = lastSmaller(s, start, N-1, upper+s[i]);
            if(end==-1) continue;
            count += end-start+1;
            System.out.println(i+":"+start +","+end);
        }
        return count;
    }

    static class Node{
        Node left, right;
        long v;
        int count=1, leftCount;

        Node(long v){
            this.v=v;
        }

        int lessThan(Node node, long high){
            if(node==null) return 0;

            if(node.v>high) return lessThan(node.left, high);
            else if(node.v < high) return node.leftCount+node.count + lessThan(node.right,high);
            else return node.leftCount;
        }

        void insert(long newvalue){
            if(newvalue<v){
                leftCount++;
                if(left==null) left = new Node(newvalue);
                else left.insert(newvalue);
            }else if(newvalue>v){
                if(right==null) right = new Node(newvalue);
                else right.insert(newvalue);
            }else count++;
        }
    }

    public int countRangeSum(int[] nums, long lower, long upper) {
        int N = nums.length;
        if(N==0) return 0;
        int count=0;

        long sum=0;
        Node root = new Node(0);
        for (int i = 0; i < N; i++){
            sum+=nums[i];
            int left = root.lessThan(root, -upper + sum);
            int right = root.lessThan(root, -lower + sum+1);
            count += right-left;
            //System.out.println(count);
            root.insert(sum);
        }
        return count;
    }

    public int countRangeSum_treeset(int[] nums, long lower, long upper) {
        int N = nums.length;
        if(N==0) return 0;
        int count=0;

        long sum=0;
        TreeSet<Long> set = new TreeSet<>();
        set.add(0l);
        for (int i = 0; i < N; i++){
            sum+=nums[i];
            Set<Long> filtered = set.subSet(-upper + sum, true, -lower + sum, true);
            count += filtered.size();
            //System.out.println(count);
            set.add(sum);
        }
        return count;
    }


    @Test
    public void test_treeset(){
        assertEquals(4, countRangeSum_treeset(new int[]{2147483647, -2147483648, -1, 0}, -1, 0));
        assertEquals(3, countRangeSum_treeset(new int[]{-2, 5, -1}, -2, 2));
        assertEquals(3, countRangeSum_treeset(new int[]{-2, 3, 2}, -2, 2));
        assertEquals(0, countRangeSum_treeset(new int[]{}, 1, 2));
        assertEquals(1, countRangeSum_treeset(new int[]{0}, 0, 0));
    }

    @Test
    public void test(){
        assertEquals(4, countRangeSum(new int[]{2147483647, -2147483648, -1, 0}, -1, 0));
        assertEquals(3, countRangeSum(new int[]{-2, 5, -1}, -2, 2));
        assertEquals(3, countRangeSum(new int[]{-2, 3, 2}, -2, 2));
        assertEquals(0, countRangeSum(new int[]{}, 1, 2));
        assertEquals(1, countRangeSum(new int[]{0}, 0, 0));
    }
}
