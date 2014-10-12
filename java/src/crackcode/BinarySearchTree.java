package crackcode;

import java.util.Stack;

import codejam.lib.CheckUtil;


public class BinarySearchTree {
	public static class Node {
		int key;
		Node left, right;

		Node(int v) {
			this.key = v;
		}

		void add(Node n) {
			if (n.key > key) {
				if (right != null) {
					right.add(n);
				} else
					right = n;
			} else {
				if (left != null) {
					left.add(n);
				} else
					left = n;
			}
		}

		@Override
		public String toString() {
			return String.format("%d (%d,%d)", key, left == null ? -1
					: left.key, right == null ? -1 : right.key);
		}

		void print() {
			System.out.println(String.format("%d (%d,%d)", key,
					left == null ? -1 : left.key, right == null ? -1
							: right.key));
		}
	}

	public static void main(String[] args) {
		Node root = new Node(100);
		root.add(new Node(50));
		root.add(new Node(70));
		root.add(new Node(30));
		root.add(new Node(220));
		root.add(new Node(300));
		root.add(new Node(400));

		CheckUtil.check(2, countInRange(root, 50, 70));
		CheckUtil.check(7, countInRange(root, 0, 500));

		inorderTraversalIterative(root);

		Node out = null;
		findKthNode(root, -1, 5, out);
		System.out.println(out);
	}

	// see http://www.careercup.com/question?id=5165570324430848
	// inclusive
	static int countInRange(Node n, int min, int max) {
		if (n == null)
			return 0;
		int ret = 0;
		if(min <=n.key && n.key <=max)
			ret ++;
		if(n.key > min)
			ret += countInRange(n.left, min, max);
		if (n.key < max)
			ret += countInRange(n.right, min, max);
		return ret;
	}

	static int findKthNode(Node n, int start, int k, Node out) {
		if (out != null)
			return 0;
		if (n == null)
			return 0;
		int ret = start == -1 ? 0 : start;
		ret += findKthNode(n.left, start, k, out);
		ret++;
		if (ret == k) {
			out = n;
			return 0;
		}
		ret += findKthNode(n.right, ret, k, out);
		return ret;
	}

	// sounds strange
	static void inorderTraversalIterative(Node root) {
		Stack<Node> s = new Stack<Node>();
		Node cur = root;
		while (s.size() > 0 || cur != null) {
			if (cur != null) {
				s.push(cur);
				cur = cur.left;
			} else { // reach to leaf
				cur = s.pop();
				System.out.println(cur);
				cur = cur.right;
			}
		}
	}

}
