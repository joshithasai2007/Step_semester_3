
import java.util.*;

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Product {
    String name;
    double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class Order {
    String id;
    Customer customer;
    Map<Product, Integer> items = new LinkedHashMap<>();
    String status = "Pending";

    Order(String id, Customer customer) {
        this.id = id;
        this.customer = customer;
        System.out.println("Order created for " + customer.name);
    }

    void addProduct(Product p, int quantity) {
        if (quantity <= 0) {
            System.out.println("Invalid quantity.");
            return;
        }
        items.put(p, items.getOrDefault(p, 0) + quantity);
    }

    double getTotal() {
        double total = 0;
        for (Product p : items.keySet()) {
            total += p.price * items.get(p);
        }
        return total;
    }

    void pay(PaymentMethod method) {
        if (items.isEmpty()) {
            System.out.println("Cannot process payment for an empty order.");
            return;
        }

        if (status.equals("Paid")) {
            System.out.println("Order is already paid.");
            return;
        }

        System.out.println("Payment initiated via " +
                method.getName() + " for Order " + id);

        if (method.processPayment(getTotal())) {
            status = "Paid";
            System.out.println("Payment for Order " + id + " successful.");
        } else {
            System.out.println("Payment for Order " + id + " failed.");
        }

        System.out.println("Order status: " + status);
    }
}

interface PaymentMethod {
    boolean processPayment(double amount);
    String getName();
}

class CreditCardPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        return true;
    }

    public String getName() {
        return "Credit Card";
    }
}

class PayPalPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        return false;
    }

    public String getName() {
        return "PayPal";
    }
}

class BankTransferPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        return true;
    }

    public String getName() {
        return "Bank Transfer";
    }
}

public class Problem5 {
    public static void main(String[] args) {
        Product a = new Product("Product A", 100);
        Product b = new Product("Product B", 200);
        Product c = new Product("Product C", 300);

        Customer x = new Customer("Customer X");
        Customer y = new Customer("Customer Y");
        Customer z = new Customer("Customer Z");

        Order orderX = new Order("X", x);
        orderX.addProduct(a, 2);
        orderX.addProduct(b, 1);
        orderX.pay(new CreditCardPayment());

        System.out.println();

        Order orderY = new Order("Y", y);
        orderY.pay(new CreditCardPayment());

        System.out.println();

        Order orderZ = new Order("Z", z);
        orderZ.addProduct(c, 1);
        orderZ.pay(new PayPalPayment());
    }
}