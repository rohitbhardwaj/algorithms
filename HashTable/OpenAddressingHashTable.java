/**
 * This class implements a Hash Table using Open Addressing with Linear Probing.
 * Methods include:
 * - Insert (O(1) average, O(N) worst-case complexity)
 * - Search (O(1) average, O(N) worst-case complexity)
 * - Delete (O(1) average, O(N) worst-case complexity)
 */
import java.util.Arrays;

public class OpenAddressingHashTable {
    private static final int EMPTY = -1;
    private static final int DELETED = -2;
    private int[] table;
    private int size;
    
    /**
     * Constructor to initialize the hash table.
     * @param capacity The size of the hash table.
     */
    public OpenAddressingHashTable(int capacity) {
        this.table = new int[capacity];
        Arrays.fill(this.table, EMPTY);
        this.size = 0;
    }
    
    /**
     * Hash function for indexing.
     * @param key The key to be hashed.
     * @return The hash index.
     */
    private int hash(int key) {
        return key % table.length;
    }
    
    /**
     * Inserts a key into the hash table using linear probing.
     * @param key The key to insert.
     * @return true if insertion is successful, false if the table is full.
     */
    public boolean insert(int key) {
        if (size == table.length) return false; // Table full
        
        int index = hash(key);
        while (table[index] != EMPTY && table[index] != DELETED) {
            index = (index + 1) % table.length; // Linear probing
        }
        
        table[index] = key;
        size++;
        return true;
    }
    
    /**
     * Searches for a key in the hash table.
     * @param key The key to search.
     * @return true if key is found, false otherwise.
     */
    public boolean search(int key) {
        int index = hash(key);
        int start = index;
        
        while (table[index] != EMPTY) {
            if (table[index] == key) return true;
            index = (index + 1) % table.length;
            if (index == start) break; // Avoid infinite loop
        }
        return false;
    }
    
    /**
     * Deletes a key from the hash table.
     * @param key The key to delete.
     * @return true if deletion is successful, false if the key is not found.
     */
    public boolean delete(int key) {
        int index = hash(key);
        int start = index;
        
        while (table[index] != EMPTY) {
            if (table[index] == key) {
                table[index] = DELETED;
                size--;
                return true;
            }
            index = (index + 1) % table.length;
            if (index == start) break; // Avoid infinite loop
        }
        return false;
    }
    
    /**
     * Prints the current state of the hash table.
     */
    public void printTable() {
        System.out.println(Arrays.toString(table));
    }
    
    /**
     * Main method to demonstrate hash table operations.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        OpenAddressingHashTable hashTable = new OpenAddressingHashTable(7);
        
        // Insert elements
        hashTable.insert(10);
        hashTable.insert(20);
        hashTable.insert(5);
        hashTable.insert(15);
        hashTable.insert(30);
        
        // Print table
        System.out.println("Hash Table:");
        hashTable.printTable();
        
        // Search for elements
        System.out.println("Search 15: " + hashTable.search(15)); // Expected: true
        System.out.println("Search 25: " + hashTable.search(25)); // Expected: false
        
        // Delete an element
        System.out.println("Delete 15: " + hashTable.delete(15)); // Expected: true
        hashTable.printTable();
    }
}
