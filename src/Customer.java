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
        getOrders().add(cart);
        cart = new Order(0, getName(), address); // temp id 0
    }
}
