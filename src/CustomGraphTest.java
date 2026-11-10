
public class CustomGraphTest {

    public static void main(String[] args) {
        MapFileReader reader = new MapFileReader();

        if (!reader.loadFile("ateneo_map.txt")) {
            System.out.println("Error: Could not load map.");
            return;
        }

        CustomGraph graph = new CustomGraph(reader);

        // Add the road connections to the graph
        graph.buildConnections(reader);

        System.out.println("Places in graph: " + graph.size());
        System.out.println("Roads loaded: " + reader.getRoadCount());

        graph.printGraph();
    }
}
