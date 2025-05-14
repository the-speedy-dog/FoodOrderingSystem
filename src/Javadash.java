import java.util.ArrayList;
import java.util.Queue;
import java.util.Arrays;
import java.util.Stack;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Random;

public class Javadash {
    private ArrayList<Restaurant> restaurants; 
    private Stack<Driver> availableDrivers;
    private Queue<Order> pendingOrders;
    private Restaurant restaurant;
    private User user;
    private Handler handler;

    public Javadash() {
        initRestaurants();
        availableDrivers = new Stack<>();
        pendingOrders = new LinkedList<>();
    }

    public void start() {
        Scanner scnr = new Scanner(System.in);

        initUser(scnr);
        printMisc();
        System.out.println();

        // command handler loop
        handler = new Handler(scnr);

        addUserCommands();

        if (user instanceof Customer) {
            initCustomer();
            addCustomerCommands();
        } else {
            initDriver();
            addDriverCommands();
        }

        handler.loop();

        // end swiftly
        scnr.close();
    }

    private void initCustomer() {
        initDrivers(new ArrayList<Driver>(Arrays.asList(
            new Driver("Bob"),
            new Driver("Bill"),
            new Driver("Joe"),
            new Driver("Greg"),
            new Driver("Coolguy123"),
            new Driver("imaDriver")
        )));
    }

    private void initDrivers(ArrayList<Driver> drivers) {
        for (Driver driver : drivers) {
            availableDrivers.push(driver);
        }
    }

    private void initDriver() {
        Driver driver = (Driver) user;
        Random random = new Random();
        initCustomers(new ArrayList<Order>(Arrays.asList(
            new Order(1, "Bill", "123 Road", driver.getName(), driver.getRatingObject(), restaurants.get(random.nextInt(restaurants.size()))),
            new Order(2, "Bob", "456 Lane", driver.getName(), driver.getRatingObject(), restaurants.get(random.nextInt(restaurants.size()))),
            new Order(3, "Joe", "House St.", driver.getName(), driver.getRatingObject(), restaurants.get(random.nextInt(restaurants.size()))),
            new Order(4, "House MD", "House St.", driver.getName(), driver.getRatingObject(), restaurants.get(random.nextInt(restaurants.size())))
        )));
    }

    private void initCustomers(ArrayList<Order> orders) {
        for (Order order: orders) {
            pendingOrders.add(order);
        }
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
        System.out.println("Javadash v1.0.0");
        System.out.printf(
            "Logged in as %s (%s)\n",
            user.getName(),
            user instanceof Customer ? "Customer" : "Driver"
        );
        System.out.println("Type \"help\" for command list");
    }

    // initialize all restaurants and menus
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

    public void tryAssignDriver() {
        if (availableDrivers.size() < 1) {
            initDrivers(new ArrayList<Driver>(Arrays.asList(new Driver("Filler_Driver")))); // Add new fake driver to the reserve
        }
        if (pendingOrders.size() > 0) {
            Order order = pendingOrders.poll();
            Driver driver = availableDrivers.pop();
            driver.setOrder(order);
            order.assignDriver(driver);
        }
    }
    
    /////////////////////////////
    // ADD COMMANDS TO HANDLER //
    /////////////////////////////

