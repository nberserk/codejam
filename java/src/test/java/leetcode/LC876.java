package leetcode;


import leetcode.common.ListNode;

public class LC876 {

    public ListNode middleNode(ListNode head) {
        ListNode slow=head;
        ListNode fast=head.next;
        while(fast!=null){
            slow=slow.next;
            if(fast.next==null)
                return slow;
            fast=fast.next.next;
        }
        return slow;
    }


}
