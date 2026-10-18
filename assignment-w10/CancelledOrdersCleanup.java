// Custom Node class
class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class CancelledOrdersCleanup {

    // Method to remove all nodes with the given code
    public static Node removeAll(Node head, int code) {
        // 1. Remove all matching nodes from the beginning of the list
        while (head != null && head.data == code) {
            head = head.next;
        }

        // If list is empty after removing from the head
        if (head == null) {
            return null;
        }

        // 2. Traverse the rest of the list and bypass matching nodes
        Node current = head;
        while (current.next != null) {
            if (current.next.data == code) {
                current.next = current.next.next; // Bypass the node
            } else {
                current = current.next; // Move forward
            }
        }

        return head;
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
        int[] orders1 = {5, 3, 5, 8, 5};
        Node head1 = buildList(orders1);
        int code1 = 5;

        System.out.print("Sample 1 Input:  ");
        printList(head1);
        head1 = removeAll(head1, code1);
        System.out.print("Sample 1 Output: ");
        printList(head1);

        System.out.println();

        // --- Sample 2 Test ---
        int[] orders2 = {5, 5, 5};
        Node head2 = buildList(orders2);
        int code2 = 5;

        System.out.print("Sample 2 Input:  ");
        printList(head2);
        head2 = removeAll(head2, code2);
        System.out.print("Sample 2 Output: ");
        printList(head2);
    }
}