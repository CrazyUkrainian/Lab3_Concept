
package lecture5.jpa.entities;
import lecture5.jpa.controllers.BookJpaController;
import lecture5.jpa.controllers.DiscMagJpaController;
import lecture5.jpa.controllers.MagazineJpaController;
import lecture5.jpa.controllers.TicketJpaController;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public abstract class Editable implements Serializable {
    protected Scanner input = new Scanner(System.in);

    // Get input methods for various data types
    public String getInputString(String prompt) {
        System.out.print(prompt);  // Display the prompt to the user
        return input.nextLine();   // Return the user's input as a String
    }

    public Date getInputDate(String prompt) {
        System.out.print(prompt);
        String dateString = input.nextLine(); // Read user input
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dateString); // Attempt to parse the date
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-mm-dd.");
            return getInputDate(prompt); // Re-prompt for input if parsing fails
        }
    }

    public int getInputInt(String prompt) {
        System.out.print(prompt);  // Display the prompt to the user
        while (!input.hasNextInt()) {  // Check if the input is a valid integer
            System.out.println("Invalid input. Please enter a valid integer.");
            input.next();  // Clear the invalid input
            System.out.print(prompt);  // Re-prompt
        }
        int result = input.nextInt(); // Capture the valid integer
        input.nextLine(); // Consume the leftover newline
        return result;  // Return the user's input as an integer
    }

    public double getInputDouble(String prompt) {
        System.out.print(prompt);  // Display the prompt to the user
        while (!input.hasNextDouble()) {  // Check if the input is a valid double
            System.out.println("Invalid input. Please enter a valid decimal number.");
            input.next();  // Clear the invalid input
            System.out.print(prompt);  // Re-prompt
        }
        double result = input.nextDouble(); // Capture the valid double
        input.nextLine(); // Consume the leftover newline
        return result;  // Return the user's input as a double
    }

    public boolean getInputBoolean(String prompt) {
        System.out.print(prompt);  // Display the prompt to the user
        while (!input.hasNextBoolean()) {  // Check if the input is a valid boolean
            System.out.println("Invalid input. Please enter true or false.");
            input.next();  // Clear the invalid input
            System.out.print(prompt);  // Re-prompt
        }
        return input.nextBoolean();  // Return the user's input as a boolean
    }

    // Abstract methods
    public abstract void edit();
    public abstract void initialize();
    public void saveChangesToDatabase(Object controller) {
        try {
            if (controller instanceof BookJpaController && this instanceof Book) {
                ((BookJpaController) controller).edit((Book) this);
            } else if (controller instanceof MagazineJpaController && this instanceof Magazine) {
                ((MagazineJpaController) controller).edit((Magazine) this);
            } else if (controller instanceof TicketJpaController && this instanceof Ticket) {
                ((TicketJpaController) controller).edit((Ticket) this);
            } else if (controller instanceof DiscMagJpaController && this instanceof DiscMag) {
                ((DiscMagJpaController) controller).edit((DiscMag) this);
            } else {
                throw new UnsupportedOperationException("Controller not supported for this entity type.");
            }
            System.out.println("Changes saved to database successfully.");
        } catch (Exception e) {
            System.err.println("Error saving changes to database: " + e.getMessage());
        }
    }
}
