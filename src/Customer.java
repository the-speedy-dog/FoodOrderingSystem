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

    public void displayPreviousOrders() {
        ArrayList<Order> orders = getOrders();
        for (Order order : orders) {
            System.out.println();
            order.toString();
        }
    }

}
