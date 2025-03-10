/**
 * Implementation of a Priority Queue using an ArrayList.
 * This class provides methods to enqueue, dequeue, and inspect elements in a priority queue.
 * The queue follows the rule that a smaller priority number means higher priority.
 */
import java.util.ArrayList;
import java.util.Arrays;

public class PriorityQueues {
    private final ArrayList<Node> queueArray = new ArrayList<>();

    /**
     * Inner class representing a node in the priority queue.
     */
    private static class Node {
        String value;
        int priority;

        Node(String value, int priority) {
            this.value = value;
            this.priority = priority;
        }
    }

    /**
     * Inserts an element into the priority queue based on its priority.
     * @param value The value to be added.
     * @param priority The priority of the value (lower value means higher priority).
     */
    public void enqueue(String value, int priority) {
        Node newNode = new Node(value, priority);
        boolean contain = false;

        for (int i = 0; i < queueArray.size(); i++) {
            if (queueArray.get(i).priority > newNode.priority) {
                queueArray.add(i, newNode);
                contain = true;
                break;
            }
        }
        if (!contain) {  // If no position was found, add at the end.
            queueArray.add(newNode);
        }
    }

    /**
     * Removes and returns the element with the highest priority.
     * @return The value of the removed element or "empty array" if the queue is empty.
     */
    public String dequeue() {
        if (queueArray.isEmpty()) {
            return "empty array";
        }
        Node toRemove = queueArray.get(0);
        queueArray.remove(0);
        return toRemove.value;
    }

    /**
     * Returns the front element of the priority queue without removing it.
     * @return The front node or null if the queue is empty.
     */
    public Node front() {
        return queueArray.isEmpty() ? null : queueArray.get(0);
    }

    /**
     * Returns the rear element of the priority queue without removing it.
     * @return The rear node or null if the queue is empty.
     */
    public Node rear() {
        return queueArray.isEmpty() ? null : queueArray.get(queueArray.size() - 1);
    }

    /**
     * Checks if the priority queue is empty.
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return queueArray.isEmpty();
    }

    /**
     * Prints the queue as an array of values in priority order.
     * @return A string array of queue values.
     */
    public String[] printQueue() {
        String[] array = new String[queueArray.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = queueArray.get(i).value;
        }
        return array;
    }

    /**
     * Main method to demonstrate the priority queue operations.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        PriorityQueues queue = new PriorityQueues();
        queue.enqueue("akshay", 3);
        queue.enqueue("rohit", 1);
        queue.enqueue("adam", 2);
        queue.enqueue("Elon", 5);
        queue.enqueue("Bill", 4);
        
        System.out.println("Initial Queue: " + Arrays.toString(queue.printQueue()));
        System.out.println("Dequeued: " + queue.dequeue());
        System.out.println("Queue after dequeue: " + Arrays.toString(queue.printQueue()));
        System.out.println("Front element: " + (queue.front() != null ? queue.front().value : "null"));
        System.out.println("Rear element: " + (queue.rear() != null ? queue.rear().value : "null"));
        System.out.println("Is queue empty? " + queue.isEmpty());
    }
}
