
package lecture5.jpa;

import java.io.Serializable;

public abstract class Editable implements Serializable {

    // Abstract methods that must be implemented by subclasses
    public abstract void initialize();
    public abstract void edit();

    // Input methods for various types of data (left as is, but won't interact directly with DB)
    public String getInputString(String prompt) {
        System.out.print(prompt);
        return "";
    }

    public int getInputInt(String prompt) {
        System.out.print(prompt);
        return 0;
    }

    public double getInputDouble(String prompt) {
        System.out.print(prompt);
        return 0.0;
    }

    public boolean getInputBoolean(String prompt) {
        System.out.print(prompt);
        return false;
    }
}

