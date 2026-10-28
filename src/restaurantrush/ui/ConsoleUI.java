package restaurantrush.ui;

import java.util.List;
import java.util.Scanner;

import restaurantrush.model.Restaurant;
import restaurantrush.model.Table;
import restaurantrush.model.customer.Customer;
import restaurantrush.util.MoneyUtils;

public class ConsoleUI {
    private final Scanner scanner;

    public ConsoleUI() {
        this.scanner = new Scanner(System.in);
    }

    public void displayTurnHeader(int turn, Restaurant restaurant) {
        System.out.println("\n==================================================");
        System.out.println("                 TURN " + turn + " / 18");
        if (restaurant.getHappyHour().isActive()) {
            System.out.println("   *** HAPPY HOUR ACTIVE (" + restaurant.getHappyHour().getTurnsRemaining() + " turns left) ***");
        }
        System.out.println("==================================================");

        String rev = MoneyUtils.formatCents(restaurant.getTotalRevenueCents());
        String avgSat = restaurant.getRoster().averagePaidSatisfactionText();
        int paidCount = restaurant.getRoster().paidCount();

        System.out.println("Revenue: " + rev + " | Paid Customers: " + paidCount + " | Avg Paid Sat: " + avgSat);
        System.out.println("--------------------------------------------------");

        System.out.print("Waiting Queue: ");
        List<Customer> waiting = restaurant.getWaitingQueue().asList();
        if (waiting.isEmpty()) {
            System.out.println("[Empty]");
        } else {
            for (int i = 0; i < waiting.size(); i++) {
                Customer c = waiting.get(i);
                System.out.print((i + 1) + ". " + c.label() + " (Sat: " + c.getSatisfaction() + ")  ");
            }
            System.out.println();
        }

        System.out.println("Tables:");
        for (Table t : restaurant.getTables()) {
            if (!t.isOccupied()) {
                System.out.println("  Table " + t.getTableNumber() + ": [EMPTY]");
            } else {
                Customer c = t.getCurrentCustomer();
                System.out.println("  Table " + t.getTableNumber() + ": " + c.label()
                        + " | Status: " + c.getStatus().getDescription()
                        + " | Sat: " + c.getSatisfaction());
            }
        }
        System.out.println("--------------------------------------------------");
    }

    public void displayMessage(String msg) {
        System.out.println(msg);
    }

    public void displayError(String errorMsg) {
        System.out.println("[ERROR] " + errorMsg);
    }

    public int getIntInput(String prompt, int min, int max) {
        int choice;
        while (true) {
            System.out.print(prompt + " (" + min + "-" + max + "): ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >= min && choice <= max) {
                    return choice;
                }
            } else {
                scanner.next();
            }
            System.out.println("Invalid choice. Please enter a number between " + min + " and " + max + ".");
        }
    }
}