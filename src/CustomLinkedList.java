public class CustomLinkedList<E> {

    private static class Node<E> {
        E item;
        Node<E> next;

        public Node(E item) {
            this.item = item;
            this.next = null;
        }
    }

    private Node<E> head;
    private Node<E> tail;

    public CustomLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void add(E e) {
        Node<E> newNode = new Node<>(e);
        if (head == null) {
            head = newNode;
            tail = head;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public void clear() {
        head = null;
        tail = null;
    }

    public E get(int index) {
        if (head == null) {
            throw new RuntimeException("List Empty");
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        Node<E> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
            if (temp == null) {
                throw new IndexOutOfBoundsException("Index out of bounds: " + index);
            }
        }
        return temp.item;
    }

    public void remove(int index) {
        if (head == null) {
            throw new RuntimeException("List Empty");
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        if (index == 0) {
            head = head.next;
            if (head == null) {
                tail = null; // List became empty
            }
            return;
        }

        Node<E> temp = head;
        Node<E> prev = null;
        for (int i = 0; i < index; i++) {
            prev = temp;
            temp = temp.next;
            if (temp == null) {
                throw new IndexOutOfBoundsException("Index out of bounds: " + index);
            }
        }

        prev.next = temp.next;
        if (prev.next == null) {
            tail = prev;
        }
    }
    public int size() {
        int count = 0;
        for (Node<E> temp = head; temp != null; temp = temp.next) {
            count++;
        }
        return count;
    }

    public boolean isEmpty() {
        return head == null;
    }
    public static void main(String[] args) {
        CustomLinkedList<String> campusMap = new CustomLinkedList<>();

        campusMap.add("Gate 1");
        campusMap.add("Gate 2.5");
        campusMap.add("Gate 3");
        campusMap.add("Fr. Masterson Drive");
        campusMap.add("Fr. Arrupe Loop");
        campusMap.add("Parade Loop");
        campusMap.add("University Road");
        campusMap.add("Loyola Drive");

        System.out.println("--- Ateneo Campus Locations Route (" + campusMap.size() + " waypoints) ---");
        for (int i = 0; i < campusMap.size(); i++) {
            System.out.println((i + 1) + ". " + campusMap.get(i));
        }

        System.out.println("\nFirst stop: " + campusMap.get(0));

        System.out.println("\nRemoving index 1 (Gate 2.5)...");
        campusMap.remove(1);

        System.out.println("\n--- Updated Route (" + campusMap.size() + " waypoints) ---");
        for (int i = 0; i < campusMap.size(); i++) {
            System.out.println((i + 1) + ". " + campusMap.get(i));
        }

        campusMap.clear();
        System.out.println("\nRoute cleared. Is route empty? " + campusMap.isEmpty());
    }
}