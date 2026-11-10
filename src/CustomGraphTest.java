
public class CustomGraphTest {

    public static void main(String[] args) {
        MapFileReader reader = new MapFileReader();

        if (!reader.loadFile("ateneo_map.txt")) {
            System.out.println("Error: Could not load map.");
            return;
        }

        CustomGraph graph = new CustomGraph(reader);

        System.out.println("Places in graph: " + graph.size());
        graph.printGraph();
    }
}
