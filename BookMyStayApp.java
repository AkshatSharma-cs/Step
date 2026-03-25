import java.util.*;

/**
 * ============================================================
 * CLASS - Reservation
 * ============================================================
 *
 * Represents a confirmed booking request.
 * (Reused from earlier use cases.)
 *
 * @version 8.0
 */
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }
}

/**
 * ============================================================
 * CLASS - BookingHistory
 * ============================================================
 *
 * Use Case 8: Booking History & Reporting
 *
 * Description:
 * Maintains a record of confirmed reservations
 * in insertion order for audit and reporting.
 *
 * @version 8.0
 */
class BookingHistory {
    private List<Reservation> confirmedReservations;

    public BookingHistory() {
        confirmedReservations = new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {
        confirmedReservations.add(reservation);
    }

    public List<Reservation> getConfirmedReservations() {
        return confirmedReservations;
    }
}

/**
 * ============================================================
 * CLASS - BookingReportService
 * ============================================================
 *
 * Use Case 8: Booking History & Reporting
 *
 * Description:
 * Generates reports from booking history data.
 * Reporting logic is separated from storage.
 *
 * @version 8.0
 */
class BookingReportService {
    public void generateReport(BookingHistory history) {
        System.out.println("Booking History Report\n");
        for (Reservation r : history.getConfirmedReservations()) {
            System.out.println("Guest: " + r.getGuestName() +
                    ", Room Type: " + r.getRoomType());
        }
    }
}

/**
 * ============================================================
 * MAIN CLASS - BookMyStayApp
 * ============================================================
 *
 * Use Case 8: Booking History & Reporting
 *
 * Description:
 * Demonstrates how confirmed bookings are stored
 * and reported, maintaining an ordered audit trail.
 *
 * @version 8.0
 */
public class BookMyStayApp {
    public static void main(String[] args) {
        System.out.println("Booking History and Reporting\n");

        // Initialize booking history
        BookingHistory history = new BookingHistory();

        // Add confirmed reservations (simulated)
        history.addReservation(new Reservation("Abhi", "Single"));
        history.addReservation(new Reservation("Subha", "Double"));
        history.addReservation(new Reservation("Yamathi", "Suite"));

        // Generate report
        BookingReportService reportService = new BookingReportService();
        reportService.generateReport(history);
    }
}
