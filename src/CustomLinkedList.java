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

}