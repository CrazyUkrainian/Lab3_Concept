/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package lab3.q2.jpa;

import lab3.q2.jpa.entities.*;
import lab3.q2.jpa.controllers.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class Bookstore {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookstore");
    private final BookJpaController bookJpaController = new BookJpaController(emf);
    private final PencilJpaController pencilJpaController = new PencilJpaController(emf);
    private final TicketJpaController ticketJpaController = new TicketJpaController(emf);
    private final MagazineJpaController magazineJpaController = new MagazineJpaController(emf);

    private static Scanner scanner = new Scanner(System.in);

    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("\nMain");
            System.out.println("1 Manage Books");
            System.out.println("2 Manage Tickets");
            System.out.println("3 Manage Magazines");
            System.out.println("4 Manage Pencils");
            System.out.println("5 Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> manageBooks();
                case 2 -> manageTickets();
                case 3 -> manageMagazines();
                case 4 -> managePencils();
                case 5 -> {
                    running = false;
                    System.out.println("Finish");
                }
                default -> System.out.println("Error");
            }
        }
    }

    private void manageBooks() {
        boolean running = true;
        while (running) {
            System.out.println("\nBooks");
            List<Book> books = bookJpaController.findBookEntities();
            for (Book book : books) {
                System.out.println(book);
            }
            System.out.println("3 Add a Book");
            System.out.println("4 Edit a Book");
            System.out.println("5 Delete a Book");
            System.out.println("6 Sell a Book");
            System.out.println("7 Quit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 3 -> addBook();
                case 4 -> editBook();
                case 5 -> deleteBook();
                case 6 -> sellBook();
                case 7 -> running = false;
                default -> System.out.println("Error");
            }
        }
    }

    public void addBook() {
        Book book = new Book();
        book.initialize();
        try {
            bookJpaController.create(book);
            System.out.println(" added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding book: " + e.getMessage());
        }
    }

    public void editBook() {
        System.out.print("Enter book ID to edit: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        try {
            Book book = bookJpaController.findBook(id);
            if (book != null) {
                book.initialize();
                bookJpaController.edit(book);
                System.out.println("updated successfully.");
            } else {
                System.out.println("Didn't found your book.");
            }
        } catch (Exception e) {
            System.out.println("Error editing book: " + e.getMessage());
        }
    }

    public void deleteBook() {
        System.out.print("Enter book ID for delete: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        try {
            bookJpaController.destroy(id);
            System.out.println(" deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error deleting book: " + e.getMessage());
        }
    }

    public void sellBook() {
        System.out.print("Enter book ID to sell: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline
        try {
            Book book = bookJpaController.findBook(id);
            if (book != null) {
                book.sellCopy();
                bookJpaController.edit(book); // Update book after sale
            } else {
                System.out.println("Book not found.");
            }
        } catch (Exception e) {
            System.out.println("Error selling book: " + e.getMessage());
        }
    }

    public void managePencils() {
        boolean running = true;
        while (running) {
            System.out.println("\n Pencils");

            System.out.println("3 Add a Pencil");
            System.out.println("4 Edit a Pencil");
            System.out.println("5 Delete a Pencil");
            System.out.println("6 Sell a Pencil");
            System.out.println("7 Quit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 3 -> addPencil();
                case 4 -> editPencil();
                case 5 -> deletePencil();
                case 6 -> sellPencil();
                case 7 -> running = false;
                default -> System.out.println("Error");
            }
        }
    }

    public void addPencil() {
        Pencil pencil = new Pencil();
        pencil.initialize();
        try {
            pencilJpaController.create(pencil);
            System.out.println("Pencil added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding pencil: " + e.getMessage());
        }
    }

    public void editPencil() {
        System.out.print("Enter pencil ID to edit: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        try {
            Pencil pencil = pencilJpaController.findPencil(id);
            if (pencil != null) {
                pencil.initialize();
                pencilJpaController.edit(pencil);
                System.out.println("Pencil updated successfully.");
            } else {
                System.out.println("Pencil not found.");
            }
        } catch (Exception e) {
            System.out.println("Error editing pencil: " + e.getMessage());
        }
    }

    public void deletePencil() {
        System.out.print("Enter pencil ID to delete: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        try {
            pencilJpaController.destroy(id);
            System.out.println("Pencil deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error deleting pencil: " + e.getMessage());
        }
    }

    public void sellPencil() {
        System.out.print("Enter pencil ID to sell: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        try {
            Pencil pencil = pencilJpaController.findPencil(id);
            if (pencil != null) {
                pencil.sellCopy();
                pencilJpaController.edit(pencil);
                System.out.println("sold successfully.");
            } else {
                System.out.println("Didnt found your pencil.");
            }
        } catch (Exception e) {
            System.out.println("Error selling pencil: " + e.getMessage());
        }
    }

    public void manageTickets() {
        boolean running = true;
        while (running) {
            System.out.println("\n-------------------------Tickets---------------------------------");
            List<Ticket> tickets = ticketJpaController.findTicketEntities();
            for (Ticket ticket : tickets) {
                System.out.println(ticket);
            }
            System.out.println("3 Add a Ticket");
            System.out.println("4 Edit a Ticket");
            System.out.println("5 Delete a Ticket");
            System.out.println("6 Sell a Ticket");
            System.out.println("7 Quit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 3 -> addTicket();
                case 4 -> editTicket();
                case 5 -> deleteTicket();
                case 6 -> sellTicket();
                case 7 -> running = false;
                default -> System.out.println("Error");
            }
        }
    }

    public void addTicket() {
        Ticket ticket = new Ticket();
        ticket.initialize();
        try {
            ticketJpaController.create(ticket);
            System.out.println("Ticket added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding ticket: " + e.getMessage());
        }
    }

    public void editTicket() {
        System.out.print("Enter ticket ID to edit: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        try {
            Ticket ticket = ticketJpaController.findTicket(id);
            if (ticket != null) {
                ticket.initialize();
                ticketJpaController.edit(ticket);
                System.out.println("Ticket updated successfully.");
            } else {
                System.out.println("Ticket not found.");
            }
        } catch (Exception e) {
            System.out.println("Error editing ticket: " + e.getMessage());
        }
    }

    public void deleteTicket() {
        System.out.print("Enter ticket ID to delete: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        try {
            ticketJpaController.destroy(id);
            System.out.println("Ticket deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error deleting ticket: " + e.getMessage());
        }
    }

    public void sellTicket() {
        System.out.print("Enter ticket ID to sell: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        try {
            Ticket ticket = ticketJpaController.findTicket(id);
            if (ticket != null) {
                ticket.sellCopy();
                ticketJpaController.edit(ticket);
                System.out.println("sold successfully.");
            } else {
                System.out.println("Didn't found ticket");
            }
        } catch (Exception e) {
            System.out.println("Error selling ticket: " + e.getMessage());
        }
    }

    public void manageMagazines() {
        boolean running = true;
        while (running) {
            System.out.println("\n-------------------------Magazines---------------------------------");
            List<Magazine> magazines = magazineJpaController.findMagazineEntities();
            for (Magazine magazine : magazines) {
                System.out.println(magazine);
            }
            System.out.println("3 Add a Magazine");
            System.out.println("4 Edit a Magazine");
            System.out.println("5 Delete a Magazine");
            System.out.println("6 Sell a Magazine");
            System.out.println("7 Quit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 3 -> addMagazine();
                case 4 -> editMagazine();
                case 5 -> deleteMagazine();
                case 6 -> sellMagazine();
                case 7 -> running = false;
                default -> System.out.println("Error");
            }
        }
    }

    public void addMagazine() {
        Magazine magazine = new Magazine();
        magazine.initialize();
        try {
            magazineJpaController.create(magazine);
            System.out.println(" added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding magazine: " + e.getMessage());
        }
    }

    public void editMagazine() {
        System.out.print("Enter magazine ID to edit: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        try {
            Magazine magazine = magazineJpaController.findMagazine(id);
            if (magazine != null) {
                magazine.initialize();
                magazineJpaController.edit(magazine);
                System.out.println("updated successfully.");
            } else {
                System.out.println("Didn't found magazine.");
            }
        } catch (Exception e) {
            System.out.println("Error editing magazine: " + e.getMessage());
        }
    }

    public void deleteMagazine() {
        System.out.print("Enter magazine ID to delete: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        try {
            magazineJpaController.destroy(id);
            System.out.println("deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error deleting magazine: " + e.getMessage());
        }
    }

    public void sellMagazine() {
        System.out.print("Enter magazine ID to sell: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        try {
            Magazine magazine = magazineJpaController.findMagazine(id);
            if (magazine != null) {
                magazine.sellCopy();
                magazineJpaController.edit(magazine);
                System.out.println("sold successfully.");
            } else {
                System.out.println("Didnt fount your magazine.");
            }
        } catch (Exception e) {
            System.out.println("Error selling magazine: " + e.getMessage());
        }
    }


}