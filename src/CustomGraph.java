
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

    public int size() {
        return vertices.size();
    }

    public void printGraph() {
        System.out.println("--- Campus Graph ---");

        for (int i = 0; i < vertices.size(); i++) {
            Vertex current = vertices.get(i);

            System.out.println(current.name + " -> " +
                    "(no connections yet)");
        }
    }
}
