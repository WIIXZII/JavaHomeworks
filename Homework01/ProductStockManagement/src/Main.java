import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    static ArrayList<String> insertionHistory = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[][] products = null;
        boolean exit = false;
        do {
            System.out.println("\n========= STOCK MANAGEMENT MENU =========");
            System.out.println("1. Set up stocks");
            System.out.println("2. View product in stock");
            System.out.println("3. Insert product to stock catalogue");
            System.out.println("4. Update product in stock catalogue by product name");
            System.out.println("5. Delete product in stock catalogue by name");
            System.out.println("6. View insertion history in stock catalogue");
            System.out.println("7. Exit");
            System.out.print("Choose an option (1-7): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    Object[] result = setupStock(scanner);
                    products = (String[][]) result[1];
                    insertionHistory.clear();
                    System.out.println("\nStocks set up successfully!");
                    break;

                case "2":
                    if (products == null) {
                        System.out.println("Please set up the stocks first!");
                    } else {
                        displayStocks(products);
                    }
                    break;

                case "3":
                    if (products == null) {
                        System.out.println("Please set up the stocks first!");
                    } else {
                        insertProduct(scanner, products);
                    }
                    break;

                case "4":
                    if (products == null) {
                        System.out.println("Please set up the stocks first!");
                    } else {
                        updateProduct(scanner, products);
                    }
                    break;

                case "5":
                    if (products == null) {
                        System.out.println("Please set up the stocks first!");
                    } else {
                        deleteProduct(scanner, products);
                    }
                    break;

                case "6":
                    viewInsertionHistory();
                    break;

                case "7":
                    exit = true;
                    System.out.println("Exiting the program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (!exit);

        scanner.close();
    }

    public static Object[] setupStock(Scanner scanner) {
        System.out.print("Input number of stocks: ");
        int numStocks = Integer.parseInt(scanner.nextLine());

        int[] cataloguesPerStock = new int[numStocks];
        String[][] products = new String[numStocks][];

        for (int i = 0; i < numStocks; i++) {
            System.out.print("Input number of catalogues for stock " + (i + 1) + ": ");
            int count = Integer.parseInt(scanner.nextLine());
            cataloguesPerStock[i] = count;
            products[i] = new String[count];
        }

        return new Object[]{cataloguesPerStock, products};
    }

    public static void displayStocks(String[][] products) {
        System.out.println("\n====> STOCK VIEW <====");
        for (int i = 0; i < products.length; i++) {
            System.out.print("[+] Stock [" + (i + 1) + "] => ");
            for (int j = 0; j < products[i].length; j++) {
                if (products[i][j] == null) {
                    System.out.print("[ " + (j + 1) + " -> EMPTY ] ");
                } else {
                    System.out.print("[ " + (j + 1) + " -> " + products[i][j] + " ] ");
                }
            }
            System.out.println();
        }
    }

    public static void insertProduct(Scanner scanner, String[][] products) {
        try {
            System.out.print("Enter stock number: ");
            int stockNum = Integer.parseInt(scanner.nextLine()) - 1;

            if (stockNum < 0 || stockNum >= products.length) {
                System.out.println("Invalid stock number!");
                return;
            }

            System.out.print("Enter catalogue number: ");
            int catNum = Integer.parseInt(scanner.nextLine()) - 1;

            if (catNum < 0 || catNum >= products[stockNum].length) {
                System.out.println("Invalid catalogue number!");
                return;
            }

            if (products[stockNum][catNum] != null) {
                System.out.println("That spot is already occupied with: " + products[stockNum][catNum]);
                return;
            }

            System.out.print("Enter product name: ");
            String productName = scanner.nextLine();
            products[stockNum][catNum] = productName;
            insertionHistory.add("Inserted '" + productName + "' at Stock " + (stockNum + 1) + ", Catalogue " + (catNum + 1));
            System.out.println("Product inserted successfully!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numeric values.");
        }
    }

    public static void updateProduct(Scanner scanner, String[][] products) {
        System.out.print("Enter product name to update: ");
        String oldName = scanner.nextLine();
        boolean found = false;

        for (int i = 0; i < products.length; i++) {
            for (int j = 0; j < products[i].length; j++) {
                if (oldName.equalsIgnoreCase(products[i][j])) {
                    System.out.print("Enter new product name: ");
                    String newName = scanner.nextLine();
                    products[i][j] = newName;
                    insertionHistory.add("Updated '" + oldName + "' to '" + newName + "' at Stock " + (i + 1) + ", Catalogue " + (j + 1));
                    System.out.println("Product updated successfully!");
                    found = true;
                    break;
                }
            }
        }

        if (!found) {
            System.out.println("Product name not found.");
        }
    }

    public static void deleteProduct(Scanner scanner, String[][] products) {
        System.out.print("Enter product name to delete: ");
        String nameToDelete = scanner.nextLine();
        boolean deleted = false;

        for (int i = 0; i < products.length; i++) {
            for (int j = 0; j < products[i].length; j++) {
                if (nameToDelete.equalsIgnoreCase(products[i][j])) {
                    products[i][j] = null;
                    insertionHistory.add("Deleted '" + nameToDelete + "' from Stock " + (i + 1) + ", Catalogue " + (j + 1));
                    System.out.println("Product deleted successfully!");
                    deleted = true;
                    break;
                }
            }
        }

        if (!deleted) {
            System.out.println("Product name not found.");
        }
    }

    public static void viewInsertionHistory() {
        System.out.println("\n====> INSERTION HISTORY <====");
        if (insertionHistory.isEmpty()) {
            System.out.println("No history yet.");
        } else {
            for (String entry : insertionHistory) {
                System.out.println("- " + entry);
            }
        }
    }
}

