import java.util.ArrayList;

public class Restaurant {
    private String name;
    private ArrayList<FoodItem> menu;
    private double rating;
    private int ratingCount;

    public Restaurant(
        String name,
        ArrayList<FoodItem> menu,
        double rating,
        int ratingCount
    ) {
        this.name = name;
        this.menu = menu;
        this.rating = rating;
        this.ratingCount = ratingCount;
    }

    public String getName() {
        return name;
    }
    
    public double getRating() {
        return rating;
    }
    
    public void printMenu() {
        for (int i = 0; i < menu.size(); i++) {
            System.out.printf(
                "%d: %s ($%,.2f)\t\t",
                i+1,
                menu.get(i).getName(),
                menu.get(i).getPrice()
            );
            if (i % 3 == 0) {
                System.out.println();
            }
        }
    }

    public void rate(double rating) {
        ratingCount++;
        this.rating = (this.rating + rating) / ratingCount;
    }
}
