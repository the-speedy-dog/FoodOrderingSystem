import java.util.ArrayList;
import java.util.Arrays;

public class Restaurant {
    private String name;
    private ArrayList<FoodItem> menu;
    private Rating rating;
    private int priceRank;


    public Restaurant(
        String name,
        ArrayList<FoodItem> menu,
        double rating,
        int ratingCount,
        int priceRank
    ) {
        this.name = name;
        this.menu = menu;
        this.rating = new Rating(rating, ratingCount);
        this.priceRank = priceRank;
    }

    public String getName() {
        return name;
    }

    public int getPriceRank() {
        return priceRank;
    }

    public String getPriceRankString() {
        char[] charArr = new char[priceRank];
        Arrays.fill(charArr, '$');
        return new String(charArr);
    }
    
    public void  printMenu() {
        for (int i = 0; i < menu.size(); i++) {
            System.out.printf(
                "%s%d: %s ($%,.2f)\t\t",
                i < 9 ? " " : "",
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
        System.out.printf(
            "%s - ★ %.1f (%,d) - %s\n", 
            name,
            rating.getRating(),
            rating.getRatingCount(),
            getPriceRank()
        );
    }

}
