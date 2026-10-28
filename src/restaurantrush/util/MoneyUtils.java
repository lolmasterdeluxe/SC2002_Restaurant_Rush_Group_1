package restaurantrush.util;

public class MoneyUtils {

    /**
     * Formats integer cents into standard currency display string (e.g., 1800 -> "$18.00").
     */
    public static String formatCents(int cents) {
        return String.format("$%.2f", cents / 100.0);
    }
}