package crackcode;

public class List {
	Node head, tail;

	static class Node {
		Node next;
		int data;

		Node(int d) {
			data = d;
		}
	}

	boolean insertAfter(Node n, int data) {
		if (n == null) {
			Node node = new Node(data);
			node.data = data;
			node.next = head;
			head = node;
			return true;
		}


		return true;
	}

	boolean delete(Node n) {
		if (n == null)
			return false;
		Node cur = head;
		Node prev = null;
		while (cur != null) {
			if (cur == n) {
				if (prev != null)
					prev.next = cur.next;
				// update head
				if (cur == head) {
					head = cur.next;
				}
				// update tail
				if (cur == tail)
					tail = prev;
				return true;
			}
			cur = cur.next;
		}

		return false;
	}

	public static void main(String[] args) {
		
		String t = "0123456789";
		System.out.println(t.charAt(9));
		int cp = t.codePointAt(0);
		int count = Character.charCount(cp);


		char[] c = t.toCharArray();
		String nn = new String(c);
		if (t.contains("1")) {
			System.out.println("t contains 1");
		}

		Node n1 = new Node(10);
		Node n2 = new Node(20);
		Node n3 = n1;

		System.out.println(n1 == n2 ? "same" : "not same");
		System.out.println(n1 == n3 ? "same" : "not same");

	}

}
