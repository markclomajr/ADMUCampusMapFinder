
public class CustomGraphTest {

    public static void main(String[] args) {
        MapFileReader reader = new MapFileReader();

        if (!reader.loadFile("ateneo_map.txt")) {
            System.out.println("Error: Could not load map.");
            return;
        }

        CustomGraph graph = new CustomGraph(reader);
        graph.buildConnections(reader);

        System.out.println("Places in graph: " + graph.size());
        System.out.println("Roads loaded: " + reader.getRoadCount());

        graph.printGraph();

        // Check if all roads connect in both directions
        boolean correct = true;

        for (int i = 0; i < reader.getRoadCount(); i++) {
            String from = reader.getRoadsFrom().get(i);
            String to = reader.getRoadsTo().get(i);

            if (!graph.hasEdge(from, to) ||
                    !graph.hasEdge(to, from)) {
                correct = false;
                System.out.println("Connection error: " +
                        from + " - " + to);
            }
        }

        System.out.println();

        if (correct) {
            System.out.println("All road connections are correct!");
        } else {
            System.out.println("Some connections are incorrect.");
        }
    }
}
