package crackcode;

public class List {
	Node head, tail;

	static class Node {
		Node next;
		int data;

		Node(int d) {
			data = d;
		}

		@Override
		public String toString() {
			return String.format("%d", data);
		}
	}

	int size() {
		int count = 0;
		Node cur = head;
		while (cur != null) {
			count++;
			cur = cur.next;
		}
		return count;
	}

	Node insertAfter(Node n, int data) {
		if (n == null) {
			Node node = new Node(data);
			node.data = data;
			node.next = head;
			head = node;
			return node;
		} else {
			Node cur = head;
			while (cur != null) {
				if (cur == n) {
					Node node = new Node(data);
					node.next = cur.next;
					cur.next = node;
					return node;
				}
				cur = cur.next;
			}
			return null;
		}
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
		List list = new List();
		CheckUtil.check(0, list.size());

		Node inserted = list.insertAfter(null, 1);
		CheckUtil.check(1, list.size());
		inserted = list.insertAfter(inserted, 5);
		inserted = list.insertAfter(inserted, 10);
		inserted = list.insertAfter(inserted, 20);
		CheckUtil.check(4, list.size());

		CheckUtil.check(true, list.deleteMiddle(list.head));
		CheckUtil.check(3, list.size());
	}

	boolean deleteMiddle(Node head) {
		Node slow = head, prev = null;
		Node fast = head;
		while (fast != null) {
			fast = fast.next;
			if (fast != null) {
				fast = fast.next;
			}
			if (fast == null) {
				if (prev != null) {
					prev.next = slow.next;
				}
				return true;
			}
			prev = slow;
			slow = slow.next;
		}

		return false;
	}
}
