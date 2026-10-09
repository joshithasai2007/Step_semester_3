
public class Problem4 {
    static void analyzeInventory(int[] sectionA, int[] sectionB) {
        if (sectionA.length != sectionB.length) {
            System.out.println("Arrays must have equal length");
            return;
        }

        int totalA = 0, totalB = 0;

        for (int i = 0; i < sectionA.length; i++) {
            totalA += sectionA[i];
            totalB += sectionB[i];
        }

        String status = (totalA == totalB)
                ? "Balanced" : "Not Balanced";

        if (sectionA.length == 0) {
            System.out.println("Section A Total: " + totalA
                    + " | Section B Total: " + totalB
                    + " | Status: " + status
                    + " | No items available");
            return;
        }

        int highest = sectionA[0];
        String section = "Section A";
        int item = 1;

        for (int i = 0; i < sectionA.length; i++) {
            if (sectionA[i] > highest) {
                highest = sectionA[i];
                section = "Section A";
                item = i + 1;
            }

            if (sectionB[i] > highest) {
                highest = sectionB[i];
                section = "Section B";
                item = i + 1;
            }
        }

        System.out.println("Section A Total: " + totalA
                + " | Section B Total: " + totalB
                + " | Status: " + status
                + " | Highest Quantity: " + highest
                + " (" + section + ", Item " + item + ")");
    }

    public static void main(String[] args) {
        int[] sectionA = {20, 15, 30};
        int[] sectionB = {25, 10, 30};

        analyzeInventory(sectionA, sectionB);
    }
}