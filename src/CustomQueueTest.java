
public class CustomQueueTest {
    public static void main(String[] args) {
        CustomQueue<String> queue = new CustomQueue<>();

        queue.enqueue("Library");
        queue.enqueue("Cafeteria");
        queue.enqueue("Gym");

        System.out.println("Queue size: " + queue.size());
        System.out.println("First place: " + queue.peek());

        System.out.println("Removed: " + queue.dequeue());
        System.out.println("Next place: " + queue.peek());

        queue.clear();
        System.out.println("Is queue empty? " + queue.isEmpty());
    }
}
