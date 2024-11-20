/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package lecture5.jpa;

import lecture5.jpa.entities.*;
import lecture5.jpa.controllers.BookJpaController;

import java.util.ArrayList;
import java.util.Scanner;
import lecture5.jpa.controllers.*;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("DEFAULT_PU");
    private final BookJpaController bookController = new BookJpaController(emf);
    private final MagazineJpaController magazineController = new MagazineJpaController(emf);
    private final TicketJpaController ticketController = new TicketJpaController(emf);
    private final DiscMagJpaController discMagController = new DiscMagJpaController(emf);
    private CashTillJpaController cashTillController = new CashTillJpaController(emf);


    public ArrayList<SaleableItem> saleableItems = new ArrayList<>();
    private CashTill cashTill = CashTill.getInstance();
    private final Scanner scanner = new Scanner(System.in);


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



    public void saveItemToDatabase(SaleableItem item) {
        if (item instanceof Book) {
            bookController.create((Book) item);
        } else if (item instanceof Magazine) {
            magazineController.create((Magazine) item);
        } else if (item instanceof Ticket) {
            ticketController.create((Ticket) item);
        } else if (item instanceof DiscMag) {
            discMagController.create((DiscMag) item);
        }
    }

    public void deleteItemFromDatabase(SaleableItem item) {
        try {
            if (item instanceof Book) {
                bookController.destroy(((Book) item).getId());
            } else if (item instanceof Magazine) {
                magazineController.destroy(((Magazine) item).getId());
            } else if (item instanceof Ticket) {
                ticketController.destroy(((Ticket) item).getId());
            } else if (item instanceof DiscMag) {
                discMagController.destroy(((DiscMag) item).getId());
            }
        } catch (Exception e) {
            System.out.println("Error deleting item: " + e.getMessage());
        }
    }

    public void sellItem(SaleableItem item) {
        if (item.sellCopy() > 0) {
            cashTill.addToTotal(item.getPrice());
            System.out.println("Sold item: " + item);
        } else {
            System.out.println("Item out of stock.");
        }
    }

    public App() {
        // Initialize CashTill from database or create a new one
        CashTill existingCashTill = cashTillController.findCashTill(1L); // Assuming a single CashTill with ID 1
        if (existingCashTill != null) {
            cashTill = existingCashTill;
        } else {
            cashTill = new CashTill();
            cashTillController.create(cashTill);
        }

        // Add shutdown hook to save CashTill state when application exits
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                cashTillController.edit(cashTill);
                System.out.println("Saved CashTill state to the database.");
            } catch (Exception e) {
                System.err.println("Error saving CashTill: " + e.getMessage());
            }
        }));
    }


    public void run() throws Exception {
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

    private void booksMenu() throws Exception {
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
    private void magazinesMenu() throws Exception {
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

    private void ticketsMenu() throws Exception {
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
    private void displayMenu() throws Exception {
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
        String isbn10 = scanner.nextLine();

        Book book = new Book(title, author, price, quantity, isbn10) {
            @Override
            public Double getBrand() {
                return 0.0;
            }
        };
        addItem(book);
        saveItemToDatabase(book);
        System.out.println("Book added successfully.");
    }

    private void editBook() throws Exception {
        viewItems();
        System.out.print("Enter the index of the book to edit: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (index < 1 || index > saleableItems.size()) {
            System.out.println("Invalid index.");
            return;
        }

        SaleableItem item = saleableItems.get(index - 1);
        if (item instanceof Book) {
            Book book = (Book) item;
            System.out.print("Enter new Title: ");
            book.setTitle(scanner.nextLine());

            System.out.print("Enter new Price: ");
            book.setPrice(scanner.nextDouble());
            scanner.nextLine(); // Consume newline

            System.out.print("Enter new Quantity: ");
            book.setCopies(scanner.nextInt());
            scanner.nextLine(); // Consume newline

            System.out.print("Enter new ISBN10: ");
            book.setISBN10(scanner.nextLine());

            bookController.edit(book);
            System.out.println("Book edited successfully.");
        } else {
            System.out.println("Selected item is not a book.");
        }
    }

    private void deleteBook() {
        viewItems();
        System.out.print("Enter the index of the book to delete: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (index < 1 || index > saleableItems.size()) {
            System.out.println("Invalid index.");
            return;
        }

        SaleableItem item = saleableItems.get(index - 1);
        if (item instanceof Book) {
            deleteItemFromDatabase(item);
            removeItem(item);
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("Selected item is not a book.");
        }
    }

    private void sellBook() {
        viewItems();
        System.out.print("Enter the index of the book to sell: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (index < 1 || index > saleableItems.size()) {
            System.out.println("Invalid index.");
            return;
        }

        SaleableItem item = saleableItems.get(index - 1);
        if (item instanceof Book) {
            sellItem(item);
        } else {
            System.out.println("Selected item is not a book.");
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

        Ticket ticket = new Ticket(description, price, quantity) {
            @Override
            public Double getBrand() {
                return 0.0; // Placeholder implementation
            }
        };

        addItem(ticket);
        saveItemToDatabase(ticket);
        System.out.println("Ticket added successfully.");
    }

    private void editTicket() throws Exception {
        viewItems();
        System.out.print("Enter the index of the ticket to edit: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (index < 1 || index > saleableItems.size()) {
            System.out.println("Invalid index.");
            return;
        }

        SaleableItem item = saleableItems.get(index - 1);
        if (item instanceof Ticket) {
            Ticket ticket = (Ticket) item;

            System.out.print("Enter new Description: ");
            ticket.setDescription(scanner.nextLine());

            System.out.print("Enter new Price: ");
            ticket.setTicketPrice(scanner.nextDouble());
            scanner.nextLine(); // Consume newline

            System.out.print("Enter new Quantity: ");
            ticket.setQuantity(scanner.nextInt());
            scanner.nextLine(); // Consume newline

            ticketController.edit(ticket);
            System.out.println("Ticket edited successfully.");
        } else {
            System.out.println("Selected item is not a ticket.");
        }
    }

    private void deleteTicket() {
        viewItems();
        System.out.print("Enter the index of the ticket to delete: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (index < 1 || index > saleableItems.size()) {
            System.out.println("Invalid index.");
            return;
        }

        SaleableItem item = saleableItems.get(index - 1);
        if (item instanceof Ticket) {
            deleteItemFromDatabase(item);
            removeItem(item);
            System.out.println("Ticket deleted successfully.");
        } else {
            System.out.println("Selected item is not a ticket.");
        }
    }

    private void sellTicket() {
        viewItems();
        System.out.print("Enter the index of the ticket to sell: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (index < 1 || index > saleableItems.size()) {
            System.out.println("Invalid index.");
            return;
        }

        SaleableItem item = saleableItems.get(index - 1);
        if (item instanceof Ticket) {
            sellItem(item);
        } else {
            System.out.println("Selected item is not a ticket.");
        }
    }


    private void addDiscMag() {
        System.out.print("Enter Title ('q' to quit): ");
        String title = scanner.nextLine();
        if (title.equalsIgnoreCase("q")) return;

        System.out.print("Quantity to Order: ");
        int quantity = scanner.nextInt();
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

        DiscMag discMag = new DiscMag(title, price, quantity, orderQty, hasDisc) {
            @Override
            public Double getBrand() {
                return 0.0; // Placeholder implementation
            }
        };

        addItem(discMag);
        saveItemToDatabase(discMag);
        System.out.println("Disc Magazine added successfully.");
    }

    private void editDiscMag() throws Exception {
        viewItems();
        System.out.print("Enter the index of the disc magazine to edit: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (index < 1 || index > saleableItems.size()) {
            System.out.println("Invalid index.");
            return;
        }

        SaleableItem item = saleableItems.get(index - 1);
        if (item instanceof DiscMag) {
            DiscMag discMag = (DiscMag) item;

            System.out.print("Enter new Title: ");
            discMag.setTitle(scanner.nextLine());

            System.out.print("Enter new Price: ");
            discMag.setPrice(scanner.nextDouble());
            scanner.nextLine(); // Consume newline

            System.out.print("Enter new Quantity: ");
            discMag.setCopies(scanner.nextInt());
            scanner.nextLine(); // Consume newline

            System.out.print("Enter new Order Quantity: ");
            discMag.setOrderQty(scanner.nextInt());
            scanner.nextLine(); // Consume newline

            System.out.print("Does it have a disc? (true/false): ");
            discMag.setHasDisc(scanner.nextBoolean());
            scanner.nextLine(); // Consume newline

            discMagController.edit(discMag);
            System.out.println("Disc Magazine edited successfully.");
        } else {
            System.out.println("Selected item is not a disc magazine.");
        }
    }

    private void deleteDiscMag() {
        viewItems();
        System.out.print("Enter the index of the disc magazine to delete: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (index < 1 || index > saleableItems.size()) {
            System.out.println("Invalid index.");
            return;
        }

        SaleableItem item = saleableItems.get(index - 1);
        if (item instanceof DiscMag) {
            deleteItemFromDatabase(item);
            removeItem(item);
            System.out.println("Disc Magazine deleted successfully.");
        } else {
            System.out.println("Selected item is not a disc magazine.");
        }
    }

    private void sellDiscMag() {
        viewItems();
        System.out.print("Enter the index of the disc magazine to sell: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (index < 1 || index > saleableItems.size()) {
            System.out.println("Invalid index.");
            return;
        }

        SaleableItem item = saleableItems.get(index - 1);
        if (item instanceof DiscMag) {
            sellItem(item);
        } else {
            System.out.println("Selected item is not a disc magazine.");
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
        String isbn13 = scanner.nextLine();

        Magazine magazine = new Magazine(title, price, quantity) {
            @Override
            public Double getBrand() {
                return 0.0; // Adjust based on your application
            }
        };
        magazine.setIsbn13(isbn13);

        addItem(magazine);
        saveItemToDatabase(magazine);
        System.out.println("Magazine added successfully.");
    }

    private void editMagazine() throws Exception {
        viewItems();
        System.out.print("Enter the index of the magazine to edit: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (index < 1 || index > saleableItems.size()) {
            System.out.println("Invalid index.");
            return;
        }

        SaleableItem item = saleableItems.get(index - 1);
        if (item instanceof Magazine) {
            Magazine magazine = (Magazine) item;

            System.out.print("Enter new Title: ");
            magazine.setTitle(scanner.nextLine());

            System.out.print("Enter new Price: ");
            magazine.setPrice(scanner.nextDouble());
            scanner.nextLine(); // Consume newline

            System.out.print("Enter new Quantity: ");
            magazine.setCopies(scanner.nextInt());
            scanner.nextLine(); // Consume newline

            System.out.print("Enter new ISBN13: ");
            magazine.setIsbn13(scanner.nextLine());

            magazineController.edit(magazine);
            System.out.println("Magazine edited successfully.");
        } else {
            System.out.println("Selected item is not a magazine.");
        }
    }

    private void deleteMagazine() {
        viewItems();
        System.out.print("Enter the index of the magazine to delete: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (index < 1 || index > saleableItems.size()) {
            System.out.println("Invalid index.");
            return;
        }

        SaleableItem item = saleableItems.get(index - 1);
        if (item instanceof Magazine) {
            deleteItemFromDatabase(item);
            removeItem(item);
            System.out.println("Magazine deleted successfully.");
        } else {
            System.out.println("Selected item is not a magazine.");
        }
    }

    private void sellMagazine() {
        viewItems();
        System.out.print("Enter the index of the magazine to sell: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (index < 1 || index > saleableItems.size()) {
            System.out.println("Invalid index.");
            return;
        }

        SaleableItem item = saleableItems.get(index - 1);
        if (item instanceof Magazine) {
            sellItem(item);
        } else {
            System.out.println("Selected item is not a magazine.");
        }
    }



}