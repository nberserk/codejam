package crackcode;

import codejam.lib.CheckUtil;

public class HStackMin {
	int[] data = new int[1024];
	int[] min = new int[1024];
	int top;

	void push(int v) {
		data[top++] = v;
		if (top==1) {
			min[top - 1] = v;
		} else {
			min[top - 1] = Math.min(v, min[top - 2]);
		}

	}

	int pop() {
		if (top == 0) {
			return -1;
		}
		return data[--top];
	}

	int getCurrentMin() {
		if (top == 0) {
			return -1;
		}
		return min[top - 1];
	}

	public static void main(String[] args) {
		HStackMin s = new HStackMin();
		CheckUtil.check(-1, s.pop());

		s.push(100);
		CheckUtil.check(100, s.getCurrentMin());
		s.push(1);
		CheckUtil.check(1, s.getCurrentMin());
		s.push(5);
		CheckUtil.check(1, s.getCurrentMin());
		s.push(-5);
		CheckUtil.check(-5, s.getCurrentMin());

		CheckUtil.check(-5, s.pop());
		CheckUtil.check(5, s.pop());
		CheckUtil.check(1, s.pop());
		CheckUtil.check(100, s.pop());
		CheckUtil.check(-1, s.pop());
		
		System.out.println("HStack ended");
	}

}
