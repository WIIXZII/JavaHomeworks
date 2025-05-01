import java.util.*;

class Room {
    String guestName = "";
    boolean isOccupied = false;
    List<String> history = new ArrayList<>();
}

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Room[][] rooms;
    static int floors, roomsPerFloor;

    public static void main(String[] args) {
        int choice = -1;
        do {
            System.out.println("\n--- Condo Management System ---");
            System.out.println("1. Setup condo");
            System.out.println("2. Display rooms");
            System.out.println("3. Check-in room");
            System.out.println("4. Check-out room");
            System.out.println("5. View history");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            try {
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
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number between 1 and 6.");
                scanner.nextLine(); // Clear the invalid input
            }

        } while (choice != 6);
    }


    static void setupCondo() {
        System.out.print("Enter number of floors: ");
        floors = scanner.nextInt();
        System.out.print("Enter number of rooms per floor: ");
        roomsPerFloor = scanner.nextInt();
        scanner.nextLine();
        rooms = new Room[floors][roomsPerFloor];
        for (int i = 0; i < floors; i++) {
            for (int j = 0; j < roomsPerFloor; j++) {
                rooms[i][j] = new Room();
            }
        }
        System.out.println("Condo setup complete.");
    }

    static void displayRooms() {
        if (rooms == null) {
            System.out.println("Please setup condo first.");
            return;
        }
        for (int i = 0; i < floors; i++) {
            System.out.println("Floor " + (i + 1) + ":");
            for (int j = 0; j < roomsPerFloor; j++) {
                Room room = rooms[i][j];
                System.out.printf("  Room %d: %s%n", j + 1,
                        room.isOccupied ? "Occupied by " + room.guestName : "Available");
            }
        }
    }

    static void checkInRoom() {
        if (rooms == null) {
            System.out.println("Please setup condo first.");
            return;
        }
        System.out.print("Enter floor number: ");
        int floor = scanner.nextInt() - 1;
        System.out.print("Enter room number: ");
        int roomNum = scanner.nextInt() - 1;
        scanner.nextLine();

        if (!isValidRoom(floor, roomNum)) return;

        Room room = rooms[floor][roomNum];
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
        System.out.print("Enter floor number: ");
        int floor = scanner.nextInt() - 1;
        System.out.print("Enter room number: ");
        int roomNum = scanner.nextInt() - 1;
        scanner.nextLine();

        if (!isValidRoom(floor, roomNum)) return;

        Room room = rooms[floor][roomNum];
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
        System.out.print("Enter floor number: ");
        int floor = scanner.nextInt() - 1;
        System.out.print("Enter room number: ");
        int roomNum = scanner.nextInt() - 1;
        scanner.nextLine();

        if (!isValidRoom(floor, roomNum)) return;

        Room room = rooms[floor][roomNum];
        System.out.println("History for Floor " + (floor + 1) + ", Room " + (roomNum + 1) + ":");
        if (room.history.isEmpty()) {
            System.out.println("No history available.");
        } else {
            for (String record : room.history) {
                System.out.println("- " + record);
            }
        }
    }

    static boolean isValidRoom(int floor, int roomNum) {
        if (floor < 0 || floor >= floors || roomNum < 0 || roomNum >= roomsPerFloor) {
            System.out.println("Invalid floor or room number.");
            return false;
        }
        return true;
    }
}
