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

            System.out.printf(
                " %d: %s ($%,.2f)\n",
                i+1,
                menu.get(i).getName(),
                menu.get(i).getPrice()
            );
            /*if ((i+1) % 3 == 0 || i+1 == menu.size()) {
                System.out.println();
            }*/
        }
    }
    
    public void printDetails() {
        System.out.printf("%s - ★ %.1f (%,d)\n", 
                name, rating.getRating(), rating.getRatingCount());
    }

}
