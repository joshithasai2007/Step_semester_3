
class Inventory {
    void parseInventoryRecord(String csvLine) {
        String[] fields = csvLine.split(",", -1);

        if (fields.length != 3) {
            System.out.println("Invalid Record");
        } else {
            System.out.println("Product: " + fields[0]
                    + " | SKU: " + fields[1]
                    + " | Qty: " + fields[2]);
        }
    }
}

public class Problem3 {
    public static void main(String[] args) {
        Inventory i = new Inventory();

        i.parseInventoryRecord("Wireless Mouse,WM-2201,150");
        i.parseInventoryRecord("Wireless Mouse,150");
    }
}