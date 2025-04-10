import java.util.HashMap;

public class Order {
    private int id;
    private Customer customer;
    private Driver driver;
    private Restaurant restaurant;
    private int totalItems;
    private double totalPrice;
    private HashMap<Integer, Integer> items;

    public Order(int id, Customer customer, Driver driver, Restaurant restaurant) {
        this.id = id;
        this.customer = customer;
        this.driver = driver;
        this.restaurant = restaurant;
        totalItems = 0;
        totalPrice = 0.0;
        items = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Driver getDriver() {
        return driver;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

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
}
