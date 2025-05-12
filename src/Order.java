import java.util.HashMap;
import java.util.Map;

public class Order {
    private int id;
    private String customerName;
    private String customerAddr;
    private String driverName;
    private Rating driverRating;
    private Restaurant restaurant;
    private int totalItems;
    private double totalPrice;
    private HashMap<Integer, Integer> items; // order<index, amount>
    private Status status;

    public enum Status {
        IN_CART,
        IN_PROGRESS,
        COMPLETE
    }

    // constructor for when user is customer
    public Order(int id, String customerName, String customerAddr) {
        this.id = id; // ID assigned by Restaurant or Random? TBD?
        // could be random, or based on an array index ... 1, 2, 3, 4...

        this.customerName = customerName;
        this.customerAddr = customerAddr;
        totalItems = 0;
        totalPrice = 0.0;
        items = new HashMap<>();
    }

    // constructor for when user is driver
    public Order(
        int id,
        String customerName,
        String customerAddr,
        String driverName,
        Rating driverRating,
        Restaurant restaurant
        // TODO: add implementation for randomizing a customers order
        // (after predetermined customers for when user is driver)
    ) {
        this.id = id; // ID assigned by Restaurant or Random? TBD?
        // could be random, or based on an array index ... 1, 2, 3, 4...

        this.customerName = customerName;
        this.customerAddr = customerAddr;
        this.driverName = driverName;
        this.driverRating = driverRating;
        this.restaurant = restaurant;
        this.totalItems = 0;
        this.totalPrice = 0.0;
        this.items = new HashMap<>();
        this.status = Status.IN_CART;
    }

    public int getId() {
        return id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public int size() {
        return totalItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    // This structure creates a dependence on Restaurant's structure?
    public void addItem(int itemId, int amount) {  
        if (items.containsKey(itemId)) {
            items.put(itemId, items.get(itemId) + amount);
        } else {
            items.put(itemId, amount);
        }
        totalPrice += restaurant.getItem(itemId).getPrice() * amount;
        totalItems += amount;
    }
    
    public void removeItem(int itemId, int amount) {
        if (items.containsKey(itemId)) {
            double itemPrice = restaurant.getItem(itemId).getPrice();
            int itemCount = items.get(itemId);
            if (itemCount <= amount) {
                items.remove(itemId);
                totalPrice -= itemPrice * itemCount;
                totalItems -= itemCount;
            } else {
                items.put(itemId, itemCount - amount);
                totalPrice -= itemPrice * amount;
                totalItems -= amount;
            }
        } else {
            throw new IllegalArgumentException("Item not found");
        }
    }


    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("╔═ Order number ");
        str.append(id);
        str.append(":\n║");

        for (Map.Entry<Integer, Integer> entry : items.entrySet()) {
            int index = (int)entry.getKey();
            int amount = (int)entry.getValue();
            for (int i = 0; i < amount; i++) {
                str.append("\n║ ");
                str.append("ID: ");
                str.append(index);
                str.append(" - ");
                str.append(restaurant.getItem(index).toString());
            }
        }
        str.append("\n║\n╚═ Total: ");
        str.append(getTotalPrice());

        return str.toString();
    }
}
