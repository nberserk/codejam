package crackcode;

public class BinaryTree {
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
		root.add(new Node(5));
		root.add(new Node(220));

		// root.print();
		int max = maxDepth(root);
		CheckUtil.check(2, maxDepth(root));
		CheckUtil.check(true, isBalanced(root));

		root.add(new Node(300));
		root.add(new Node(400));
		// root.print();
		max = maxDepth(root);
		CheckUtil.check(4, maxDepth(root));
		CheckUtil.check(false, isBalanced(root));
	}

	public static int maxDepth(Node n) {
		if (n == null) {
			return 0;
		}
		return 1 + Math.max(maxDepth(n.left), maxDepth(n.right));
	}

	public static int minDepth(Node n) {
		if (n == null)
			return 0;
		return 1 + Math.min(minDepth(n.left), minDepth(n.right));
	}

	public static boolean isBalanced(Node root) {
		int max = maxDepth(root);
		int min = minDepth(root);
		if (max - min > 1) {
			return false;
		}
		return true;
	}
}
