public class Customer extends User {
    private String address;
    private Order order;

    public Customer(String name, String address) {
        super(name);
        this.order = new Order(name, address); 
    }

    public Order getOrder() {
        return order;
    }
}
