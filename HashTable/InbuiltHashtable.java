/**
 * The InbuiltHashtable class demonstrates the usage of Java's built-in Hashtable
 * for storing and managing key-value pairs. It showcases basic operations such as
 * inserting entries, accessing values, and removing entries. Additionally, it
 * highlights the differences between Hashtable and other similar data structures
 * like HashMap and HashSet.
 *
 * <p>
 * <b>Key Points:</b>
 * <ul>
 *     <li>Hashtable is synchronized and thread-safe.</li>
 *     <li>HashMap is unsynchronized and generally preferred for single-threaded applications.</li>
 *     <li>HashSet is used for storing unique elements and is backed by a HashMap.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <b>Example Usage:</b>
 * <pre>
 * {@code
 * phoneBook: {Mary=865656223, Chris=445454545, Morris=4548623233}
 * Chris's phoneNo.445454545
 * remove Morris: 4548623233
 * phoneBook: {Mary=865656223, Chris=445454545}
 * }
 * </pre>
 * </p>
 */
import java.util.Hashtable;
import java.util.Map;

public class InbuiltHashtable {

    /**
     * The main method serves as the entry point of the program. It demonstrates
     * how to create a Hashtable, insert key-value pairs, access values using keys,
     * remove entries, and display the contents of the Hashtable at different stages.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Initialize a Hashtable to store phone book entries.
        // Hashtable does not allow null keys or values.
        // It is synchronized, making it thread-safe.
        // Alternative data structures:
        // - HashMap: Unsynchronized, allows one null key and multiple null values.
        // - HashSet: Used for storing unique elements, backed by a HashMap.
        Map<String, String> phoneBook = new Hashtable<>();

        // Inserting key-value pairs into the Hashtable using the put() method.
        phoneBook.put("Chris", "445454545");
        phoneBook.put("Morris", "4548623233");
        phoneBook.put("Mary", "865656223");

        // Displaying the entire Hashtable.
        // The output does not guarantee any specific order of elements.
        System.out.println("phoneBook: " + phoneBook);

        // Accessing a value using its corresponding key with the get() method.
        String chrisPhone = phoneBook.get("Chris");
        System.out.println("Chris's phoneNo.: " + chrisPhone);

        // Removing an entry from the Hashtable using the remove() method.
        // It returns the value associated with the removed key.
        String removedPhone = phoneBook.remove("Morris");
        System.out.println("remove Morris: " + removedPhone);

        // Displaying the Hashtable after removing an entry.
        System.out.println("phoneBook after removing Morris: " + phoneBook);

        // Attempting to retrieve a removed entry.
        String morrisPhone = phoneBook.get("Morris");
        System.out.println("Morris's phoneNo. after removal: " + morrisPhone);

        // Demonstrating that Hashtable does not allow null keys or values.
        try {
            phoneBook.put(null, "000000000");
        } catch (NullPointerException e) {
            System.out.println("Error: Hashtable does not allow null keys.");
        }

        try {
            phoneBook.put("NullValue", null);
        } catch (NullPointerException e) {
            System.out.println("Error: Hashtable does not allow null values.");
        }

        // Iterating over the Hashtable entries using entrySet().
        System.out.println("\nIterating over phoneBook entries:");
        for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        // Checking if a key exists in the Hashtable using containsKey().
        String keyToCheck = "Mary";
        if (phoneBook.containsKey(keyToCheck)) {
            System.out.println("\n" + keyToCheck + " exists in the phoneBook.");
        } else {
            System.out.println("\n" + keyToCheck + " does not exist in the phoneBook.");
        }

        // Checking if a value exists in the Hashtable using containsValue().
        String valueToCheck = "865656223";
        if (phoneBook.containsValue(valueToCheck)) {
            System.out.println(valueToCheck + " exists in the phoneBook.");
        } else {
            System.out.println(valueToCheck + " does not exist in the phoneBook.");
        }
    }
}
