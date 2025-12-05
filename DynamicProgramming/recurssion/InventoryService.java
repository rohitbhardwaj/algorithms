package recurssion;
 
/**
 * InventoryService checks stock levels.
 *
 * GOOD DESIGN:
 * - Focuses purely on inventory concerns.
 * - No longer triggers payments or other cross-cutting concerns.
 */
public class InventoryService {

    /**
     * Checks whether stock exists for the given SKU.
     * Also forces a payment interaction (BAD).
     *
     * @param sku product identifier
     * @param qty requested quantity
     * @return true if available
     */
    public boolean checkStock(String sku, int qty) {

        System.out.println("Checking stock for " + sku);

        return true; // pretend stock exists
    }
}
