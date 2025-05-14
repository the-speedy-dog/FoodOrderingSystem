import java.util.ArrayList;

public class Customer extends User {
    private String address;
    private Order cart;

    public Customer(String name, String address) {
        super(name);
        cart = new Order(0, name, address);
    }
    
    public String getAddress() {
        return address;
    }

    public Order getCart() {
        return cart;
    }

    public void submitOrder() {
        // Add cart to list of previous orders
        getOrders().add(cart); 
        // Adds cart to previous orders
        cart = new Order(getOrders().size(), getName(), address); 
    }

    public void rateLastDriver(int rating) {
        int size = getOrders().size();
        if (size > 0) {
            getOrders().get(size - 1).rateDriver(rating);
            getOrders().get(size - 1).printDriverDetails();
        } else {
            throw new IllegalArgumentException("No Driver available!");
        }
    }

    public void displayPreviousOrders() {
        ArrayList<Order> orders = getOrders();
        for (Order order : orders) {
            System.out.println("══) "+order.getRestaurant().getName()+":");
            System.out.println(order.toString()+"\n");
        }
    }

    public String toString() {
        return "Customer: "+getName()+", "+address;
    }

}
