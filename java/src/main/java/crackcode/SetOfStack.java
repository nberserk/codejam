package crackcode;

import java.util.ArrayList;
import java.util.Stack;

public class SetOfStack {
	ArrayList<Stack<Integer>> mStacks = new ArrayList<Stack<Integer>>();
	static final int LIMIT = 10;

	public SetOfStack() {
		mStacks.add(new Stack<Integer>());
	}

	public static void main(String[] args) {
		SetOfStack stack = new SetOfStack();

		for (int i = 0; i < 13; i++) {
			stack.push(i);
		}

		System.out.println(stack.toString());

		stack.popAt(0);

		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
		// stack.pop();

		System.out.println(stack.toString());
	}
	
	void push(int v){
		Stack<Integer> s = mStacks.get(mStacks.size() - 1);
		if (s == null || s.size() == LIMIT) {
			s = new Stack<Integer>();
			mStacks.add(s);
		}
		s.push(v);
	}

	int pop() {
		if (mStacks.size() == 0) {
			return -1;
		}
		Stack<Integer> s = mStacks.get(mStacks.size() - 1);

		int ret = s.pop();
		if (s.size() == 0) {
			mStacks.remove(mStacks.size() - 1);
		}
		return ret;
	}

	int popAt(int idx) {
		Stack<Integer> s = mStacks.get(idx);
		int ret = s.pop();
		if (s.size() == 0) {
			mStacks.remove(idx);
		}
		return ret;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (Stack<Integer> stack : mStacks) {
			s.append(stack.toString());
		}
		return s.toString();
	}
}
