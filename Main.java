import java.util.*;

class Room {
    int id;
    boolean booked = false;

    Room(int id) {
        this.id = id;
    }
}

class Hotel {
    List<Room> rooms = new ArrayList<>();

    Hotel() {
        rooms.add(new Room(101));
        rooms.add(new Room(102));
    }

    synchronized void bookRoom(int id) {
        for (Room r : rooms) {
            if (r.id == id && !r.booked) {
                r.booked = true;
                System.out.println("Room " + id + " booked successfully.");
                return;
            }
        }
        System.out.println("Room unavailable!");
    }

    synchronized void cancelRoom(int id) {
        for (Room r : rooms) {
            if (r.id == id && r.booked) {
                r.booked = false;
                System.out.println("Booking cancelled for room " + id);
                return;
            }
        }
        System.out.println("No active booking found.");
    }

    void showRooms() {
        for (Room r : rooms)
            System.out.println("Room " + r.id + ": " +
                (r.booked ? "Booked" : "Available"));
    }
}

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        hotel.showRooms();
        hotel.bookRoom(101);
        hotel.bookRoom(101);
        hotel.cancelRoom(101);
        hotel.showRooms();
    }
}