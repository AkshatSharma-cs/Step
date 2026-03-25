import java.util.*;

/**
 * ============================================================
 * CLASS - RoomInventory
 * ============================================================
 *
 * Simplified inventory for cancellation purposes.
 * (Reused from earlier use cases.)
 *
 * @version 10.0
 */
class RoomInventory {
    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        initializeInventory();
    }

    private void initializeInventory() {
        roomAvailability.put("Single", 5);
        roomAvailability.put("Double", 3);
        roomAvailability.put("Suite", 2);
    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }

    public void incrementAvailability(String roomType) {
        int current = roomAvailability.getOrDefault(roomType, 0);
        roomAvailability.put(roomType, current + 1);
    }
}

/**
 * ============================================================
 * CLASS - CancellationService
 * ============================================================
 *
 * Use Case 10: Booking Cancellation & Inventory Rollback
 *
 * Description:
 * Handles booking cancellations safely.
 * Tracks released room IDs in a stack (LIFO).
 * Restores inventory immediately after cancellation.
 *
 * @version 10.0
 */
class CancellationService {
    private Stack<String> releasedRooms;
    private Map<String, String> reservationRoomTypeMap;

    public CancellationService() {
        releasedRooms = new Stack<>();
        reservationRoomTypeMap = new HashMap<>();
    }

    // Register a confirmed booking
    public void registerBooking(String reservationId, String roomType) {
        reservationRoomTypeMap.put(reservationId, roomType);
    }

    // Cancel a booking and restore inventory
    public void cancelBooking(String reservationId, RoomInventory inventory) {
        if (!reservationRoomTypeMap.containsKey(reservationId)) {
            System.out.println("Invalid cancellation: Reservation ID not found.");
            return;
        }

        String roomType = reservationRoomTypeMap.get(reservationId);

        // Push cancelled room ID onto stack
        releasedRooms.push(reservationId);

        // Restore inventory
        inventory.incrementAvailability(roomType);

        // Remove reservation from active map
        reservationRoomTypeMap.remove(reservationId);

        System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType);
    }

    // Show rollback history
    public void showRollbackHistory() {
        System.out.println("\nRollback History (Most Recent First):");
        while (!releasedRooms.isEmpty()) {
            System.out.println("Restored Reservation ID: " + releasedRooms.pop());
        }
    }
}

/**
 * ============================================================
 * MAIN CLASS - BookMyStayApp
 * ============================================================
 *
 * Use Case 10: Booking Cancellation & Inventory Rollback
 *
 * Description:
 * Demonstrates how confirmed bookings can be cancelled safely.
 * Inventory is restored and rollback history is maintained.
 *
 * @version 10.0
 */
public class BookMyStayApp {
    public static void main(String[] args) {
        System.out.println("Booking Cancellation\n");

        RoomInventory inventory = new RoomInventory();
        CancellationService cancellationService = new CancellationService();

        // Register confirmed bookings
        cancellationService.registerBooking("Single-1", "Single");
        cancellationService.registerBooking("Double-1", "Double");

        // Cancel one booking
        cancellationService.cancelBooking("Single-1", inventory);

        // Show rollback history
        cancellationService.showRollbackHistory();

        // Display updated inventory
        System.out.println("\nUpdated Single Room Availability: " +
                inventory.getRoomAvailability().get("Single"));
    }
}
