import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Javadash {
    private ArrayList<Restaurant> restaurants = new ArrayList<>(); // ???
    private User user;

    public Javadash() {
        initRestaurants();
    }

    public void start() {
        Restaurant restaurant = restaurants.get(0);
        restaurant.printDetails();
        restaurant.printMenu();

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

        restaurant.printMenu();

        /*while (decision != 'c' && decision != 'd') {
            System.out.print("Customer or Driver? (c/d): ");
            String decisionStr = scnr.nextLine();
            if (decisionStr.length() > 0) {
                decision = decisionStr.charAt(0);
            }
        }*/

        System.out.println("Shutting down prematurely because fuck you that's why");
        
        // end swiftly
        scnr.close();
    }

    private void customerMainLoop(Restaurant restaurant) {
        
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

        if (decision == 'c') {
            // get address
            String address = null;
            while (address == null) {
                System.out.print("Enter Your Address: ");
                address = scnr.nextLine();

                if (address.length() == 0) {
                    address = null;
                }
            }
            user = new Customer(name, address);
        } else {
            user = new Driver(name);
        }
    }

    // initialize all restaurants and menus
    private void initRestaurants() {
        ArrayList<FoodItem> novoMenu = new ArrayList<>(
            Arrays.asList(
                new FoodItem("House Salad", 9.00),
                new FoodItem("Samosas", 16.00),
                new FoodItem("Beet & Burrata", 14.00),
                new FoodItem("Meze", 23.00),
                new FoodItem("Ahi Nachos", 22.00),
                new FoodItem("Burrata", 14.00),
                new FoodItem("Calamari", 20.00),
                new FoodItem("Risotto", 29.00),
                new FoodItem("Prime Ribeye", 56.00),
                new FoodItem("Filet", 56.00),
                new FoodItem("Lamb Shank", 38.00),
                new FoodItem("King Salmon", 42.00),
                new FoodItem("Espresso", 4.50),
                new FoodItem("Hot Tea", 4.50),
                new FoodItem("Soda", 5.00)
            )
        );
        restaurants.add(new Restaurant("Novo", novoMenu, 4.6, 1329, 3));
    }
}
