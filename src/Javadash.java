import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Javadash {
    private ArrayList<Restaurant> restaurants; 
    private User user;
    private Handler handler;

    public Javadash() {
        initRestaurants();
    }

    public void start() {
        Restaurant restaurant = restaurants.get(0);
        restaurant.printMenu();
        Scanner scnr = new Scanner(System.in);

        initUser(scnr);
        printMisc();
        System.out.println();

        // command handler loop
        handler = new Handler(scnr);

        // testing handler commands
        handler.commands.add(
            new Command(
                new ArrayList<String>(Arrays.asList("restaurant", "res")),
                "Print speicified restaurant's menu",
                obj -> restaurantCommand((String[]) obj)
            )
        );
        handler.commands.add(
            new Command(
                new ArrayList<String>(Arrays.asList("cart", "c")),
                new ArrayList<ArrayList<String>>(
                    Arrays.asList(
                        new ArrayList<String>(
                            Arrays.asList("add", "rm")
                        ),
                        new ArrayList<String>(
                            Arrays.asList("item_id")
                        ),
                        new ArrayList<String>(
                            Arrays.asList("count")
                        )
                    )
                ),
                "Cart command, more to add",
                obj -> cartCommand((String[]) obj)
            )
        );

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
        restaurants.add(new Restaurant("Novo", novoMenu, 4.6, 1329, 3));
    }

    ///////////////////////////
    // COMMAND METHODS START //
    ///////////////////////////

    private void restaurantCommand(String[] args) {

        // could try catch every function that could break
        // for better errors or could wrap it all in try catch
        // like what i currently have, but will not give more
        // info than either missing args or incorrect input types
        try {
            int resId = Integer.parseInt(args[0]);
            if (resId < 1 || resId > restaurants.size()) {
                System.out.println("Requested restaurant does not exist");
            System.out.println("passed the return");
                return;
            }
            restaurants.get(resId - 1).printMenu();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("missing arguments");
        } catch (NumberFormatException e) {
            System.out.println("incorrect inputs");
        }
    }

    private void cartCommand(String[] args) {
        try {
            Customer customer = (Customer) user;
            if (args.length == 0) {
                System.out.println(customer.getCart().toString());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("error");
        }
    }
    
    /////////////////////////
    // COMMAND METHODS END //
    /////////////////////////
}
