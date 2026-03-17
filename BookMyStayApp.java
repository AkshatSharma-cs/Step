import java.util.HashMap;
import java.util.Map;

/**
 * ============================================================
 * MAIN CLASS - BookMyStayApp
 * ============================================================
 *
 * Use Case 3: Centralized Room Inventory Management
 *
 * Description:
 * Demonstrates how room availability is managed
 * using a centralized inventory with HashMap.
 *
 * Room objects are used to retrieve pricing
 * and room characteristics.
 *
 * No booking or search logic is introduced here.
 *
 * @version 3.1
 */
public class BookMyStayApp {

    // Abstract Room class
    public static abstract class Room {
        protected int numberOfBeds;
        protected int squareFeet;
        protected double pricePerNight;

        public Room(int numberOfBeds, int squareFeet, double pricePerNight) {
            this.numberOfBeds = numberOfBeds;
            this.squareFeet = squareFeet;
            this.pricePerNight = pricePerNight;
        }

        public void displayRoomDetails() {
            System.out.println("Beds: " + numberOfBeds);
            System.out.println("Size: " + squareFeet + " sqft");
            System.out.println("Price per night: " + pricePerNight);
        }
    }

    // Concrete room classes
    public static class SingleRoom extends Room {
        public SingleRoom() { super(1, 250, 1500.0); }
    }

    public static class DoubleRoom extends Room {
        public DoubleRoom() { super(2, 400, 2500.0); }
    }

    public static class SuiteRoom extends Room {
        public SuiteRoom() { super(3, 750, 5000.0); }
    }

    // Centralized RoomInventory class
    public static class RoomInventory {
        private Map<String, Integer> roomAvailability;

        public RoomInventory() {
            roomAvailability = new HashMap<>();
            initializeInventory();
        }

        private void initializeInventory() {
            roomAvailability.put("SingleRoom", 5);
            roomAvailability.put("DoubleRoom", 3);
            roomAvailability.put("SuiteRoom", 2);
        }

        public Map<String, Integer> getRoomAvailability() {
            return roomAvailability;
        }

        public void updateAvailability(String roomType, int count) {
            roomAvailability.put(roomType, count);
        }
    }

    // Application entry point
    public static void main(String[] args) {
        System.out.println("Hotel Room Inventory Status\n");

        // Initialize room objects
        Room single = new SingleRoom();
        Room doubleR = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Initialize centralized inventory
        RoomInventory inventory = new RoomInventory();

        // Display details with availability
        System.out.println("Single Room:");
        single.displayRoomDetails();
        System.out.println("Available Rooms: " + inventory.getRoomAvailability().get("SingleRoom") + "\n");

        System.out.println("Double Room:");
        doubleR.displayRoomDetails();
        System.out.println("Available Rooms: " + inventory.getRoomAvailability().get("DoubleRoom") + "\n");

        System.out.println("Suite Room:");
        suite.displayRoomDetails();
        System.out.println("Available Rooms: " + inventory.getRoomAvailability().get("SuiteRoom") + "\n");
    }
}
