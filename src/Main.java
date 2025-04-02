import java.util.Scanner;

public class Main {

    public static void start(String name, boolean isCustomer, Scanner scnr) {

    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

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
        boolean isCustomer = decision == 'c';

        // print info before start
        System.out.println();
        System.out.println("Food Delivery v0.1.0");
        System.out.printf("Logged in as %s (%s)\n", name, isCustomer ? "Customer" : "Driver");
        System.out.println("Type \"help\" for command list");
        System.out.println();

        // start food ordering system
        start(name, isCustomer, scnr);

        // end swiftly
        scnr.close();
    }
}
