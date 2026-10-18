// Custom Node class
class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

// Custom Singly Linked List class
class SinglyLinkedList {
    Node head;
    int size;

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    // 1. addFirst: Inserts token at the front (Priority) - O(1)
    public void addFirst(int token) {
        Node newNode = new Node(token);
        newNode.next = head;
        head = newNode;
        size++;
    }

    // 2. addLast: Inserts token at the end (Normal) - O(N)
    public void addLast(int token) {
        Node newNode = new Node(token);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    // 3. insertAt: Inserts token at a given 0-based position (Rescheduled) - O(index)
    public void insertAt(int index, int token) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        if (index == 0) {
            addFirst(token);
            return;
        }
        
        Node newNode = new Node(token);
        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }

    // Helper method to print the linked list format
    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();

        // Initialize starting list: 101 -> 102 -> 103
        list.addLast(101);
        list.addLast(102);
        list.addLast(103);

        // Perform operations from the example
        list.addFirst(100);
        list.addLast(104);
        list.addLast(150); // wait, let's look at the insertAt example
        // Example operations: addFirst(100), addLast(104), insertAt(2, 150)
        
        // Reset list for precise example match:
        SinglyLinkedList exampleList = new SinglyLinkedList();
        exampleList.addLast(101);
        exampleList.addLast(102);
        exampleList.addLast(103);

        System.out.print("Initial List: ");
        exampleList.printList();

        // Executing operations
        exampleList.addFirst(100);
        exampleList.addLast(104);
        exampleList.insertAt(2, 150);

        System.out.print("Expected Output: ");
        exampleList.printList();
    }
}