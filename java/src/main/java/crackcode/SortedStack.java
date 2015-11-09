package crackcode;

import java.util.Arrays;
import java.util.Stack;

public class SortedStack {


	Stack<Integer> mStack = new Stack<Integer>();

	void push(int v) {

		Stack<Integer> t = new Stack<Integer>();

		while (!mStack.isEmpty()) {
			if (mStack.peek() > v) {
				break;
			}
			t.push(mStack.pop());
		}
		mStack.push(v);
		while (!t.isEmpty()) {
			mStack.push(t.pop());
		}
	}

	void print() {
		System.out.println(mStack.toString());
	}

	public static void main(String[] args) {
		Stack<Integer> s = new Stack<Integer>();
		s.push(3);
		s.push(7);
		s.push(1);
		s.push(10);

		Stack<Integer> sorted = sortStack(s);
		System.out.println(sorted.toString());

		int[] st = new int[10];
		int top = 0;

		Arrays.binarySearch(st, 0, 10, 5);

		st[top++] = 1;
		System.out.println(top);
	}

	static Stack<Integer> sortStack(Stack<Integer> s) {
		Stack<Integer> r = new Stack<Integer>();
		while (!s.isEmpty()) {
			int tmp = s.pop();

			while (r.isEmpty() == false && r.peek() > tmp) {
				s.push(r.pop());
			}
			r.push(tmp);
		}

		return r;
	}

}
