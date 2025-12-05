package recurssion;
 
/**
 * OrderService is responsible for placing new orders.
 * 
 * BAD DESIGN EXAMPLE:
 * - This class depends on InventoryService AND PaymentService.
 * - It is also CALLED BACK by NotificationService, completing
 *   a circular dependency chain:
 *
 *   OrderService → InventoryService → PaymentService → NotificationService → OrderService
 */
public class OrderService {

    private final InventoryService inventoryService = new InventoryService();
    private final PaymentService paymentService = new PaymentService();

    /**
     * Attempts to place an order.
     *
     * @param sku the product SKU.
     * @param quantity the product quantity.
     */
    public void placeOrder(String sku, int quantity) {

        // ❌ BAD: Order depends directly on Inventory
        if (!inventoryService.checkStock(sku, quantity)) {
            throw new IllegalStateException("Stock unavailable");
        }

        // ❌ BAD: Order depends directly on Payment
        boolean paymentSuccess = paymentService.chargeCustomer("customer-123", 199.99);

        if (!paymentSuccess) {
            throw new IllegalStateException("Payment failed");
        }

        System.out.println("Order placed successfully.");

        // ❌ WORST PART: Completing the cycle by calling NotificationService (indirectly)
        new NotificationService().sendOrderConfirmation("customer-123", "ORDER-001");
    }

    /** Called BACK by NotificationService, causing recursion risk. */
    public void onNotificationLogged(String message) {
        System.out.println("OrderService received callback: " + message);
    }
}
