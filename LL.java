import java.util.List;
class LL{
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

    ListNode head;
    public void insertlinkedlist(int data){
        ListNode newnode=new ListNode(data);
        if(head==null){
            head=newnode;
            return;
        }
        ListNode temp=head;
        while (temp.next!=null){
            temp=temp.next;
        }
        temp.next=newnode;
    }

    public void printLL(){
        ListNode temp=head;
        while (temp!=null){
            System.out.print(temp.val+" -> ");
            temp=temp.next;
        }
        if(temp==null){
            System.out.print("null");
        }
    }
    public boolean hasCycle(ListNode head) {

        if(head==null || head.next==null){
            return false;
        }

        ListNode slow=head;
        ListNode fast=head;

        while (fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast){
                return true;
            }
        }
        return false;
    }
//    https://leetcode.com/problems/merge-two-sorted-lists/
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        while (list1!=null && list2!=null){
            if(list1.val<=list2.val){
                curr.next=list1;
                list1=list1.next;
            }else{
                curr.next=list2;
                list2=list2.next;
            }
            curr=curr.next;
        }
        curr.next = (list1 != null) ? list1 : list2;

        return dummy.next;


    }





    public void printLL(ListNode node){
        while (node != null){
            System.out.print(node.val + " -> ");
            node = node.next;
        }
        System.out.print("null");
    }


//    public boolean isPalindrome(ListNode head) {
//
//        if (head == null || head.next == null) {
//            return true;
//        }
//
//        ListNode slow=head;
//        ListNode fast=head;
//
//        while (fast != null && fast.next != null) {
//            slow = slow.next;
//            fast = fast.next.next;
//        }
//
//        slow=slow.next;
//            fast=fast.next.next;
//        }
//        ListNode secondhalf=reversell(slow);
//        ListNode firsthalf=head;
//
//        while (secondhalf!=null){
//            if(firsthalf.val!=secondhalf.val){return false;};
//            firsthalf=firsthalf.next;
//            secondhalf=secondhalf.next;
//
//        }
//        return  true;
//    }

    private ListNode reversell(ListNode head){

        ListNode prev=null;
        while (head!=null){
            ListNode nextnode=head.next;
            prev=head;
            head=nextnode;

        }
        return prev;
    }


    public static void main(String[] args) {
        LL l1=new LL();
        l1.insertlinkedlist(1);
        l1.insertlinkedlist(2);
        l1.insertlinkedlist(2);
        l1.insertlinkedlist(1);


//        System.out.println(l1.isPalindrome(l1.head));



//        LL l2=new LL();
//        l2.insertlinkedlist(1);
//        l2.insertlinkedlist(3);
//        l2.insertlinkedlist(4);
//
//        ListNode ans= l1.mergeTwoLists(l1.head, l2.head);
//
//        l1.printLL(ans);


    }
}