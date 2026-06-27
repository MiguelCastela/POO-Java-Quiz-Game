import java.io.Serializable;
import java.util.ArrayList;

/**
 * The Question class is an abstract class representing a generic question in a trivia game.
 * It implements the Serializable interface to support serialization.
 */
abstract public class Question implements Serializable {

    /**
     * An ArrayList containing options associated with the question.
     */
    ArrayList<Option> arrayOption = new ArrayList<Option>();

    /**
     * The text of the question.
     */
    protected String question;

    /**
     * The type of the question.
     */
    protected String typeQuestion;

    /**
     * The correct option for the question.
     */
    protected Option correctOption = new Option();

    /**
     * Parameterized constructor for the Question class.
     *
     * @param arrayOption  The list of options for the question.
     * @param question     The text of the question.
     * @param typeQuestion The type of the question.
     */
    public Question(ArrayList<Option> arrayOption, String question, String typeQuestion) {
        this.typeQuestion = typeQuestion;
        this.arrayOption = new ArrayList<>(arrayOption);
        this.question = question;
    }

    /**
     * Default constructor for the Question class.
     */
    public Question() {
    }

    /**
     * Retrieves the text of the question.
     *
     * @return The text of the question.
     */
    protected String getQuestion() {
        return question;
    }

    /**
     * Retrieves the list of options for the question.
     *
     * @return The ArrayList of options associated with the question.
     */
    public ArrayList<Option> getOptions() {
        return arrayOption;
    }

    /**
     * Abstract method to calculate the score for the question.
     *
     * @return The calculated score for the question.
     */
    abstract protected int scoreCalculator();

    /**
     * Abstract method to set up the mode for the question based on the provided value.
     *
     * @param value The value used for question setup.
     * @return The updated Question.
     */
    abstract protected Question modeSetUp(int value);
}
