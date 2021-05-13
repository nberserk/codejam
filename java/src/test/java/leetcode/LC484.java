package leetcode;

import java.util.Stack;

import static org.junit.Assert.assertArrayEquals;

public class LC484 {

    public int[] findPermutation(String s) {
        int N = s.length()+1;
        int[] n = new int[N];
        Stack<Integer> stack = new Stack<>();
        int idx=0;
        int v=1;
        for (char c : s.toCharArray()){
            if(c=='I'){
                stack.push(v++);
                while(stack.size()>0){
                    n[idx++] = stack.pop();
                }
            }
            else{
                stack.push(v++);
            }
        }
        stack.push(v++);
        while(stack.size()>0){
            n[idx++] = stack.pop();
        }
        return n;
    }



    @org.junit.Test
    public void test(){
        assertArrayEquals(new int[]{2,1}, findPermutation("D"));
        assertArrayEquals(new int[]{1,2}, findPermutation("I"));
        assertArrayEquals(new int[]{1,5,4,3,2,9,8,7,6}, findPermutation("IDDDIDDD"));

    }
}
