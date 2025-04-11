import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Javadash {
    private ArrayList<Restaurant> restaurants = new ArrayList<>();
    private User user;

    public Javadash() {
        initRestaurants();
    }

    public void start() {
        Restaurant mcdonalds = restaurants.get(0);
        mcdonalds.printDetails();

        Scanner scnr = new Scanner(System.in);
        initUser(scnr);

        // print info before start
        System.out.println();
        System.out.println("Javadash v0.1.0");
        System.out.printf(
            "Logged in as %s (%s)\n",
            user.getName(),
            user instanceof Customer ? "Customer" : "Driver"
        );
        System.out.println("Type \"help\" for command list");
        System.out.println();

        mcdonalds.printMenu();


        // We should add a "shutdown" or "cleanup" function
        System.out.println("Shutting down prematurely because fuck you that's why");
        
        // end swiftly
        scnr.close();
    }

    // initialize all restaurants and menus
    private void initRestaurants() {
        ArrayList<FoodItem> novoMenu = new ArrayList<>(
            Arrays.asList(
                new FoodItem("Big Mac", 3.99),
                new FoodItem("Medium Mac", 6.99),
                new FoodItem("Small Mac", 9.99),
                new FoodItem("Fries", 8.99),
                new FoodItem("Lies", 8.99),
                new FoodItem("Apple Pies", 8.99)
            )
        );
        restaurants.add(new Restaurant("Novo", novoMenu, 4.6, 1329));
    }
    
    // init user
    private void initUser(Scanner scnr) {
        // get name
        String name = null;
        while (name == null) {
            System.out.print("Enter name: ");
            name = scnr.nextLine();

            if (name.length() == 0) {
                name = null;
            }
        }

        // select customer or driver
        char decision = '0';
        while (decision != 'c' && decision != 'd') {
            System.out.print("Customer or Driver? (c/d): ");
            String decisionStr = scnr.nextLine();
            if (decisionStr.length() > 0) {
                decision = decisionStr.charAt(0);
            }
        }
        //boolean isCustomer = decision == 'c';
        // if (myObj instanceof Customer) -> checking type

        if (decision == 'c') {
            System.out.print("Enter Delivery Address: ");
            String address = scnr.nextLine();
            user = new Customer(name, address);
        } else {
            user = new Driver(name);
        }
    }
}
