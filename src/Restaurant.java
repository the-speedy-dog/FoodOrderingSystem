import java.util.ArrayList;

public class Restaurant {
    private String name;
    private ArrayList<FoodItem>  menu;

    public Restaurant(String name, ArrayList<FoodItem> menu) {
        this.name = name;
        this.menu = menu;
    }

    public String getName() {
        return name;
    }
    
    public void printMenu() {
        for (int i = 0; i < menu.size(); i++) {
            System.out.printf(
                "%d: %s ($%,.2f)",
                i+1,
                menu.get(i).getName(),
                menu.get(i).getPrice()
            );
        }
    }
}
