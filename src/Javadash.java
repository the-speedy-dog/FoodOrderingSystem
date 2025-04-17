import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Javadash {
    private ArrayList<Restaurant> restaurants; 
    private Restaurant restaurant;
    private User user;
    private Handler handler;

    public Javadash() {
        initRestaurants();
    }

    public void start() {
        restaurant = restaurants.get(0);
        restaurant.printMenu();
        Scanner scnr = new Scanner(System.in);

        initUser(scnr);
        printMisc();
        System.out.println();

        // command handler loop
        handler = new Handler(scnr);

        // Passes a list of commands to Handler class to be processed
        handler.addCommands(new ArrayList<Command>(Arrays.asList(
            /// Restaurant
            new Command(
                new ArrayList<String>(Arrays.asList("restaurant", "res")),
                new ArrayList<ArrayList<String>>(
                    Arrays.asList(
                        new ArrayList<String>(
                            Arrays.asList("restaurant_id - int")
                        )
                    )
                ),
                "Select a restaurant - Use no arguments to display available restaurants",
                obj -> restaurantCommand((String[]) obj)
            ),
            /// Menu
            new Command(
                new ArrayList<String>(Arrays.asList("menu","m")),
                "Print selected restaurant's menu",
                obj -> menuCommand()
            ),
            /// Cart
            new Command(
                new ArrayList<String>(Arrays.asList("cart", "c")),
                new ArrayList<ArrayList<String>>(
                    Arrays.asList(
                        new ArrayList<String>(
                            Arrays.asList("add", "rm")
                        ),
                        new ArrayList<String>(
                            Arrays.asList("item_id - int")
                        ),
                        new ArrayList<String>(
                            Arrays.asList("count - int")
                        )
                    )
                ),
                "Cart command, more to add",
                obj -> cartCommand((String[]) obj)
            ),
            /// Clear 
            new Command(
                new ArrayList<String>(Arrays.asList("clear", "cls")),
                "Clears the screen",
                obj -> clearCommand()
            )
        )));

        handler.loop();

        // end swiftly
        scnr.close();
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

    private void printMisc() {
        System.out.println();
        System.out.println("Javadash v0.1.0");
        System.out.printf(
            "Logged in as %s (%s)\n",
            user.getName(),
            user instanceof Customer ? "Customer" : "Driver"
        );
        System.out.println("Type \"help\" for command list");
    }

    // initialize all restaurants and menus
    // TODO: add more restaurants
    private void initRestaurants() {
        restaurants = new ArrayList<Restaurant>();

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
        restaurants.add(new Restaurant("Novo", novoMenu, 4));

        ArrayList<FoodItem> mcBurgerMenu = new ArrayList<>(
            Arrays.asList(
                new FoodItem("McYummy Burger", 8.99),
                new FoodItem("McNugs", 6.99),
                new FoodItem("McTacos", 3.99),
                new FoodItem("McFries", 2.99),
                new FoodItem("McDrink", 4.99)
            )
        );
        restaurants.add(new Restaurant("McBurger", mcBurgerMenu, 2));

        ArrayList<FoodItem> burgerQueenMenu = new ArrayList<>(
            Arrays.asList(
                new FoodItem("'Whopper? I Barely Even Know Her' Meal", 14.99),
                new FoodItem("Almost Food", 0.01)
            )
        );
        restaurants.add(new Restaurant("Burger Queen", burgerQueenMenu, 3));
    }

    ///////////////////////////
    // COMMAND METHODS START //
    ///////////////////////////

    private void restaurantCommand(String[] args) {

        if (args.length == 0) {
            System.out.println("\n╔═ Available Restaurants:");
            for (int i = 0; i < restaurants.size(); i++) {
                if (restaurants.get(i) == restaurant) {
                    System.out.print("> ");
                } else {
                    System.out.print("║ ");
                }
                System.out.println((i < 9 ? " " : "") + (i+1) + ": " + restaurants.get(i).getDetails());
            }
            System.out.println();
            return;
        }

        int resId = Integer.parseInt(args[0]);
        if (resId < 1 || resId > restaurants.size()) {
            System.out.println("Requested restaurant does not exist");
            System.out.println("passed the return");
            return;
        }
        // Set current restaurant and print its menu
        restaurant = restaurants.get(resId - 1);
        restaurant.printMenu();
    }

    private void menuCommand() {
        restaurant.printMenu();
    }

    private void cartCommand(String[] args) {
        Customer customer = (Customer) user;
        if (args.length == 0) {
            System.out.println(customer.getCart().toString());
            return;
        }
        
        // Unhandled Input
        throw new IllegalArgumentException(":(");
    }


    private void clearCommand() {
        try {
            // Runtime.getRuntime().exec("clear");
            System.out.print("\033[H\033[2J");
            System.out.flush();
            printMisc();
            System.out.println();
        } catch (Exception e) {
            System.out.println("Could not clear screen!");
        }
    }

    
    /////////////////////////
    // COMMAND METHODS END //
    /////////////////////////
}
