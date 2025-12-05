package recurssion;

/**
 * PaymentService processes payments.
 *
 * BAD DESIGN:
 * - Depends on NotificationService.
 * - Should only handle transactions, not messaging or logging.
 */
public class PaymentService {

    private final NotificationService notificationService = new NotificationService();

    /**
     * Charges a customer.
     *
     * @param customerId ID of customer
     * @param amount amount to charge
     * @return true if successful
     */
    public boolean chargeCustomer(String customerId, double amount) {

        System.out.println("Charging customer: " + customerId + " $" + amount);

        // ❌ BAD: Cross-service messaging dependency
        notificationService.sendPaymentReceipt(customerId, amount);

        return true;
    }

    /** Reserves funds (for InventoryService). */
    public void reserveFunds(String customerId, double amount) {
        System.out.println("Reserving $" + amount + " for customer " + customerId);
    }
}
