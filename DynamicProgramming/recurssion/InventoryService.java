package recurssion;
 
/**
 * InventoryService checks stock levels.
 *
 * BAD DESIGN:
 * - Knows about PaymentService (tight coupling).
 * - Should be isolated, but instead calls PaymentService for "reservation fees."
 */
public class InventoryService {

    private final PaymentService paymentService = new PaymentService();

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

        // ❌ BAD: Inventory randomly forces a fee payment,
        // creating a hidden dependency
        paymentService.reserveFunds("customer-123", 10.00);

        return true; // pretend stock exists
    }
}
