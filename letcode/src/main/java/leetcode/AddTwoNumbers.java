package leetcode;

/**
 * @date: 2022-05-17
 * @author: yangniuhaojiang
 * @title: AddTwoNumbers
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        int[] l1 = new int[]{9, 9, 9, 9, 9, 9, 9};
        ListNode listNode1 = toListNode(l1);
        int[] l2 = new int[]{9, 9, 9, 9};
        ListNode listNode2 = toListNode(l2);
        printListNode(addTwoNumbers(listNode1, listNode2));
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;

    }

    public static ListNode toListNode(int[] arr) {
        ListNode listNode = new ListNode(arr[0]);
        ListNode next = listNode;
        for (int i = 1; i < arr.length; i++) {
            next = addListNode(next, arr[i]);
        }
        return listNode;
    }

    public static ListNode addListNode(ListNode listNode, int val) {
        listNode.next = new ListNode(val);
        return listNode.next;
    }

    public static void printListNode(ListNode l) {
        while (l.next != null) {
            System.out.println(l.val);
            l = l.next;
        }
        System.out.println(l.val);
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
