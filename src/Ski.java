import java.util.ArrayList;

/**
 * The Ski class represents a skiing-related question, extending the Question class.
 * It includes functionality for setting up the question mode and scoring.
 */
public class Ski extends Question {

    /**
     * Default constructor for the Ski class.
     * Initializes the Ski question with default values.
     */
    public Ski() {
        super();
    }

    /**
     * Parameterized constructor for the Ski class.
     *
     * @param arrayOption  The list of options for the skiing question.
     * @param question     The text of the skiing question.
     * @param typeQuestion The type of question.
     */
    public Ski(ArrayList<Option> arrayOption, String question, String typeQuestion) {
        super(arrayOption, question, typeQuestion);
    }

    /**
     * Calculates the score for the skiing question.
     *
     * @return The calculated score for the skiing question.
     */
    @Override
    protected int scoreCalculator() {
        int questionScore = (5 + 3) * 2;
        return questionScore;
    }

    /**
     * Sets up the mode for the skiing question based on the provided value.
     *
     * @param value The value used for question setup.
     * @return The updated Ski question.
     */
    @Override
    protected Question modeSetUp(int value) {
        this.correctOption = this.arrayOption.get(0);
        return this;
    }
}
