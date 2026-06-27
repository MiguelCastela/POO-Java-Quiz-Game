import java.io.Serializable;

/**
 * The Option class represents a choice or option in a trivia game question.
 * It implements the Serializable interface to support serialization.
 */
public class Option implements Serializable {

    /**
     * The text associated with the option.
     */
    protected String opString;

    /**
     * Parameterized constructor for the Option class.
     *
     * @param opString The text associated with the option.
     */
    public Option(String opString) {
        this.opString = opString;
    }

    /**
     * Default constructor for the Option class.
     */
    public Option() {
    }

    /**
     * Retrieves the text associated with the option.
     *
     * @return The text of the option.
     */
    public String getOpString() {
        return opString;
    }
}
