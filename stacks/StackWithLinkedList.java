public class StackWithLinkedList {
    private Node top;
    private Node bottom;
    private int length;

    public StackWithLinkedList() {
        top = null;
        bottom = null;
        length = 0;
    }

    public String peek() {
        if (length > 0) {
            return top.value;
        } else {
            return null;
        }
    }

    public void push(String value) {
        Node newNode = new Node(value);
        if (length == 0) {
            top = newNode;
            bottom = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
        this.length++;
    }

    public void pop() {
        if (length > 0) {
            top = top.next;

            if (length == 1) {
                bottom = null;
            }
            length--;
        }
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public String getLastElement() {
        if (length > 0) {
            return bottom.value;
        }
        return null;
    }

    public static void main(String[] args) {
        StackWithLinkedList myStack = new StackWithLinkedList();
        myStack.push("Google");
        myStack.push("NFJS");
        myStack.push("Apple");
        System.out.println(myStack.peek());
        myStack.pop();
        System.out.println(myStack.isEmpty());
        System.out.println(myStack.getLastElement());
    }
}