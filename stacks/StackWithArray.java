import java.util.ArrayList;

public class StackWithArray {
    ArrayList<String> stackArray;

    public StackWithArray() {
        this.stackArray = new ArrayList<>();
    }

    public String peek() {
        if (this.stackArray.size() > 0) {
            return stackArray.get(stackArray.size() - 1);
        } else {
            return null;
        }
    }

    public void push(String value) {
        this.stackArray.add(value);
    }

    public void pop() {
        this.stackArray.remove(stackArray.size() - 1);
    }

    public boolean isEmpty() {
        return stackArray.size() == 0;
    }

    public static void main(String[] args) {
        StackWithArray myStack = new StackWithArray();
        myStack.push("Google");
        myStack.push("NFJS");
        myStack.push("Apple");
        System.out.println(myStack.peek());
        myStack.pop();
        System.out.println(myStack.peek());
        System.out.println(myStack.isEmpty());
        myStack.pop();
        System.out.println(myStack.peek());
        System.out.println(myStack.isEmpty());
        myStack.pop();
        System.out.println(myStack.peek());
        System.out.println(myStack.isEmpty());      
    }
}