import java.util.Scanner;

/*
 * Main.java - Term Project: Campus Map Finder
 * Displays the main menu and handles user selection.
 * Functionality for options will be linked in upcoming modules.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Enter your choice (1-4): ");

            if (!scanner.hasNextInt()) {
                System.out.println("\nError: Invalid input." +
                        " Please enter a number between 1 and 4.\n");
                scanner.next(); // Consume invalid input
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            System.out.println();
            switch (choice) {
                case 1:
                    System.out.println("[Stub] Option 1: Load map file " +
                            "selected.");
                    System.out.println("Functionality will be added in " +
                            "Week 2.\n");
                    break;

                case 2:
                    System.out.println("[Stub] Option 2: Show places " +
                            "A-to-Z selected.");
                    System.out.println("Functionality will be added in " +
                            "Week 3.\n");
                    break;

                case 3:
                    System.out.println("[Stub] Option 3: Find path between " +
                            "2 places selected.");
                    System.out.println("Functionality will be added in " +
                            "Week 3.\n");
                    break;

                case 4:
                    System.out.println("Exiting Campus Map Finder. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Error: Choice out of range." +
                            " Please enter a number between 1 and 4.\n");
                    break;
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("=========================================");
        System.out.println("        CAMPUS MAP FINDER MENU           ");
        System.out.println("=========================================");
        System.out.println("1. Load the map file");
        System.out.println("2. Show all places in A-to-Z order");
        System.out.println("3. Find a path between 2 places");
        System.out.println("4. Quit");
        System.out.println("=========================================");
    }
}