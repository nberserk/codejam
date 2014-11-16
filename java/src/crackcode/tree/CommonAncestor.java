package crackcode.tree;

import java.util.ArrayList;

public class CommonAncestor {

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

    static Node commonAncestor(Node parent, Node p, Node q) {
        if (isChild(parent.left, p) && isChild(parent.left, q)) {
            return commonAncestor(parent.left, p, q);
        }
        if (isChild(parent.right, p) && isChild(parent.right, q)) {
            return commonAncestor(parent.right, p, q);
        }

        return parent;
    }

    static boolean isChild(Node p, Node c){
        if (p == null || c == null) {
            return false;
        }

        if (p==c) {
            return true;
        }
        return isChild(p.left, c) || isChild(p.right, c);
    }

    public static void main(String[] args) {
        Node root = new Node(100);
        Node n1 = new Node(10);
        Node n7 = new Node(70);
        root.add(new Node(50));
        root.add(n7);
        root.add(new Node(20));
        root.add(n1);

        Node ancestor = commonAncestor(root, n1, n7);
        ancestor.print();

		ArrayList<Node> path = findPath(root, n1, n7);
		System.out.println(path.toString());
    }

	static void findPath(Node root, Node target, ArrayList<Node> path) {
		if (root.key == target.key) {
			return;
		}

		if (isChild(root.left, target)) {
			path.add(root.left);
			findPath(root.left, target, path);
		} else {
			path.add(root.right);
			findPath(root.right, target, path);
		}
	}

	static ArrayList<Node> findPath(Node root, Node a, Node b) {
		if (root == null) {
			return null;
		}

		if (a.key == root.key) {
			ArrayList<Node> r1 = new ArrayList<Node>();
			findPath(root, b, r1);
			return r1;
		} else if (root.key == b.key) {
			ArrayList<Node> r1 = new ArrayList<Node>();
			findPath(root, a, r1);
			return r1;
		} else {
			if (isChild(root.left, a) && isChild(root.left, b)) {
				return findPath(root.left, a, b);
			} else if (isChild(root.right, a) && isChild(root.right, b)) {
				return findPath(root.right, a, b);
			} else {
				ArrayList<Node> r1 = new ArrayList<Node>();
				findPath(root, a, r1);
				ArrayList<Node> r2 = new ArrayList<Node>();
				findPath(root, b, r2);

				ArrayList<Node> result = new ArrayList<Node>();
				for (int i = r1.size() - 1; i >= 0; i--) {
					result.add(r1.get(i));
				}
				result.add(root);
				result.addAll(r2);
				return result;
			}
		}
	}

}
