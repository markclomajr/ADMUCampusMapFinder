import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * MapFileReader.java
 * Reads the text file and populates places and road connections.
 */
public class MapFileReader {

    private CustomLinkedList<String> places;
    private CustomLinkedList<String> roadsFrom;
    private CustomLinkedList<String> roadsTo;
    private boolean loaded;

    public MapFileReader() {
        this.places = new CustomLinkedList<>();
        this.roadsFrom = new CustomLinkedList<>();
        this.roadsTo = new CustomLinkedList<>();
        this.loaded = false;
    }

    public boolean loadFile(String fileName) {
        File file = new File(fileName);

        // Check src/ directory if not found in project root
        if (!file.exists()) {
            file = new File("src/" + fileName);
        }

        // Check without .txt if filename was created without extension
        if (!file.exists() && fileName.endsWith(".txt")) {
            String noExt = fileName.substring(0, fileName.length() - 4);
            file = new File(noExt);
            if (!file.exists()) {
                file = new File("src/" + noExt);
            }
        }

        if (!file.exists()) {
            return false;
        }

        places.clear();
        roadsFrom.clear();
        roadsTo.clear();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String p1 = scanner.next();
                if (scanner.hasNext()) {
                    String p2 = scanner.next();
                    addPlaceUnique(p1);
                    addPlaceUnique(p2);
                    roadsFrom.add(p1);
                    roadsTo.add(p2);
                }
            }
            loaded = true;
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    private void addPlaceUnique(String place) {
        for (int i = 0; i < places.size(); i++) {
            if (places.get(i).equalsIgnoreCase(place)) {
                return;
            }
        }
        places.add(place);
    }

    public CustomLinkedList<String> getPlaces() {
        return places;
    }

    public CustomLinkedList<String> getRoadsFrom() {
        return roadsFrom;
    }

    public CustomLinkedList<String> getRoadsTo() {
        return roadsTo;
    }

    public int getRoadCount() {
        return roadsFrom.size();
    }

    public boolean isLoaded() {
        return loaded;
    }

    public static void main(String[] args) {
        MapFileReader reader = new MapFileReader();
        String fileName = "ateneo_map.txt";

        if (reader.loadFile(fileName)) {
            System.out.println("--- Map Loaded Successfully ---");
            System.out.println("Total Unique Places: " +
                    reader.getPlaces().size());
            System.out.println("Places:");
            for (int i = 0; i < reader.getPlaces().size(); i++) {
                System.out.println("  " + (i + 1) + ". " +
                        reader.getPlaces().get(i));
            }

            System.out.println("\nTotal Roads: " + reader.getRoadCount());
            System.out.println("Road Connections:");
            for (int i = 0; i < reader.getRoadCount(); i++) {
                System.out.println("  " + reader.getRoadsFrom().get(i) +
                        " <-> " + reader.getRoadsTo().get(i));
            }
        } else {
            System.out.println("Error: Could not load file '" +
                    fileName + "'.");
        }
    }
}