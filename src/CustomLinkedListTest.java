import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CustomLinkedListTest {

    @Test
    void add() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.add(1);
        assertEquals(1, list.size());
    }

    @Test
    void clear() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.add(1);
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    void get() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.add(1);
        // Casts 1 to Integer (or (int) list.get(0)) to remove overload ambiguity
        assertEquals(Integer.valueOf(1), list.get(0));
    }

    @Test
    void remove() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.add(1);
        list.remove(0);
        assertThrows(RuntimeException.class, () -> list.remove(0));
    }

    @Test
    void size() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.add(1);
        list.add(2);
        assertEquals(2, list.size());
    }

    @Test
    void isEmpty() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        assertTrue(list.isEmpty());
    }
}