import java.util.Scanner;

public class Main {

    static final int MAX_PRODUCTS = 100;
    static final int ATTRIBUTES = 3; // Name, Quantity, Price
    static String[][] products = new String[MAX_PRODUCTS][ATTRIBUTES];
    static int productCount = 0;
    static String[] insertionHistory = new String[MAX_PRODUCTS];
    static int historyCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Product Stock Management System ===");
            System.out.println("1. Set Up Stock Catalogue");
            System.out.println("2. View Products in Stock");
            System.out.println("3. Insert Product");
            System.out.println("4. Update Product by Name");
            System.out.println("5. Delete Product by Name");
            System.out.println("6. View Insertion History");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear input buffer

            switch (choice) {
                case 1:
                    setUpStock(scanner);
                    break;
                case 2:
                    viewProducts();
                    break;
                case 3:
                    insertProduct(scanner);
                    break;
                case 4:
                    updateProduct(scanner);
                    break;
                case 5:
                    deleteProduct(scanner);
                    break;
                case 6:
                    viewHistory();
                    break;
                case 7:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 7);

        scanner.close();
    }

    static void setUpStock(Scanner scanner) {
        System.out.print("How many products to add initially? ");
        int count = scanner.nextInt();
        scanner.nextLine(); // Clear input buffer

        for (int i = 0; i < count; i++) {
            if (productCount >= MAX_PRODUCTS) {
                System.out.println("Stock full!");
                break;
            }

            System.out.print("Enter Product Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Quantity: ");
            String quantity = scanner.nextLine();
            System.out.print("Enter Price: ");
            String price = scanner.nextLine();

            products[productCount][0] = name;
            products[productCount][1] = quantity;
            products[productCount][2] = price;
            insertionHistory[historyCount++] = "Inserted: " + name;
            productCount++;
        }
    }

    static void viewProducts() {
        if (productCount == 0) {
            System.out.println("No products in stock.");
            return;
        }

        System.out.println("\n--- Product Catalogue ---");
        for (int i = 0; i < productCount; i++) {
            if (products[i][0] != null) {
                System.out.println((i + 1) + ". Name: " + products[i][0] +
                        ", Quantity: " + products[i][1] +
                        ", Price: $" + products[i][2]);
            }
        }
    }

    static void insertProduct(Scanner scanner) {
        if (productCount >= MAX_PRODUCTS) {
            System.out.println("Stock is full!");
            return;
        }

        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Quantity: ");
        String quantity = scanner.nextLine();
        System.out.print("Enter Price: ");
        String price = scanner.nextLine();

        products[productCount][0] = name;
        products[productCount][1] = quantity;
        products[productCount][2] = price;
        insertionHistory[historyCount++] = "Inserted: " + name;
        productCount++;

        System.out.println("Product inserted successfully.");
    }

    static void updateProduct(Scanner scanner) {
        System.out.print("Enter the name of the product to update: ");
        String name = scanner.nextLine();
        boolean found = false;

        for (int i = 0; i < productCount; i++) {
            if (products[i][0] != null && products[i][0].equalsIgnoreCase(name)) {
                System.out.print("Enter new Quantity: ");
                products[i][1] = scanner.nextLine();
                System.out.print("Enter new Price: ");
                products[i][2] = scanner.nextLine();
                System.out.println("Product updated successfully.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Product not found!");
        }
    }

    static void deleteProduct(Scanner scanner) {
        System.out.print("Enter the name of the product to delete: ");
        String name = scanner.nextLine();
        boolean found = false;

        for (int i = 0; i < productCount; i++) {
            if (products[i][0] != null && products[i][0].equalsIgnoreCase(name)) {
                products[i][0] = null;
                products[i][1] = null;
                products[i][2] = null;
                System.out.println("Product deleted successfully.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Product not found!");
        }
    }

    static void viewHistory() {
        if (historyCount == 0) {
            System.out.println("No insertion history.");
            return;
        }

        System.out.println("\n--- Insertion History ---");
        for (int i = 0; i < historyCount; i++) {
            System.out.println((i + 1) + ". " + insertionHistory[i]);
        }
    }
}
