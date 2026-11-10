
public class CustomQueue<E> {

    private static class Node<E> {
        E item;
        Node<E> next;

        public Node(E item) {
            this.item = item;
            this.next = null;
        }
    }

    private Node<E> front;
    private Node<E> rear;
    private int count;

    public CustomQueue() {
        front = null;
        rear = null;
        count = 0;
    }

    // Add an item to the back of the queue
    public void enqueue(E item) {
        Node<E> newNode = new Node<>(item);

        if (front == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }

        count++;
    }

    // Remove and return the first item
    public E dequeue() {
        if (front == null) {
            throw new RuntimeException("Queue Empty");
        }

        E item = front.item;
        front = front.next;

        if (front == null) {
            rear = null;
        }

        count--;
        return item;
    }

    // Look at the first item without removing it
    public E peek() {
        if (front == null) {
            throw new RuntimeException("Queue Empty");
        }

        return front.item;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return count;
    }

    public void clear() {
        front = null;
        rear = null;
        count = 0;
    }
}
