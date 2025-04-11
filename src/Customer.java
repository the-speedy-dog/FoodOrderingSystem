import java.util.Scanner;
import java.util.ArrayList;

public class Customer extends User {
    private String address;
    private Order currentOrder; // Can add to and interact with
    private ArrayList<Order> pastOrders; // Cannot influence -> After placing order

    public Customer(String name, String address) {
        super(name);
        this.currentOrder = null;
        this.pastOrders = null;
    }
    
    public void placeNewOrder() {
        Scanner scnr = new Scanner(System.in);
        if (currentOrder != null) {
            System.out.println("Abandon order and create a new one?");
            //if ()
        }
        //orders.add(new Order(name, address)); 
    }

    public Order getOrder() {
        return currentOrder;
    }

    public void printPastOrders() {
        // Print in order of most to least recent
        for (int i = pastOrders.size(); i > 0; i++) {
            System.out.println(pastOrders.toString());
        }
    }
}
