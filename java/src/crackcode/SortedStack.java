package crackcode;

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
		SortedStack s = new SortedStack();
		s.push(3);
		s.push(7);
		s.print();

		s.push(1);
		s.print();

		s.push(10);
		s.print();

	}

}
