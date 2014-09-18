package crackcode;

import java.util.LinkedList;
import java.util.Stack;

public class QueueWith2Stack {
	Stack<Integer> add = new Stack<Integer>();
	Stack<Integer> del = new Stack<Integer>();

	void enqueue(int v) {
		if (!del.isEmpty()) {
			while (!del.isEmpty()) {
				int nv = del.pop();
				add.push(nv);
			}

		}

		add.push(v);
	}

	int dequeue() {
		if (!add.isEmpty()) {

			while (!add.isEmpty()) {
				int nv = add.pop();
				del.push(nv);
			}
		}
		if (del.isEmpty()) {
			return -1;
		}

		return del.pop();
	}

	void print() {
		System.out.println(add.toString());
		System.out.println(del.toString());
	}

	public static void main(String[] args) {
		QueueWith2Stack q = new QueueWith2Stack();

		q.enqueue(2);
		q.enqueue(5);
		q.enqueue(7);
		q.print();

		int v = q.dequeue();
		q.print();

		q.enqueue(100);
		q.print();

		LinkedList<Integer> l = new LinkedList<Integer>();
		l.poll();
	}


}
