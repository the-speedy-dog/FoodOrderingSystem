import java.util.ArrayList;

public abstract class User {
    private String name;
    private ArrayList<Order> orders;

    public User(String name) {
        this.name = name;
        orders = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }
}
