package Torus_1;
import java.time.LocalTime;

public class MarketTimeChecker {

    // Define market timings (You can adjust these times as per the actual market hours)
    private static final LocalTime PRE_MARKET_START = LocalTime.of(9, 0);  // 9:00 AM
    private static final LocalTime PRE_MARKET_END = LocalTime.of(9, 15);   // 9:15 AM
    private static final LocalTime MARKET_START = LocalTime.of(9, 15);     // 9:15 AM
    private static final LocalTime MARKET_END = LocalTime.of(15, 30);      // 3:30 PM
    private static final LocalTime POST_MARKET_START = LocalTime.of(15, 30); // 3:30 PM
    private static final LocalTime POST_MARKET_END = LocalTime.of(16, 0);  // 4:00 PM

    public static String getMarketSession() {
        LocalTime currentTime = LocalTime.now(); // Get the current time

        if (currentTime.isBefore(PRE_MARKET_START)) {
            return "Pre-market hasn't started yet";
        } else if (currentTime.isAfter(PRE_MARKET_START) && currentTime.isBefore(PRE_MARKET_END)) {
            return "Pre-market hour";
        } else if (currentTime.isAfter(MARKET_START) && currentTime.isBefore(MARKET_END)) {
            return "Market hour";
        } else if (currentTime.isAfter(MARKET_END) && currentTime.isBefore(POST_MARKET_END)) {
            return "Post-market hour";
        } else {
            return "Market closed";
        }
    }

    public static void main(String[] args) {
        String marketSession = getMarketSession();
        System.out.println("Current time session: " + marketSession);
    }
}
