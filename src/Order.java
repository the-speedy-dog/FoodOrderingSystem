import java.util.ArrayList;

public class Order {
    private int ID;
    private String customerName;
    private String address;
    private String driverName;
    private double totalPrice;
    private ArrayList<FoodItem> items;

    public Order(String customerName, String address) {
        this.ID = (int)(Math.random() * Integer.MAX_VALUE);
        this.customerName = customerName;
        this.address = address;
        this.driverName = "Not Assigned";
        this.totalPrice = 0.0;
        this.items = new ArrayList<>();
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setDriverName(String name) {
        this.driverName = name;
    }

    public String getDriverName() {
        return driverName;
    }

    public int getNumItems() {
        return items.size();
    }

    public int getID() {
        return ID;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void addItem(FoodItem item) {
        this.items.add(item);
    }
    
    public FoodItem removeItem(String name) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName() == name) {
                return items.remove(i);
            }
        }
        return new FoodItem("Nothing", 0.0);
    }
}
