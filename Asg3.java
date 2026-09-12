class BookInventory {
    private final int copiesTotal;
    private int copiesAvailable;

    public BookInventory(int copiesTotal) {
        this.copiesTotal = Math.max(0, copiesTotal);
        this.copiesAvailable = this.copiesTotal;
    }

    public void checkOut() {
        if (copiesAvailable > 0) {
            copiesAvailable--;
        }
        // Silently rejected if copiesAvailable == 0
    }

    public void checkIn() {
        if (copiesAvailable < copiesTotal) {
            copiesAvailable++;
        }
        // Silently rejected if inventory is already at full capacity
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public int getCopiesTotal() {
        return copiesTotal;
    }
}

public class Asg3 {
    public static void main(String[] args) {
        // Test Checkout Boundary
        BookInventory b1 = new BookInventory(3);
        b1.checkOut(); 
        b1.checkOut(); 
        b1.checkOut();
        b1.checkOut(); // 4th call - silently rejected
        System.out.println("Available after 4 checkouts: " + b1.getCopiesAvailable()); // Output: 0

        // Test Check-in Boundary
        BookInventory b2 = new BookInventory(3);
        b2.checkIn(); // Already full (3/3) - silently rejected
        System.out.println("Available after invalid checkin: " + b2.getCopiesAvailable()); // Output: 3
    }
}