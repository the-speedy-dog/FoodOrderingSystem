import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class Restaurant {
    private String name;
    private ArrayList<FoodItem> menu;
    private Rating rating;
    private int priceRank;
    private Queue<Order> pendingOrders;


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

    public FoodItem getItem(int itemId) {
        return menu.get(itemId-1);
    }
    
    public void placeOrder(Order order) { // Assign Driver, etc.
        pendingOrders.add(order);
        // TODO IDK WHAT THIS IS
    }

    public void  printMenu() {
        System.out.println("""
                ╔══════╗
                ║>Menu<║
                ╚══════╝""");
        for (int i = 0; i < menu.size(); i++) {
            
            if (i == menu.size()-1) {
                System.out.print("╚");
            } else if (i == 0) {
                System.out.print("╔");
            } else {
                System.out.print("╠");
            }

            System.out.println((i+1) + ": " + menu.get(i).toString());
            /*if ((i+1) % 3 == 0 || i+1 == menu.size()) {
                System.out.println();
            }*/
        }
    }
    
    public void printDetails() {
        System.out.printf(
            "%s - ★ %.1f (%,d) - %s\n", 
            name,
            rating.getRating(),
            rating.getRatingCount(),
            getPriceRankString()
        );
    }

}
