import java.util.ArrayList;

public class Restaurant {
    private String name;
    private ArrayList<FoodItem> menu;
    private Rating rating;


    public Restaurant(
        String name,
        ArrayList<FoodItem> menu,
        double rating,
        int ratingCount
    ) {
        this.name = name;
        this.menu = menu;
        this.rating = new Rating(rating, ratingCount);
    }

    public String getName() {
        return name;
    }
    
    
    public void  printMenu() {
        for (int i = 0; i < menu.size(); i++) {
            System.out.printf(
                "%d: %s ($%,.2f)\t\t",
                i+1,
                menu.get(i).getName(),
                menu.get(i).getPrice()
            );
            if ((i+1) % 3 == 0 || i+1 == menu.size()) {
                System.out.println();
            }
        }
    }
    
    public void printDetails() {
        System.out.printf("%s - ★ %.1f (%,d)\n", 
                name, rating.getRating(), rating.getRatingCount());
    }

}
