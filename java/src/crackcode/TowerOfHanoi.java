package crackcode;

import java.util.Stack;

public class TowerOfHanoi {
	static int callCount;
	static class Tower {
		Stack<Integer> mStack = new Stack<Integer>();
		int mIndex;

		Tower(int idx) {
			mIndex = idx;
		}

		void push(int v) {
			if (!mStack.isEmpty()) {
				if (mStack.peek() < v) {
					System.out.println("wrong move");
					return;
				}
			}
			mStack.push(v);
		}

		void moveTower(int n, Tower dest, Tower buf) {
			if (n <= 0)
				return;

			moveTower(n - 1, buf, dest);
			moveTopTo(dest);
			buf.moveTower(n - 1, dest, this);
			callCount++;
		}

		private void moveTopTo(Tower dest) {
			int v = mStack.pop();
			dest.push(v);
		}

		void print() {
			System.out.println(mStack.toString());
		}

	}



	
	public static void main(String[] args) {
		Tower[] towers = new Tower[3];
		for (int i = 0; i < towers.length; i++) {
			towers[i] = new Tower(i);
		}
		int N = 4;
		for (int i = N; i > 0; i--) {
			towers[0].push(i);
		}

		towers[0].moveTower(N, towers[2], towers[1]);
		towers[0].print();
		towers[1].print();
		towers[2].print();
		System.out.println("call count: " + callCount);
	}




}
