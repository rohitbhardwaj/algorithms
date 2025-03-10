/**
 * Implementation of a Stack using a Linked List.
 * This class provides standard stack operations: push, pop, peek, and checking if the stack is empty.
 * It maintains a reference to the top and bottom nodes, allowing efficient stack operations.
 */
public class StackWithLinkedList {
    private Node top; // Top of the stack
    private Node bottom; // Bottom of the stack
    private int length; // Number of elements in the stack

    /**
     * Inner class representing a node in the linked list.
     */
    private static class Node {
        String value;
        Node next;

        Node(String value) {
            this.value = value;
            this.next = null;
        }
    }

    /**
     * Constructor to initialize an empty stack.
     */
    public StackWithLinkedList() {
        top = null;
        bottom = null;
        length = 0;
    }

    /**
     * Returns the top element of the stack without removing it.
     * @return the top element, or null if the stack is empty.
     */
    public String peek() {
        return (length > 0) ? top.value : null;
    }

    /**
     * Pushes a new element onto the stack.
     * @param value the value to be pushed.
     */
    public void push(String value) {
        Node newNode = new Node(value);
        if (length == 0) {
            top = newNode;
            bottom = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
        length++;
    }

    /**
     * Removes the top element from the stack.
     */
    public void pop() {
        if (length > 0) {
            top = top.next;
            length--;
            if (length == 0) {
                bottom = null;
            }
        }
    }

    /**
     * Checks if the stack is empty.
     * @return true if the stack is empty, false otherwise.
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * Returns the bottom element of the stack.
     * @return the bottom element, or null if the stack is empty.
     */
    public String getLastElement() {
        return (length > 0) ? bottom.value : null;
    }

    /**
     * Main method to demonstrate the stack operations.
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        StackWithLinkedList myStack = new StackWithLinkedList();
        myStack.push("Google");
        myStack.push("NFJS");
        myStack.push("Apple");
        System.out.println("Top element: " + myStack.peek()); // Expected: Apple
        myStack.pop();
        System.out.println("Is stack empty? " + myStack.isEmpty()); // Expected: false
        System.out.println("Bottom element: " + myStack.getLastElement()); // Expected: Google
    }
}