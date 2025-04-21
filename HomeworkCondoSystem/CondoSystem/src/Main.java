import java.util.*;

class Condo {
    int floor;
    int room;
    String owner;

    Condo(int floor, int room) {
        this.floor = floor;
        this.room = room;
        this.owner = null;
    }

    boolean isAvailable() {
        return owner == null;
    }

    String getRoomLabel() {
        return "Floor " + floor + ", Room " + room;
    }
}

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<Condo> condos = new ArrayList<>();

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n=== Condo Management System ===");
            System.out.println("1. Setup Condo (Floor and Room)");
            System.out.println("2. Buy Condo");
            System.out.println("3. Sell Condo");
            System.out.println("4. Search Condo Owner");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    setupCondo();
                    break;
                case 2:
                    buyCondo();
                    break;
                case 3:
                    sellCondo();
                    break;
                case 4:
                    searchCondoOwner();
                    break;
                case 5:
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (choice != 5);
    }

    static void setupCondo() {
        System.out.print("Enter number of floors: ");
        int floors = scanner.nextInt();
        System.out.print("Enter number of rooms per floor: ");
        int rooms = scanner.nextInt();

        condos.clear();
        for (int f = 1; f <= floors; f++) {
            for (int r = 1; r <= rooms; r++) {
                condos.add(new Condo(f, r));
            }
        }

        System.out.println("Condo setup complete: " + floors + " floors, " + rooms + " rooms per floor.");
    }

    static void buyCondo() {
        System.out.print("Enter floor: ");
        int floor = scanner.nextInt();
        System.out.print("Enter room: ");
        int room = scanner.nextInt();
        scanner.nextLine();
        Condo condo = findCondo(floor, room);

        if (condo == null) {
            System.out.println("Condo not found.");
        } else if (!condo.isAvailable()) {
            System.out.println("Condo already owned by: " + condo.owner);
        } else {
            System.out.print("Enter buyer's name: ");
            String name = scanner.nextLine();
            condo.owner = name;
            System.out.println("Condo bought successfully.");
        }
    }

    static void sellCondo() {
        System.out.print("Enter floor: ");
        int floor = scanner.nextInt();
        System.out.print("Enter room: ");
        int room = scanner.nextInt();
        Condo condo = findCondo(floor, room);

        if (condo == null) {
            System.out.println("Condo not found.");
        } else if (condo.isAvailable()) {
            System.out.println("This condo is already available.");
        } else {
            condo.owner = null;
            System.out.println("Condo is now available for sale.");
        }
    }

    static void searchCondoOwner() {
        System.out.print("Enter owner name to search: ");
        String name = scanner.nextLine();
        boolean found = false;

        for (Condo condo : condos) {
            if (name.equalsIgnoreCase(condo.owner)) {
                System.out.println("Owned Condo: " + condo.getRoomLabel());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No condos found for owner: " + name);
        }
    }

    static Condo findCondo(int floor, int room) {
        for (Condo condo : condos) {
            if (condo.floor == floor && condo.room == room) {
                return condo;
            }
        }
        return null;
    }
}
