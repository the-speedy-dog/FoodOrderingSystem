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
        this.rating = new Rating();
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

    public FoodItem getItem(int itemId) {
        return menu.get(itemId-1);
    }

    public void  printMenu() {
        String details = getDetails();
        char[] charArr = new char[details.length()];
        Arrays.fill(charArr, '═');
        String line = new String(charArr);

        System.out.println("╔═" + line + "═╗");
        System.out.println("║ " + details + " ║");
        System.out.println("╚═" + line + "═╝");

        for (int i = 0; i < menu.size(); i++) {
            if (i == 0) {
                System.out.print("╔");
            }  else if (i == menu.size()-1) {
                System.out.print("╚");
            } else {
                System.out.print("╠");
            }

            System.out.println((i < 9 ? "═" : "") + (i+1) + ": " + menu.get(i).toString());
        }
    }
    
    public String getDetails() {
        return String.format(
            "%s | ★ %.1f | %s",
            name,
            rating.getRating(),
            getPriceRankString()
        );
    }

}
