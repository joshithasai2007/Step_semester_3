
public class Problem5 {
    static String reverseCustomerName(String customerName) {
        StringBuilder result = new StringBuilder(customerName);
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        String customerName = "Sunil";

        String reversed = reverseCustomerName(customerName);

        System.out.println("Original Name: " + customerName);
        System.out.println("Reversed Name: " + reversed);
    }
}