/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package lecture5.jpa;

import lecture5.jpa.entities.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public ArrayList<SaleableItem> saleableItems = new ArrayList<>();
    private final CashTill cashTill = CashTill.getInstance();
    private final Scanner scanner = new Scanner(System.in);

    private void addBook() {
        System.out.print("Enter Author ('q' to quit): ");
        String author = scanner.nextLine();
        if (author.equalsIgnoreCase("q")) return;

        System.out.print("Quantity to Order: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.print("ISBN10: ");
        String isbn10 = scanner.nextLine(); // Capture ISBN10 input

        // Create a new Book and add it to the bookstore
        SaleableItem book = new Book(title, author, price, quantity, isbn10) {
            @Override
            public Double getBrand() {
                return 0.0; // Implement getBrand or adjust this logic based on your needs
            }
        };

        // Add the book to the list
        addItem(book);
        System.out.println("Book added successfully: " + book);
    }


    public void addItem(SaleableItem item) {
        saleableItems.add(item);
    }

    public void removeItem(SaleableItem item) {
        saleableItems.remove(item);
    }

    public void editItem(SaleableItem item) {
        if (item instanceof Editable) {
            ((Editable) item).edit();
        } else {
            System.out.println("Item is not editable.");
        }
    }

    public void viewItems() {
        if (saleableItems.isEmpty()) {
            System.out.println("No items available.");
            return;
        }

        System.out.println("Available Items:");
        for (int i = 0; i < saleableItems.size(); i++) {
            System.out.println((i + 1) + ". " + saleableItems.get(i));
        }
    }


    public void sellItem(SaleableItem item) {
        item.sellCopy();
        cashTill.addToTotal(item.getPrice());
        System.out.println("Sold item: " + item);
    }

    public static void main(String[] args) {
        Main store = new Main();
        store.run();
    }

    public void run() {
        boolean running = true;

        while (running) {
            System.out.println("\n-------------------------Main---------------------------------");
            System.out.println("1. Books");
            System.out.println("2. Tickets");
            System.out.println("3. Magazines"); // New option for Magazines
            System.out.println("5. Disc Magazine Operations");
            System.out.println("6. Exit");
            System.out.println("--------------------------------------------------------------");
            System.out.print("Choose an option: ");
            int mainChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (mainChoice) {
                case 1:
                    booksMenu();
                    break;
                case 2:
                    ticketsMenu();
                    break;
                case 3:
                    magazinesMenu(); // Call magazines menu
                    break;
                case 4:
                    displayMenu(); // Call magazines menu
                    break;
                case 5:
                    running = false;
                    System.out.println("Bye..");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void booksMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n-------------------------Books---------------------------------");
            viewItems();
            System.out.println("--------------------------------------------------------------");
            System.out.println("3. Add a Book");
            System.out.println("4. Edit a Book");
            System.out.println("5. Delete a Book");
            System.out.println("6. Sell a Book");
            System.out.println("7. Quit");
            System.out.print("Choose an option: ");
            int bookChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (bookChoice) {
                case 3:
                    addBook();
                    break;
                case 4:
                    editBook();
                    break;
                case 5:
                    deleteBook();
                    break;
                case 6:
                    sellBook();
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    private void magazinesMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n-------------------------Magazines---------------------------------");
            viewItems(); // Show all items, including magazines
            System.out.println("--------------------------------------------------------------");
            System.out.println("1. Add a Magazine");
            System.out.println("2. Edit a Magazine");
            System.out.println("3. Delete a Magazine");
            System.out.println("4. Sell a Magazine");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");
            int magChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (magChoice) {
                case 1:
                    addMagazine();
                    break;
                case 2:
                    editMagazine();
                    break;
                case 3:
                    deleteMagazine();
                    break;
                case 4:
                    sellMagazine();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void ticketsMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n-------------------------Tickets---------------------------------");
            viewItems(); // Show all items, including tickets
            System.out.println("--------------------------------------------------------------");
            System.out.println("1. Add a Ticket");
            System.out.println("2. Edit a Ticket");
            System.out.println("3. Delete a Ticket");
            System.out.println("4. Sell a Ticket");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");
            int ticketChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (ticketChoice) {
                case 1:
                    addTicket();
                    break;
                case 2:
                    editTicket();
                    break;
                case 3:
                    deleteTicket();
                    break;
                case 4:
                    sellTicket();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addTicket() {
        System.out.print("Enter Ticket Description ('q' to quit): ");
        String description = scanner.nextLine();
        if (description.equalsIgnoreCase("q")) return;

        System.out.print("Quantity to Order: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        // Create a new Ticket and add it to the store
        SaleableItem ticket = new Ticket(description, price, quantity) {
            @Override
            public Double getBrand() {
                return 0.0;
            }
        };
        addItem(ticket);
        System.out.println("Ticket added successfully.");
    }

    private void editTicket() {
        viewItems(); // Show items to choose from
        System.out.print("Enter the index of the ticket to edit: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (index < 1 || index > saleableItems.size()) {
            System.out.println("Invalid index.");
            return;
        }

        SaleableItem itemToEdit = saleableItems.get(index - 1);
        if (itemToEdit instanceof Ticket) {
            editItem(itemToEdit);
            System.out.println("Ticket edited successfully.");
        } else {
            System.out.println("Selected item is not a ticket.");
        }
    }

    private void deleteTicket() {
        viewItems(); // Show items to choose from
        System.out.print("Enter the index of the ticket to delete: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (index < 1 || index > saleableItems.size()) {
            System.out.println("Invalid index.");
            return;
        }

        SaleableItem itemToDelete = saleableItems.get(index - 1);
        if (itemToDelete instanceof Ticket) {
            removeItem(itemToDelete);
            System.out.println("Ticket deleted successfully.");
        } else {
            System.out.println("Selected item is not a ticket.");
        }
    }


    private void editBook() {
        viewItems(); // Show items to choose from
        System.out.print("Enter the index of the book to edit: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (index < 1 || index > saleableItems.size()) {
            System.out.println("Invalid index.");
            return;
        }

        SaleableItem itemToEdit = saleableItems.get(index - 1);
        editItem(itemToEdit);
        System.out.println("Book edited successfully.");
    }

    private void deleteBook() {
        viewItems(); // Show items to choose from
        System.out.print("Enter the index of the book to delete: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (index < 1 || index > saleableItems.size()) {
            System.out.println("Invalid index.");
            return;
        }

        SaleableItem itemToDelete = saleableItems.get(index - 1);
        removeItem(itemToDelete);
        System.out.println("Book deleted successfully.");
    }

    private void sellBook() {
        viewItems(); // Show items to choose from
        System.out.print("Enter the index of the book to sell: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (index < 1 || index > saleableItems.size()) {
            System.out.println("Invalid index.");
            return;
        }

        SaleableItem itemToSell = saleableItems.get(index - 1);
        sellItem(itemToSell);
        System.out.println("Book sold successfully.");
    }
    private void addMagazine() {
        System.out.print("Enter Title ('q' to quit): ");
        String title = scanner.nextLine();
        if (title.equalsIgnoreCase("q")) return;

        System.out.print("Quantity to Order: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.print("ISBN13: ");
        String isbn13 = scanner.nextLine(); // Capture ISBN13 input

        // Create a new Magazine and add it to the store
        SaleableItem magazine = new Magazine(title, price, quantity, isbn13) {
            @Override
            public Double getBrand() {
                return 0.0;
            }
        }; // Assuming Magazine implements SaleableItem
        addItem(magazine);
        System.out.println("Magazine added successfully.");
    }

    private void editMagazine() {
        viewItems(); // Show items to choose from
        System.out.print("Enter the index of the magazine to edit: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (index < 1 || index > saleableItems.size()) {
            System.out.println("Invalid index.");
            return;
        }

        SaleableItem itemToEdit = saleableItems.get(index - 1);
        editItem(itemToEdit);
        System.out.println("Magazine edited successfully.");
    }

    private void deleteMagazine() {
        viewItems(); // Show items to choose from
        System.out.print("Enter the index of the magazine to delete: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (index < 1 || index > saleableItems.size()) {
            System.out.println("Invalid index.");
            return;
        }

        SaleableItem itemToDelete = saleableItems.get(index - 1);
        removeItem(itemToDelete);
        System.out.println("Magazine deleted successfully.");
    }

    private void sellMagazine() {
        viewItems(); // Show items to choose from
        System.out.print("Enter the index of the magazine to sell: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (index < 1 || index > saleableItems.size()) {
            System.out.println("Invalid index.");
            return;
        }

        SaleableItem itemToSell = saleableItems.get(index - 1);
        sellItem(itemToSell);
        System.out.println("Magazine sold successfully.");
    }

    private void sellTicket() {
        // Display only tickets from saleableItems
        ArrayList<SaleableItem> ticketItems = new ArrayList<>();
        for (SaleableItem item : saleableItems) {
            if (item instanceof Ticket) {
                ticketItems.add(item);
            }
        }

        if (ticketItems.isEmpty()) {
            System.out.println("No tickets available to sell.");
            return;
        }

        // Display tickets to the user
        for (int i = 0; i < ticketItems.size(); i++) {
            System.out.println((i + 1) + ". " + ticketItems.get(i));
        }

        System.out.print("Enter the index of the ticket to sell: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Validate the index input
        if (index < 1 || index > ticketItems.size()) {
            System.out.println("Invalid index.");
            return;
        }

        // Sell the ticket
        SaleableItem itemToSell = ticketItems.get(index - 1);
        sellItem(itemToSell);
        System.out.println("Ticket sold successfully.");
    }


    private void displayMenu() {
        int choice;
        do {
            System.out.println("1. Add Disc Magazine");
            System.out.println("2. Edit Disc Magazine");
            System.out.println("3. Delete Disc Magazine");
            System.out.println("4. Sell Disc Magazine");
            System.out.println("5. View Disc Magazines");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addDiscMag();
                    break;
                case 2:
                    editDiscMag();
                    break;
                case 3:
                    deleteDiscMag();
                    break;
                case 4:
                    sellDiscMag();
                    break;
                case 5:
                    viewDiscMags();
                    break;
                case 0:
                    System.out.println("Exiting the system.");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 0);
    }

    private void addDiscMag() {
        System.out.print("Enter Disc Magazine Title ('q' to quit): ");
        String title = scanner.nextLine();
        if (title.equalsIgnoreCase("q")) return;

        System.out.print("Quantity to Order: ");
        int copies = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.print("Order Quantity: ");
        int orderQty = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Does it have a disc? (true/false): ");
        boolean hasDisc = scanner.nextBoolean();
        scanner.nextLine(); // Consume newline

        // Create a new DiscMag and add it to the store
        SaleableItem discMag = new DiscMag(title, price, copies, orderQty, hasDisc) {
            @Override
            public Double getBrand() {
                return 0.0; // Just a placeholder, adjust based on your implementation
            }
        }; // Assuming DiscMag implements SaleableItem
        saleableItems.add(discMag);
        System.out.println("Disc Magazine added successfully.");
    }

    private void editDiscMag() {
        viewItems(); // Display items
        System.out.print("Enter the index of the Disc Magazine to edit: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (index < 1 || index > saleableItems.size()) {
            System.out.println("Invalid index.");
            return;
        }

        SaleableItem itemToEdit = saleableItems.get(index - 1);
        if (itemToEdit instanceof DiscMag) {
            DiscMag discMag = (DiscMag) itemToEdit;

            System.out.print("Enter new Title: ");
            String newTitle = scanner.nextLine();
            discMag.setTitle(newTitle);

            System.out.print("Enter new Price: ");
            double newPrice = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            discMag.setPrice(newPrice);

            System.out.print("Enter new Quantity: ");
            int newCopies = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            discMag.setCopies(newCopies);

            System.out.print("Enter new Order Quantity: ");
            int newOrderQty = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            discMag.setOrderQty(newOrderQty);

            System.out.print("Does it have a disc? (true/false): ");
            boolean hasDisc = scanner.nextBoolean();
            scanner.nextLine(); // Consume newline
            discMag.setHasDisc(hasDisc);

            System.out.println("Disc Magazine edited successfully.");
        } else {
            System.out.println("Selected item is not a Disc Magazine.");
        }
    }
    private void deleteDiscMag() {
        viewItems(); // Display items
        System.out.print("Enter the index of the Disc Magazine to delete: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (index < 1 || index > saleableItems.size()) {
            System.out.println("Invalid index.");
            return;
        }

        SaleableItem itemToDelete = saleableItems.get(index - 1);
        if (itemToDelete instanceof DiscMag) {
            saleableItems.remove(itemToDelete);
            System.out.println("Disc Magazine deleted successfully.");
        } else {
            System.out.println("Selected item is not a Disc Magazine.");
        }
    }

    private void sellDiscMag() {
        viewItems(); // Display items
        System.out.print("Enter the index of the Disc Magazine to sell: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (index < 1 || index > saleableItems.size()) {
            System.out.println("Invalid index.");
            return;
        }

        SaleableItem itemToSell = saleableItems.get(index - 1);
        if (itemToSell instanceof DiscMag) {
            // Assuming you have a sell logic, adjust based on your app's sale process
            DiscMag discMag = (DiscMag) itemToSell;
            if (discMag.getCopies() > 0) {
                discMag.setCopies(discMag.getCopies() - 1); // Reduce the number of copies
                System.out.println("Disc Magazine sold successfully.");
            } else {
                System.out.println("No copies available to sell.");
            }
        } else {
            System.out.println("Selected item is not a Disc Magazine.");
        }
    }
    private void viewDiscMags() {
        System.out.println("Disc Magazines:");
        for (int i = 0; i < saleableItems.size(); i++) {
            SaleableItem item = saleableItems.get(i);
            if (item instanceof DiscMag) {
                System.out.println((i + 1) + ". " + item); // Display DiscMag items
            }
        }
    }




}