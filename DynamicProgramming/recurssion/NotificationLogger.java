package recurssion;

/**
 * Callback used by NotificationService to log events without
 * creating a circular dependency on OrderService.
 */
public interface NotificationLogger {
    void onNotificationLogged(String message);
}