    private void addUserCommands() {
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
            /// Clear 
            new Command(
                new ArrayList<String>(Arrays.asList("clear", "cls")),
                "Clears the screen",
                obj -> clearCommand()
            )
        )));
    }

    private void addCustomerCommands() {
        handler.addCommands(new ArrayList<Command>(Arrays.asList(
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
                "Add or remove items from your cart - Use no arguments display cart",
                obj -> cartCommand((String[]) obj)
            ),
            /// Submit Order
            new Command(
                new ArrayList<String>(Arrays.asList("submit", "sub")),
                "Submits current order",
                obj -> submitOrderCommand()
            ),
            /// Print Previous Orders
            new Command(
                new ArrayList<String>(Arrays.asList("receipt", "rec")),
                "Prints receipts of the previous orders",
                obj -> printPreviousOrderCommand()
            ),
            /// Rate Driver
            new Command(
                new ArrayList<String>(Arrays.asList("rate", "r")),
                new ArrayList<ArrayList<String>>(
                    Arrays.asList(
                        new ArrayList<String>(
                            Arrays.asList("rating - int(1-5)")
                        )
                    )
                ),
                "Rates the last submitted order's driver",
                obj -> rateDriverCommand((String[]) obj)
            )
        )));
    }

    private void addDriverCommands() {
        handler.addCommands(new ArrayList<Command>(Arrays.asList(
            new Command(
                new ArrayList<String>(Arrays.asList("get", "g")),
                "Gets a new order",
                obj -> getNewOrderCommand()
            ),
            new Command(
                new ArrayList<String>(Arrays.asList("pickup", "p")),
                "Picks up the order from the restaurant",
                obj -> pickupOrderCommand()
            ),
            new Command(
                new ArrayList<String>(Arrays.asList("deliver", "d")),
                "Delivers the order",
                obj -> deliverOrderCommand()
            ),
            new Command(
                new ArrayList<String>(Arrays.asList("info", "i")),
                "Displays customer's order info",
                obj -> viewOrderInfoCommand()
            )
        )));
    }


    ///////////////////////////
    // COMMAND METHODS START //
    ///////////////////////////


    private void pickupOrderCommand() {
        Driver driver = (Driver) user;

        if (driver.getOrder() == null) {
            System.out.println("No order to pick up.");
            return;
        }

        if (driver.hasOrder()) {
            System.out.println("Order already picked up. Go deliver it!");
            return;
        }
        
        if (restaurant != driver.getOrder().getRestaurant()) {
            System.out.println("Wrong restaurant!");
            return;
        }

        driver.pickupOrder();
        System.out.println("Order picked up. Go deliver it!");
    }

    private void deliverOrderCommand() {
        Driver driver = (Driver) user;

        if (!driver.hasOrder()) {
            System.out.println("Go pick up order first!");
            return;
        }

        driver.deliverOrder();
        System.out.println("Order delivered!");
    }

    private void viewOrderInfoCommand() {
        Driver driver = (Driver) user;

        if (driver.getOrder() == null) {
            System.out.println("No order to view");
            return;
        }

        driver.getOrder().printCustomerDetails();
    }

    private void getNewOrderCommand() {
        Driver driver = (Driver) user;

        if (driver.getOrder() != null) {
            System.out.println("Cannot get new order, one is already in progress");
            return;
        }

        availableDrivers.push(driver);
        
        if (pendingOrders.size() < 1) {
            pendingOrders.add(new Order(Math.abs(new Random().nextInt()),
                        "Filler_Customer", "Filler_Address", 
                        driver.getName(), driver.getRatingObject(), restaurants.get(new Random().nextInt(restaurants.size()))));
        }

        tryAssignDriver();

        System.out.println("New Order:");
        driver.getOrder().printCustomerDetails();
    }

    private void printPreviousOrderCommand() {
        Customer customer = (Customer) user;

        if (customer.getOrders().size() < 1) {
            System.out.println("No previous orders to display!");
            return;
        } 

        customer.displayPreviousOrders();
    }

    private void rateDriverCommand(String[] args) {
        Customer customer = (Customer) user;

        if (args.length == 0) {
            customer.getOrders().get(customer.getOrders().size()-1).printDriverDetails();
        }

        int rating = Integer.parseInt(args[0]);

        if (rating > 0 && rating < 6) {
            try {
                customer.rateLastDriver(rating);
            } catch (Exception e) {
                System.out.println("No previous order - Driver has not been rated.");
                return;
            }
        } else {
            System.out.println("Rating must be between 1 and 5 stars!");
        }
    }

    private void submitOrderCommand() {
        Customer customer = (Customer) user;

        if (customer.getCart().size() < 1) {
            System.out.println("Cannot submit empty cart!");
            return;
        }

        pendingOrders.add(customer.getCart());
        System.out.println("Order submitted!");
        
        tryAssignDriver();
        
        customer.submitOrder();
        
        System.out.println("Order Delivered!");

        restaurant = null;
    }

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
            return;
        }
        // Set current restaurant and print its menu
        restaurant = restaurants.get(resId - 1);
        

        restaurantCommand(new String[0]);

        if (user instanceof Customer) {
            Customer customer = (Customer) user;
            customer.getCart().setRestaurant(restaurant);
            if (customer.getCart().size() > 0) {
                customer.getCart().reset();
                System.out.println("\nCart has been cleared because restaurant changed\n");
            }
        }
    }

    private void menuCommand() {
        Customer customer = (Customer) user;
        if (customer.getCart().getRestaurant() == null) {
            System.out.println("Restaurant not set! Use 'res' command to choose a restaurant.");
            return;
        }
        restaurant.printMenu();
    }

    private void cartCommand(String[] args) {
        Customer customer = (Customer) user;

        if (args.length == 0) {
            System.out.println(customer.getCart().toString());
            return;
        }
        else if (args.length < 3) {
            System.out.println("Not enough args");
        }

        if (customer.getCart().getRestaurant() == null) {
            System.out.println("Restaurant not set! Use 'res' command to choose a restaurant.");
            return;
        }

        int id = 0;
        int count = 0;

        if (args[0].contains("add")) {
            id = Integer.parseInt(args[1]);
            count = Integer.parseInt(args[2]);

            try {
                // Restaurant getItem call necessary, because addItem doesn't throw
                System.out.println("Adding "+count+" "+restaurant.getItem(id).toString()); // If id does not exist, should throw
                customer.getCart().addItem(id, count);
            } catch (Exception e) {
                System.out.println("Could not add item: Item not found");
            }
        } else if (args[0].contains("rm")) {
            id = Integer.parseInt(args[1]);
            count = Integer.parseInt(args[2]);
            
            try {
                customer.getCart().removeItem(id, count); // Should throw if non-existent
            } catch (Exception e) {
                System.out.println(
                    "Could not remove "+count+" "+
                    restaurant.getItem(id).getName()+
                    ": Item not found in cart");
            }
        }
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
