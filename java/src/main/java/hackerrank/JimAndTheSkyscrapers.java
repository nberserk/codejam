package hackerrank;

import org.junit.Test;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 2016. 11. 9.
 *
 * from: https://www.hackerrank.com/challenges/jim-and-the-skyscrapers
 *
 */
public class JimAndTheSkyscrapers {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();

        int[] height = new int[N];
        for (int i = 0; i < N; i++) {
            height[i] = s.nextInt();
        }
        
        int r = solve_Stack(height);
        System.out.println(r);
    }

    private static int solve_Stack(int[] height) {
        int N = height.length;
        Stack<Integer> stack = new Stack<>();

        int count=0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            // discard

            while(!stack.isEmpty()&&stack.peek()<height[i]){
                int v=stack.pop();
                if(map.containsKey(v))
                    map.put(v, map.get(v)+1);
                else map.put(v, 1);
            }
            for (int k : map.keySet()){
                int c = map.get(k);
                //System.out.println(k+":"+c);
                count+=c*(c-1);
            }
            map.clear();

            // add
            stack.push(height[i]);
        }
        // process remaining stack
        while(!stack.isEmpty()){
            int v=stack.pop();
            if(map.containsKey(v))
                map.put(v, map.get(v)+1);
            else map.put(v, 1);
        }
        for (int k : map.keySet()){
            int c = map.get(k);
            //System.out.println(k+":"+c);
            count+=c*(c-1);
        }

        return count;
    }

    @Test
    public void test(){
        int[] a = {3, 2, 1, 2, 3, 3};
        assertEquals(8, solve_Stack(a));
    }

}
