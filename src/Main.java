import java.util.Scanner;

/*
 * Main.java - Term Project: Campus Map Finder
 * Handles the main menu and connects the map reader and graph.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MapFileReader reader = new MapFileReader();
        CustomGraph graph = null;
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Enter your choice (1-4): ");

            String input = scanner.nextLine().trim();

            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(
                        "\nError: Please enter a number between 1 and 4.\n");
                continue;
            }

            System.out.println();
            switch (choice) {
                case 1:
                    System.out.print("Enter map filename " +
                            "(e.g., ateneo_map.txt): ");
                    String fileName = scanner.nextLine().trim();
                    if (fileName.isEmpty()) {
                        fileName = "ateneo_map.txt";
                    }

                    boolean loaded = reader.loadFile(fileName);
                    if (loaded) {
                        graph = new CustomGraph(reader);
                        graph.buildConnections(reader);

                        System.out.println("\nMap loaded successfully!");
                        System.out.println("Places loaded: " +
                                reader.getPlaces().size());
                        System.out.println("Roads loaded: " +
                                reader.getRoadCount());
                        System.out.println();
                    } else {
                        graph = null;
                        System.out.println("\nError: File '" +
                                fileName + "' not found.\n");
                    }
                    break;

                case 2:
                    if (graph == null) {
                        System.out.println(
                                "Error: Please load a map file first!\n");
                    } else {
                        System.out.println("--- Campus Places ---");

                        for (int i = 0;
                             i < reader.getPlaces().size(); i++) {

                            System.out.println(
                                    (i + 1) + ". " +
                                            reader.getPlaces().get(i));
                        }

                        System.out.println(
                                "\nA-to-Z sorting will be integrated " +
                                        "after the sorting method is ready.\n");
                    }
                    break;

                case 3:
                    if (graph == null) {
                        System.out.println(
                                "Error: Please load a map file first!\n");
                        break;
                    }

                    System.out.print("Enter starting location: ");
                    String start = scanner.nextLine().trim();

                    System.out.print("Enter destination: ");
                    String destination = scanner.nextLine().trim();

                    if (start.isEmpty() || destination.isEmpty()) {
                        System.out.println(
                                "\nError: Locations cannot be empty.\n");
                    } else if (!graph.containsPlace(start)) {
                        System.out.println(
                                "\nError: Starting location does not exist.\n");
                    } else if (!graph.containsPlace(destination)) {
                        System.out.println(
                                "\nError: Destination does not exist.\n");
                    } else {
                        System.out.println(
                                "\nBoth locations are valid!");
                        System.out.println(
                                "BFS pathfinding will be integrated " +
                                        "when the algorithm is ready.\n");
                    }
                    break;

                case 4:
                    System.out.println(
                            "Exiting Campus Map Finder. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println(
                            "Error: Choice out of range. " +
                                    "Please enter a number between 1 and 4.\n");
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