public class SortUtils {

    // Selection Sort algorithm (A-to-Z, case-insensitive)
    public static void selectionSort(CustomLinkedList<String> list) {
        int n = list.size();

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;

            for (int j = i + 1; j < n; j++) {
                if (list.get(j).compareToIgnoreCase(
                        list.get(minIdx)) < 0) {
                    minIdx = j;
                }
            }

            if (minIdx != i) {
                String temp = list.get(i);
                list.set(i, list.get(minIdx));
                list.set(minIdx, temp);
            }
        }
    }
}