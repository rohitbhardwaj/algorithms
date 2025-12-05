package recurssion;
 
/**
 * NotificationService sends emails/SMS.
 *
 * WORST PART:
 * - Calls OrderService back again → completing circular dependency.
 * - Causes potential recursion, retry storms, or stack overflow in advanced flows.
 */
public class NotificationService {

    private final OrderService orderService = new OrderService();

    /** Sends order confirmation and CALLBACKS OrderService. */
    public void sendOrderConfirmation(String customerId, String orderId) {
        System.out.println("Sending confirmation to: " + customerId);

        // ❌ CYCLE COMPLETED: This calls back into OrderService
        orderService.onNotificationLogged("Confirmation sent for " + orderId);
    }

    /** Sends receipt message. */
    public void sendPaymentReceipt(String customerId, double amount) {
        System.out.println("Sending payment receipt to " + customerId);

        // ❌ Again calling OrderService! Very bad.
        orderService.onNotificationLogged("Payment receipt for $" + amount);
    }
}
