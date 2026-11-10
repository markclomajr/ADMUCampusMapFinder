
public class CustomGraph {

    private static class Vertex {
        String name;
        CustomLinkedList<String> neighbors;

        public Vertex(String name) {
            this.name = name;
            this.neighbors = new CustomLinkedList<>();
        }
    }

    private CustomLinkedList<Vertex> vertices;

    // Create the graph using the loaded campus locations
    public CustomGraph(MapFileReader reader) {
        vertices = new CustomLinkedList<>();

        if (reader == null || !reader.isLoaded()) {
            throw new IllegalArgumentException(
                    "Map must be loaded first.");
        }

        CustomLinkedList<String> places = reader.getPlaces();

        for (int i = 0; i < places.size(); i++) {
            vertices.add(new Vertex(places.get(i)));
        }
    }

    // Find a location in the graph
    private Vertex findVertex(String name) {
        for (int i = 0; i < vertices.size(); i++) {
            Vertex current = vertices.get(i);

            if (current.name.equalsIgnoreCase(name)) {
                return current;
            }
        }

        return null;
    }

    // Add a road connection between two locations
    public void addEdge(String from, String to) {
        Vertex first = findVertex(from);
        Vertex second = findVertex(to);

        if (first == null || second == null) {
            return;
        }

        // Check if the connection already exists
        boolean exists = false;

        for (int i = 0; i < first.neighbors.size(); i++) {
            if (first.neighbors.get(i).equalsIgnoreCase(to)) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            first.neighbors.add(second.name);
            second.neighbors.add(first.name);
        }
    }

    // Build connections using the loaded map file
    public void buildConnections(MapFileReader reader) {
        if (reader == null || !reader.isLoaded()) {
            throw new IllegalArgumentException(
                    "Map must be loaded first.");
        }

        CustomLinkedList<String> from = reader.getRoadsFrom();
        CustomLinkedList<String> to = reader.getRoadsTo();

        for (int i = 0; i < reader.getRoadCount(); i++) {
            addEdge(from.get(i), to.get(i));
        }
    }

    // Return the number of locations in the graph
    public int size() {
        return vertices.size();
    }

    // Display all locations and their connections
    public void printGraph() {
        System.out.println("--- Campus Graph ---");

        for (int i = 0; i < vertices.size(); i++) {
            Vertex current = vertices.get(i);

            System.out.print(current.name + " -> ");

            for (int j = 0; j < current.neighbors.size(); j++) {
                System.out.print(current.neighbors.get(j));

                if (j < current.neighbors.size() - 1) {
                    System.out.print(", ");
                }
            }

            System.out.println();
        }
    }
}
