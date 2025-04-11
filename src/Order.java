import java.util.HashMap;
import java.util.Map;

public class Order {
    private int id;
    private String customerName;
    private String driverName;
    private Rating driverRating;
    private String deliveryAddress;
    private Restaurant restaurant;
    private int totalItems;
    private double totalPrice;
    private HashMap<Integer, Integer> items; // order<index, amount>

    public Order(int id, String customerName, String deliveryAddress, Restaurant restaurant) {
        this.id = id; // ID assigned by Restaurant or Random? TBD?
        this.customerName = customerName;
        this.deliveryAddress = deliveryAddress;
        this.driverName = "Not Assigned"; // Driver assigned by Restaurant
        this.restaurant = restaurant;
        totalItems = 0;
        totalPrice = 0.0;
        items = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public int size() { // To make consistent with other Java data structures
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
        }
    }


    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("| Order number "+id);
        str.append("| ");

        for (Map.Entry<Integer, Integer> entry : items.entrySet()) {
            int index = (int)entry.getKey();
            int amount = (int)entry.getValue();
            for (int i = 0; i < amount; i++) {
                str.append("| " + restaurant.getItem(index).toString());
            }
        }
        return str.toString();
    }
}
