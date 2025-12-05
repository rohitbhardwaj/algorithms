package recurssion;
 
/**
 * NotificationService sends emails/SMS.
 *
 * Redesigned to avoid circular dependencies.
 * - Accepts a lightweight logger callback instead of reaching back into OrderService.
 */
public class NotificationService {

    private final NotificationLogger notificationLogger;

    public NotificationService(NotificationLogger notificationLogger) {
        this.notificationLogger = notificationLogger;
    }

    public NotificationService() {
        this(null);
    }

    /** Sends order confirmation and CALLBACKS OrderService. */
    public void sendOrderConfirmation(String customerId, String orderId) {
        System.out.println("Sending confirmation to: " + customerId);

        log("Confirmation sent for " + orderId);
    }

    /** Sends receipt message. */
    public void sendPaymentReceipt(String customerId, double amount) {
        System.out.println("Sending payment receipt to " + customerId);

        log("Payment receipt for $" + amount);
    }

    private void log(String message) {
        if (notificationLogger != null) {
            notificationLogger.onNotificationLogged(message);
        }
    }
}
