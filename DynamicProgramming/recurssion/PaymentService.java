package recurssion;

/**
 * PaymentService processes payments.
 *
 * Collaborates with NotificationService via constructor injection so
 * messaging can be composed without hard dependencies.
 */
public class PaymentService {

    private final NotificationService notificationService;

    public PaymentService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    /**
     * Charges a customer.
     *
     * @param customerId ID of customer
     * @param amount amount to charge
     * @return true if successful
     */
    public boolean chargeCustomer(String customerId, double amount) {

        System.out.println("Charging customer: " + customerId + " $" + amount);

        if (notificationService != null) {
            notificationService.sendPaymentReceipt(customerId, amount);
        }

        return true;
    }

    /** Reserves funds (for InventoryService). */
    public void reserveFunds(String customerId, double amount) {
        System.out.println("Reserving $" + amount + " for customer " + customerId);
    }
}
