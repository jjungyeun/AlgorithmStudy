# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def deleteMiddle(self, head: Optional[ListNode]) -> Optional[ListNode]:
        # return None if list length is 1
        if head.next == None:
            return None
        
        # find middle node at slow
        slow, fast = head, head
        while fast.next and fast.next.next:
            slow, fast = slow.next, fast.next.next
        
        # if list length is even, delete next node
        if fast.next:
            slow.next = slow.next.next
        # if list length is odd, delete current node
        else:
            slow.val, slow.next = slow.next.val, slow.next.next
        
        return head
