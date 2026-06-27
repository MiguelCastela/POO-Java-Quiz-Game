import java.io.Serializable;
import java.util.ArrayList;

/**
 * The Person class represents a player in a trivia game.
 * It implements the Serializable interface to support serialization.
 */
public class Person implements Serializable {

    /**
     * An ArrayList containing the list of questions associated with the person.
     */
    protected ArrayList<Question> questionList = new ArrayList<Question>();

    /**
     * An array representing the answers provided by the person for each question.
     */
    protected boolean[] answers;

    /**
     * The name of the person.
     */
    protected String name;

    /**
     * Parameterized constructor for the Person class.
     *
     * @param questionList The list of questions associated with the person.
     * @param answers      The array representing the answers provided by the person.
     * @param name         The name of the person.
     */
    public Person(ArrayList<Question> questionList, boolean[] answers, String name) {
        this.questionList = questionList;
        this.answers = answers;
        this.name = name;
    }

    /**
     * Default constructor for the Person class.
     */
    public Person() {
    }

    /**
     * Retrieves the name of the person.
     *
     * @return The name of the person.
     */
    
    public String getName() {
        return name;
    }
}
