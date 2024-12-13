/**
 * Represents a variable in a C program with its name and initial value.
 */
public class Variable {
    private String name = "";
    private int initialValue = 0;

    /**
     * Constructs a Variable object with default values for name and initial value.
     * 
     * @custom.postcondition The variable is initialized with an empty string for the name and 0 for the initial value.
     */
    public Variable() {}

    /**
     * Constructs a Variable object with the specified name and initial value.
     * 
     * @param name The name of the variable.
     * @param initialValue The initial value of the variable.
     * @custom.postcondition The variable is initialized with the given name and initial value.
     */
    public Variable(String name, int initialValue) {
        this.name = name;
        this.initialValue = initialValue;
    }

    /**
     * Returns the name of the variable.
     * 
     * @return The name of the variable.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the variable.
     * 
     * @param name The new name for the variable.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the initial value of the variable.
     * 
     * @return The initial value of the variable.
     */
    public int getInitialValue() {
        return initialValue;
    }

    /**
     * Sets the initial value of the variable.
     * 
     * @param initialValue The new initial value for the variable.
     */
    public void setInitialValue(int initialValue) {
        this.initialValue = initialValue;
    }

    /**
     * Returns a string representation of the variable in the format "name initialValue".
     * 
     * @return A string representation of the variable.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        int spacesNeeded = 15 - name.length();
        for (int i = 0; i < spacesNeeded; i++) {
            sb.append(" ");
        }
        sb.append(initialValue);
        return sb.toString();
    }
}

