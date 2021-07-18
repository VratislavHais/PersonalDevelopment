package com.vhais.reverse.node;

import java.util.Deque;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		ListNode current = head;
		for (int i = 2; i < 6; i++) {
			current.next = new ListNode(i);
			current = current.next;
		}
		head = reverseKGroup(head, 3);
		while (head != null) {
			System.out.println(head.val);
			head = head.next;
		}
	}

	public static ListNode reverseKGroup(ListNode s, int k) {
		ListNode head = new ListNode(0, s);
		ListNode current = s;
		ListNode groupStart = head;
		Deque<ListNode> stack = new LinkedList<>();
		while (current != null) {
			stack.push(current);
			current = current.next;
			if (stack.size() % k == 0) {
				while (!stack.isEmpty()) {
					groupStart.next = stack.poll();
					groupStart = groupStart.next;
				}
				groupStart.next = current;
			}
		}
		return head.next;
	}

	static class ListNode {
		int val;
		ListNode next;

		ListNode() {}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}
}
