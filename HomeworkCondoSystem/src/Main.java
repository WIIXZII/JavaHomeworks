import java.util.*;

class Room {
    String guestName = "";
    boolean isOccupied = false;
    List<String> history = new ArrayList<>();
}

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Room[] rooms;
    static int totalRooms;

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Condo Management System ---");
            System.out.println("1. Setup condo");
            System.out.println("2. Display rooms");
            System.out.println("3. Check-in room");
            System.out.println("4. Check-out room");
            System.out.println("5. View history");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1: setupCondo(); break;
                case 2: displayRooms(); break;
                case 3: checkInRoom(); break;
                case 4: checkOutRoom(); break;
                case 5: viewHistory(); break;
                case 6: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 6);
    }

    static void setupCondo() {
        System.out.print("Enter number of rooms to setup: ");
        totalRooms = scanner.nextInt();
        scanner.nextLine();
        rooms = new Room[totalRooms];
        for (int i = 0; i < totalRooms; i++) {
            rooms[i] = new Room();
        }
        System.out.println("Condo setup complete with " + totalRooms + " rooms.");
    }

    static void displayRooms() {
        if (rooms == null) {
            System.out.println("Please setup condo first.");
            return;
        }
        System.out.println("Room Status:");
        for (int i = 0; i < rooms.length; i++) {
            System.out.printf("Room %d: %s%n", i + 1, rooms[i].isOccupied ? "Occupied by " + rooms[i].guestName : "Available");
        }
    }

    static void checkInRoom() {
        if (rooms == null) {
            System.out.println("Please setup condo first.");
            return;
        }
        System.out.print("Enter room number to check in: ");
        int roomNum = scanner.nextInt();
        scanner.nextLine();
        if (roomNum < 1 || roomNum > totalRooms) {
            System.out.println("Invalid room number.");
            return;
        }

        Room room = rooms[roomNum - 1];
        if (room.isOccupied) {
            System.out.println("Room is already occupied.");
        } else {
            System.out.print("Enter guest name: ");
            String name = scanner.nextLine();
            room.guestName = name;
            room.isOccupied = true;
            room.history.add("Checked in: " + name);
            System.out.println("Guest checked in.");
        }
    }

    static void checkOutRoom() {
        if (rooms == null) {
            System.out.println("Please setup condo first.");
            return;
        }
        System.out.print("Enter room number to check out: ");
        int roomNum = scanner.nextInt();
        scanner.nextLine();
        if (roomNum < 1 || roomNum > totalRooms) {
            System.out.println("Invalid room number.");
            return;
        }

        Room room = rooms[roomNum - 1];
        if (!room.isOccupied) {
            System.out.println("Room is already available.");
        } else {
            room.history.add("Checked out: " + room.guestName);
            room.guestName = "";
            room.isOccupied = false;
            System.out.println("Guest checked out.");
        }
    }

    static void viewHistory() {
        if (rooms == null) {
            System.out.println("Please setup condo first.");
            return;
        }
        System.out.print("Enter room number to view history: ");
        int roomNum = scanner.nextInt();
        scanner.nextLine();
        if (roomNum < 1 || roomNum > totalRooms) {
            System.out.println("Invalid room number.");
            return;
        }

        Room room = rooms[roomNum - 1];
        System.out.println("History for Room " + roomNum + ":");
        if (room.history.isEmpty()) {
            System.out.println("No history available.");
        } else {
            for (String record : room.history) {
                System.out.println("- " + record);
            }
        }
    }
}
