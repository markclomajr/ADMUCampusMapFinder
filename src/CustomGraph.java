
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
        if (name == null) {
            return null;
        }

        for (int i = 0; i < vertices.size(); i++) {
            Vertex current = vertices.get(i);

            if (current.name.equalsIgnoreCase(name)) {
                return current;
            }
        }

        return null;
    }
    // Check if a location exists in the graph
    public boolean containsPlace(String name) {
        return findVertex(name) != null;
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

    // Check if two locations are connected
    public boolean hasEdge(String from, String to) {
        Vertex first = findVertex(from);

        if (first == null) {
            return false;
        }

        for (int i = 0; i < first.neighbors.size(); i++) {
            if (first.neighbors.get(i).equalsIgnoreCase(to)) {
                return true;
            }
        }

        return false;
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

    // Helper node wrapper for BFS path reconstruction
    private static class PathNode {
        String name;
        PathNode parent;

        public PathNode(String name, PathNode parent) {
            this.name = name;
            this.parent = parent;
        }
    }

    // BFS algorithm to find shortest path (fewest stops)
    public CustomLinkedList<String> findShortestPath(String start,
                                                     String target) {
        Vertex startVertex = findVertex(start);
        Vertex targetVertex = findVertex(target);

        if (startVertex == null || targetVertex == null) {
            return null;
        }

        CustomQueue<PathNode> queue = new CustomQueue<>();
        CustomLinkedList<String> visited = new CustomLinkedList<>();

        queue.enqueue(new PathNode(startVertex.name, null));
        visited.add(startVertex.name.toLowerCase());

        PathNode matchNode = null;

        while (!queue.isEmpty()) {
            PathNode current = queue.dequeue();

            if (current.name.equalsIgnoreCase(targetVertex.name)) {
                matchNode = current;
                break;
            }

            Vertex currentVertex = findVertex(current.name);
            if (currentVertex == null) {
                continue;
            }

            for (int i = 0; i < currentVertex.neighbors.size(); i++) {
                String neighbor = currentVertex.neighbors.get(i);

                if (!containsIgnoreCase(visited, neighbor)) {
                    visited.add(neighbor.toLowerCase());
                    queue.enqueue(new PathNode(neighbor, current));
                }
            }
        }

        if (matchNode == null) {
            return null; // No path found
        }

        // Reconstruct path backwards from target to start
        CustomLinkedList<String> reversed = new CustomLinkedList<>();
        PathNode curr = matchNode;
        while (curr != null) {
            reversed.add(curr.name);
            curr = curr.parent;
        }

        // Reverse to get start-to-target path order
        CustomLinkedList<String> path = new CustomLinkedList<>();
        for (int i = reversed.size() - 1; i >= 0; i--) {
            path.add(reversed.get(i));
        }

        return path;
    }

    // Helper method for case-insensitive lookup in linear search
    private boolean containsIgnoreCase(CustomLinkedList<String> list,
                                       String target) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equalsIgnoreCase(target)) {
                return true;
            }
        }
        return false;
    }
}
