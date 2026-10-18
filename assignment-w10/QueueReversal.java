// Custom Node class
class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class QueueReversal {

    // Method to reverse the singly linked list in-place
    public static Node reverse(Node head) {
        Node prev = null;
        Node current = head;
        
        while (current != null) {
            Node nextNode = current.next; // Temporarily store the next node
            current.next = prev;          // Reverse the current node's pointer
            prev = current;               // Move prev one step forward
            current = nextNode;           // Move current one step forward
        }
        
        // prev will be pointing to the new head of the reversed list
        return prev;
    }

    // Helper method to print the linked list format
    public static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // Helper method to build a list from an array of values
    public static Node buildList(int[] values) {
        if (values.length == 0) return null;
        Node head = new Node(values[0]);
        Node current = head;
        for (int i = 1; i < values.length; i++) {
            current.next = new Node(values[i]);
            current = current.next;
        }
        return head;
    }

    public static void main(String[] args) {
        // --- Sample 1 Test ---
        int[] queue1 = {1, 2, 3, 4};
        Node head1 = buildList(queue1);

        System.out.print("Sample 1 Input:  ");
        printList(head1);
        head1 = reverse(head1);
        System.out.print("Sample 1 Output: ");
        printList(head1);

        System.out.println();

        // --- Sample 2 Test ---
        int[] queue2 = {9};
        Node head2 = buildList(queue2);

        System.out.print("Sample 2 Input:  ");
        printList(head2);
        head2 = reverse(head2);
        System.out.print("Sample 2 Output: ");
        printList(head2);
    }
}