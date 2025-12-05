package recurssion;
 
/**
 * OrderService is responsible for placing new orders.
 * 
 * Redesigned to avoid circular dependencies while keeping concerns clear.
 */
public class OrderService {

    private final InventoryService inventoryService;
    private final PaymentService paymentService;
    private final NotificationService notificationService;

    public OrderService() {
        this(new InventoryService(), null, null);
    }

    OrderService(InventoryService inventoryService, PaymentService paymentService, NotificationService notificationService) {
        this.inventoryService = inventoryService;
        this.notificationService = notificationService != null ? notificationService : new NotificationService(this::onNotificationLogged);
        this.paymentService = paymentService != null ? paymentService : new PaymentService(this.notificationService);
    }

    /**
     * Attempts to place an order.
     *
     * @param sku the product SKU.
     * @param quantity the product quantity.
     */
    public void placeOrder(String sku, int quantity) {

        if (!inventoryService.checkStock(sku, quantity)) {
            throw new IllegalStateException("Stock unavailable");
        }

        boolean paymentSuccess = paymentService.chargeCustomer("customer-123", 199.99);

        if (!paymentSuccess) {
            throw new IllegalStateException("Payment failed");
        }

        System.out.println("Order placed successfully.");

        notificationService.sendOrderConfirmation("customer-123", "ORDER-001");
    }

    public void onNotificationLogged(String message) {
        System.out.println("OrderService received callback: " + message);
    }
}
