package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class _708 {
    private class Node {
        public int val;
        public Node next;

        public Node() {
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }

    public Node insert(Node head, int insertVal) {
        if (head == null) {
            Node n = new Node(insertVal, null);
            n.next = n;
            return n;
        }
        Node prev = head;
        Node n = head.next;
        while (n != prev) {

            if (prev.val <= n.val && prev.val <= insertVal && insertVal <= n.val) {
                Node newNode = new Node(insertVal, n);
                prev.next = newNode;
                return head;
            } else if (prev.val > n.val && (insertVal >= prev.val || insertVal <= n.val)) {
                Node newNode = new Node(insertVal, n);
                prev.next = newNode;
                return head;
            }
            if (n == head) {
                break;
            }
            prev = n;
            n = n.next;
        }

        // when only 1 elements, or 3->3->3 cases
        prev = head;
        n = head.next;
        Node newNode = new Node(insertVal, n);
        prev.next = newNode;
        return head;
    }


    @Test
    public void test() {
        Node root = new Node();
        root.val = 1;
        root.next = new Node();
        root.next.val = 3;
        root.next.next = new Node();
        root.next.next.val = 5;
        root.next.next.next = root;
        assertEquals(3, root.next.val);
        Node newRoot = insert(root, 2);
        assertEquals(2, newRoot.next.val);
    }

    @Test
    public void test_allSame() {
        Node root = new Node();
        root.val = 3;
        root.next = new Node();
        root.next.val = 3;
        root.next.next = new Node();
        root.next.next.val = 3;
        root.next.next.next = root;
        assertEquals(3, root.next.val);
        Node newRoot = insert(root, 0);
        assertEquals(0, newRoot.next.val);
    }
}
