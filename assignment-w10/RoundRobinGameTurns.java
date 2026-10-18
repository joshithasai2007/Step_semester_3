// Custom Node class
class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class RoundRobinGameTurns {

    // Method to simulate round-robin turns by rotating the linked list by k places
    public static Node rotateRoundRobin(Node head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // 1. Find the length of the list and locate the tail node
        int length = 1;
        Node current = head;
        while (current.next != null) {
            length++;
            current = current.next;
        }

        // Optimize k if it exceeds the length of the list
        k = k % length;
        if (k == 0) {
            return head;
        }

        // 2. Temporarily make the list circular by connecting tail to head
        current.next = head;

        // 3. Find the new tail: (length - k) steps from the head
        Node newTail = head;
        for (int i = 0; i < length - k - 1; i++) {
            newTail = newTail.next;
        }

        // 4. Set the new head and break the circular link
        Node newHead = newTail.next;
        newTail.next = null;

        return newHead;
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
        int[] queue1 = {1, 2, 3, 4, 5};
        Node head1 = buildList(queue1);
        int k1 = 2;

        System.out.print("Sample 1 Input:  ");
        printList(head1);
        head1 = rotateRoundRobin(head1, k1);
        System.out.print("Sample 1 Output: ");
        printList(head1);

        System.out.println();

        // --- Sample 2 Test ---
        int[] queue2 = {10, 20, 30};
        Node head2 = buildList(queue2);
        int k2 = 4;

        System.out.print("Sample 2 Input:  ");
        printList(head2);
        head2 = rotateRoundRobin(head2, k2);
        System.out.print("Sample 2 Output: ");
        printList(head2);
    }
}