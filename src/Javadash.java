import java.util.ArrayList;
import java.util.Arrays;

public class Javadash {
    private ArrayList<Restaurant> restaurants = new ArrayList<>();

    public Javadash() {
        initRestaurants();
    }

    public void init() {
        Restaurant mcdonalds = restaurants.get(0);
        mcdonalds.printMenu();
    }

    // initialize all restaurants and menus
    private void initRestaurants() {
        ArrayList<FoodItem> mcdonaldsMenu = new ArrayList<>(
            Arrays.asList(
                new FoodItem("Big Mac", 8.99),
                new FoodItem("Big Mac", 8.99),
                new FoodItem("Big Mac", 8.99),
                new FoodItem("Big Mac", 8.99),
                new FoodItem("Big Mac", 8.99),
                new FoodItem("Big Mac", 8.99),
                new FoodItem("Big Mac", 8.99)
            )
        );
        restaurants.add(new Restaurant("McDonalds", mcdonaldsMenu, 4.88, 100));
    }
}
