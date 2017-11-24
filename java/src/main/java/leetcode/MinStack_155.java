package leetcode;


import junit.framework.TestCase;
import org.junit.Test;

import java.util.Stack;


public class MinStack_155 extends TestCase{
    Stack<Integer> s = new Stack<>();
    Stack<Integer> min = new Stack<>();

    /** initialize your data structure here. */
    public MinStack_155() {
    }

    public void push(int x) {
        s.push(x);
        if(min.size()>0)
            min.push(Math.min(x, min.peek()));
        else min.push(x);
    }

    public void pop() {
        s.pop();
        min.pop();
    }

    public int top() {
        return s.peek();
    }

    public int getMin() {
        return min.peek();
    }

    @Test
	public void testcheck(){
		MinStack_155 m = new MinStack_155();
        m.push(1);
        m.push(-1);
        m.push(7);

        assertEquals(7, m.top());
        assertEquals(-1, m.getMin());
        m.pop();

        assertEquals(-1, m.getMin());
        assertEquals(-1, m.top());

        m.pop();
        assertEquals(1, m.getMin());
	}

}
