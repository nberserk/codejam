package crackcode;


import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;


public class MinStack extends TestCase{
	LinkedList<Integer> stack = new LinkedList<>();
    LinkedList<Integer> min = new LinkedList<>();

	void push(int v) {
	    stack.add(v);
        if(min.isEmpty())
            min.add(v);
        else
            min.add(Math.min(min.get(min.size()-1), v));
	}

	void pop() {
		stack.removeLast();
        min.removeLast();
	}

    int top(){
        return stack.peekLast();
    }

	int getCurrentMin() {
		return min.peekLast();
	}

    @Test
	public void testcheck(){
		MinStack m = new MinStack();
        m.push(1);
        m.push(-1);
        m.push(7);

        assertEquals(7, m.top());
        assertEquals(-1, m.getCurrentMin());
        m.pop();

        assertEquals(-1, m.getCurrentMin());
        assertEquals(-1, m.top());

        m.pop();
        assertEquals(1, m.getCurrentMin());
	}

}
