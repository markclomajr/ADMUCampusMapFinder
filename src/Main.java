import java.util.Scanner;

/*
 * Main.java - Term Project: Campus Map Finder
 * Handles the main menu, sorting, and BFS pathfinding execution.
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
                        CustomLinkedList<String> sortedPlaces =
                                new CustomLinkedList<>();
                        CustomLinkedList<String> rawPlaces =
                                reader.getPlaces();

                        for (int i = 0; i < rawPlaces.size(); i++) {
                            sortedPlaces.add(rawPlaces.get(i));
                        }

                        SortUtils.selectionSort(sortedPlaces);

                        System.out.println("--- Campus Places (A-to-Z) ---");
                        for (int i = 0; i < sortedPlaces.size(); i++) {
                            System.out.println(
                                    (i + 1) + ". " + sortedPlaces.get(i));
                        }
                        System.out.println();
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
                                "\nError: Starting location '" + start +
                                        "' does not exist.\n");
                    } else if (!graph.containsPlace(destination)) {
                        System.out.println(
                                "\nError: Destination '" + destination +
                                        "' does not exist.\n");
                    } else {
                        CustomLinkedList<String> path =
                                graph.findShortestPath(start, destination);

                        if (path == null) {
                            System.out.println(
                                    "\nError: No path exists between '" +
                                            start + "' and '" +
                                            destination + "'.\n");
                        } else {
                            System.out.println("\n--- Shortest Path ---");
                            for (int i = 0; i < path.size(); i++) {
                                System.out.print(path.get(i));
                                if (i < path.size() - 1) {
                                    System.out.print(" -> ");
                                }
                            }
                            System.out.println();
                            System.out.println("Number of stops: " +
                                    (path.size() - 1) + "\n");
                        }
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