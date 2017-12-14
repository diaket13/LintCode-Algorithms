package questionsList.title167;

/**
 * Definition for singly-linked list.
 */
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}

public class Title167 {
	/*
	 * @param l1: the first list
	 * 
	 * @param l2: the second list
	 * 
	 * @return: the sum list of l1 and l2
	 */
	public ListNode addLists(ListNode l1, ListNode l2) {
		// write your code here
		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}
		int number = l1.val + l2.val;
		boolean isCarry = false;
		if (number > 9) {
			number -= 10;
			isCarry = true;
		}
		ListNode l3 = new ListNode(number);
		ListNode l4 = l3;
		while (l1.next != null || l2.next != null) {
			if (l1.next == null) {
				if (!isCarry) {
					l3.next = l2.next;
					break;
				} else {
					l2 = l2.next;
					int num = l2.val + 1;
					if (num > 9) {
						isCarry = true;
						l3.next = new ListNode(0);
						l3 = l3.next;
					} else {
						isCarry = false;
						l3.next = new ListNode(num);
						l3 = l3.next;
						l3.next = l2.next;
						break;
					}
				}
			} else if (l2.next == null) {
				if (!isCarry) {
					l3.next = l1.next;
					break;
				} else {
					l1 = l1.next;
					int num = l1.val + 1;
					if (num > 9) {
						isCarry = true;
						l3.next = new ListNode(0);
						l3 = l3.next;
					} else {
						isCarry = false;
						l3.next = new ListNode(num);
						l3 = l3.next;
						l3.next = l1.next;
						break;
					}
				}
			} else {
				int num = l1.next.val + l2.next.val;
				if (isCarry) {
					num++;
				}
				if (num > 9) {
					isCarry = true;
					l3.next = new ListNode(num - 10);
				} else {
					isCarry = false;
					l3.next = new ListNode(num);
				}
				l3 = l3.next;
				l1 = l1.next;
				l2 = l2.next;
			}
		}
		if (isCarry) {
			l3.next = new ListNode(1);
		}
		return l4;
	}
}
