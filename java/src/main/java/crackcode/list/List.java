package main.java.crackcode.list;

import codejam.lib.CheckUtil;

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
            String s = String.format("%d ", data);
            if (next != null)
                s += next.toString();
            return s;
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

        List circular = new List();
        inserted = circular.insertAfter(null, 1);
        inserted = circular.insertAfter(inserted, 2);
        inserted = circular.insertAfter(inserted, 3);
        Node n3 = inserted;
        inserted = circular.insertAfter(inserted, 4);
        inserted = circular.insertAfter(inserted, 5);
        inserted = circular.insertAfter(inserted, 6);
        inserted.next = n3;

        Node start = circular.findStartOfLoop();
        CheckUtil.check(n3.data, start.data);


        // reverse
        List r = new List();

        inserted = r.insertAfter(null, 1);
        CheckUtil.check("1 ", r.head.toString());
        inserted = r.insertAfter(inserted, 2);
        inserted = r.insertAfter(inserted, 3);
        inserted = r.insertAfter(inserted, 4);
        CheckUtil.check("1 2 3 4 ", r.head.toString());

        Node newHead = r.reverseIterative(r.head);
        r.head = newHead;
        CheckUtil.check("4 3 2 1 ", newHead.toString());

        newHead = r.reverseRecursive(r.head, null);
        CheckUtil.check("1 2 3 4 ", newHead.toString());
        r.head = newHead;
        // end of reverse

		testReverseEveryK();
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

    Node findStartOfLoop() {
        if (head == null) {
            return null;
        }

        Node slow = head;
        Node fast = head;
        while (fast != null) {
            fast = fast.next;
            if (fast == null) {
                return null;
            } else {
                fast = fast.next;
            }
            slow = slow.next;

            if (slow == fast) {
                System.out.println("met first");
                break;
            }
        }
        slow = head;

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
            if (slow == fast) {
                System.out.println("second first");
                return slow;
            }
        }

        return null;
    }

    // return new head;
    Node reverseIterative(Node head) {
        if (head == null)
            return head;

        Node prev = null;
        Node cur = head;
        Node next;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;

            prev = cur;
            cur = next;
        }

        return prev;
    }

    Node reverseRecursive(Node cur, Node prev) {
        if (cur == null)
            return null;

        Node next = cur.next;
        cur.next = prev;

        Node ret = reverseRecursive(next, cur);
        if (ret == null)
            ret = cur;
        return ret;
    }

	/*
	 * returns new head
	 */
	Node reverseEveryKNodes(Node head, Node prev, int k) {
        if (head == null)
            return null;

		Node nextPrev = head;
		Node p = null;
		Node n = null;
		Node cur = head;
		int count = 0;
		while (cur != null) {
			n = cur.next;
			cur.next = p;
			count++;
			if (count == k)
				break;

			p = cur;
			cur = n;
		}

		if (prev != null)
			prev.next = cur;
		reverseEveryKNodes(n, nextPrev, k);
		return cur;
    }

	private static void testReverseEveryK() {
		List n = new List();
		Node i = n.insertAfter(null, 1);
		i = n.insertAfter(i, 2);
		i = n.insertAfter(i, 3);
		i = n.insertAfter(i, 4);
		i = n.insertAfter(i, 5);
		i = n.insertAfter(i, 6);
		i = n.insertAfter(i, 7);
		i = n.insertAfter(i, 8);
		i = n.insertAfter(i, 9);

		CheckUtil.check("1 2 3 4 5 6 7 8 9 ", n.head.toString());

		Node newHead = n.reverseEveryKNodes(n.head, null, 3);
		CheckUtil.check("3 2 1 6 5 4 9 8 7 ", newHead.toString());

		newHead = n.reverseEveryKNodes(newHead, null, 3);
		CheckUtil.check("1 2 3 4 5 6 7 8 9 ", newHead.toString());

		newHead = n.reverseEveryKNodes(newHead, null, 4);
		CheckUtil.check("4 3 2 1 8 7 6 5 ", newHead.toString());

	}

}
