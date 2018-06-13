class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null)
            return null;

        ListNode curr, stable = null;
        curr = head;
        head = null;
        boolean duplicate = false;

        while(curr.next != null)
        {
            boolean nextIsSame = curr.val == curr.next.val;

            if(duplicate) {
                if(!nextIsSame)
                    duplicate = false;

                curr = curr.next;
                continue;
            }

            if(nextIsSame) {
                duplicate = true;
            }
            else {
                if(stable == null)
                    head = stable = curr;
                else {
                    stable.next = curr;
                    stable = stable.next;
                }
            }

            curr = curr.next;
        }

        if(duplicate)
        {
            if(stable == null)
                return null;

            stable.next = null;
        }
        else {
            if(stable == null)
                head = stable = curr;
            else
                stable.next = curr;
        }

        return head;
    }
}