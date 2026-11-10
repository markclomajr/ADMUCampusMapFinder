import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * MapFileReader.java
 * Reads the text file (e.g., ateneo_map.txt) and populates
 * the custom data structure with places and road connections.
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

    /*
     * Reads the map file line by line (Place1 Place2 format).
     * Populates places list and road connections.
     */
    public boolean loadFile(String fileName) {
        File file = new File(fileName);
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
}