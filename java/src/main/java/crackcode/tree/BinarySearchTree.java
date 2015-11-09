package crackcode.tree;

import java.util.Stack;

import codejam.lib.CheckUtil;


public class BinarySearchTree {
	public static class Node {
		int key;
		int leftSubTreeCount;
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

    private static int sCount;

	public static void main(String[] args) {
		Node root = new Node(100);
		root.add(new Node(50));
		root.add(new Node(70));
		root.add(new Node(30));
		root.add(new Node(220));
		root.add(new Node(300));
		root.add(new Node(400));

		//          |-  100   -|
		//         50          220
		//   30 ---|- 70        |-- 300
		//                       	 |---400
		
		CheckUtil.check(2, countInRange(root, 50, 70));
		CheckUtil.check(7, countInRange(root, 0, 500));
        CheckUtil.check(2, countInRangeOptimized(root, 50, 70, Integer.MIN_VALUE, Integer.MAX_VALUE));
        CheckUtil.check(7, countInRangeOptimized(root, 0, 500, Integer.MIN_VALUE, Integer.MAX_VALUE));

        inorderTraversalIterative(root);

		Node target = findKthNode(root, 5);
        CheckUtil.check(220, target.key);
        
		int[] ret = findTwoNodeValueEqualToX(root, 290);
		CheckUtil.check(70, ret[0]);
		CheckUtil.check(220, ret[1]);

		Node r2 = new Node(5);
		ret = findTwoNodeValueEqualToX(r2, 7);
		CheckUtil.checkTrue(null == ret);

		//
		int[] sorted = { 1, 3, 5, 7, 9, 11, 15, 20, 25 };
		Node bst = sortedArrayToBalancedBST(sorted);
		CheckUtil.check(9, bst.key);
		System.out.println(bst);
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

    static int countInRangeOptimized(Node n, int from, int to, int curMin, int curMax) {
        if (n == null)
            return 0;
        if (curMax < from || curMin > to) {
            return 0;
        }
        int ret = 0;
        if (from <= n.key && n.key <= to)
            ret++;
        ret += countInRangeOptimized(n.left, from, to, curMin, n.key);
        ret += countInRangeOptimized(n.right, from, to, n.key, curMax);
        return ret;
    }

	static int getLessCount(Node n, int t) {
		if (n == null)
			return 0;
		if (n.key < t) {
			return 1 + n.leftSubTreeCount + getLessCount(n.right, t);
		}
		return getLessCount(n.left, t);
	}

	static Node findKthNode(Node n, int k) {
		sCount = 0;
		return findKthNodeInternal(n, k);
	}

    static Node findKthNodeInternal(Node n, int k) {
        if (n == null)
            return null;

        Node ret = null;
        ret = findKthNodeInternal(n.left, k);
        if (ret != null)
            return ret;

        sCount++;
        if (sCount == k) {
            return n;
        }
        ret = findKthNodeInternal(n.right, k);
        if (ret != null)
            return ret;

		return ret;
	}
    
	// return node value[]
	// http://www.careercup.com/question?id=15320677
	static int[] findTwoNodeValueEqualToX(Node root, int sum) {
		Stack<Node> left = new Stack<Node>();
		Stack<Node> right = new Stack<Node>();
		Node n = root;
		while (n != null) {
			left.push(n);
			n = n.left;
		}
		n = root;
		while (n != null) {
			right.push(n);
			n = n.right;
		}

		while (left.peek() != right.peek()) {
			int cur = left.peek().key + right.peek().key;
			if (cur > sum) {
				n = right.pop().left;
				while (n != null) {
					right.push(n);
					n = n.right;
				}
			} else if (cur < sum) {
				n = left.pop().right;
				while (n != null) {
					left.push(n);
					n = n.left;
				}
			} else {
				int[] ret = new int[2];
				ret[0] = left.peek().key;
				ret[1] = right.peek().key;
				return ret;
			}
		}

		return null;
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

	// http://www.geeksforgeeks.org/sorted-array-to-balanced-bst/
	// http://www.careercup.com/question?id=5261732222074880
	// O(n) solution
	public static Node sortedArrayToBalancedBST(int[] a) {
		return sortedArrayToBalancedBSTInternal(a, 0, a.length - 1);
	}

	private static Node sortedArrayToBalancedBSTInternal(int[] a, int start, int end) {
		if (start > end)
			return null;

		int m = (start + end) / 2;
		Node root = new Node(a[m]);
		root.left = sortedArrayToBalancedBSTInternal(a, start, m - 1);
		root.right = sortedArrayToBalancedBSTInternal(a, m + 1, end);

		return root;
	}
}
