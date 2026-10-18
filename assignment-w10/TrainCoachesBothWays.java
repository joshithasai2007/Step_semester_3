// Custom Doubly Linked Node class for Train Coaches
class Node {
    int data;
    Node next;
    Node prev;

    public Node(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

// Custom Doubly Linked List class representing the Train
class DoublyLinkedList {
    Node head;
    Node tail;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    // Add a coach to the end of the train - O(1) with tail pointer
    public void addLast(int coachId) {
        Node newNode = new Node(coachId);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    // Print coaches forward (Front to Back) - O(N)
    public void printForward() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // Print coaches backward (Back to Front) - O(N)
    public void printBackward() {
        Node current = tail;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.prev;
        }
        System.out.println("null");
    }

    // Reverse the train configuration in-place by swapping next and prev pointers
    public void reverseTrain() {
        Node current = head;
        Node temp = null;

        // Traverse and swap next and prev pointers for every node
        while (current != null) {
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev; // Move to the next node (which is now stored in prev due to swap)
        }

        // Adjust the head and tail pointers
        if (temp != null) {
            head = temp.prev;
        }
        
        // Update tail to point to the old head
        Node curr = head;
        while (curr != null && curr.next != null) {
            curr = curr.next;
        }
        tail = curr;
    }
}

public class TrainCoachesBothWays {
    public static void main(String[] args) {
        DoublyLinkedList train = new DoublyLinkedList();

        // Adding train coaches
        train.addLast(101);
        train.addLast(102);
        train.addLast(103);
        train.addLast(104);

        System.out.print("Forward Print (Front to Back):  ");
        train.printForward();

        System.out.print("Backward Print (Back to Front): ");
        train.printBackward();

        // Reversing the train configuration
        train.reverseTrain();

        System.out.print("After Reversing (Forward Print): ");
        train.printForward();
    }
}