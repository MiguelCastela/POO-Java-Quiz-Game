import java.util.ArrayList;

/**
 * The Swimming class represents a swimming-related question, extending the Question class.
 * It includes functionality for setting up the question mode and scoring.
 */
public class Swimming extends Question {

    /**
     * Default constructor for the Swimming class.
     * Initializes the Swimming question with default values.
     */
    public Swimming() {
        super();
    }

    /**
     * Parameterized constructor for the Swimming class.
     *
     * @param arrayOption  The list of options for the swimming question.
     * @param question     The text of the swimming question.
     * @param typeQuestion The type of question.
     */
    
    public Swimming(ArrayList<Option> arrayOption, String question, String typeQuestion) {
        super(arrayOption, question, typeQuestion);
    }

    /**
     * Calculates the score for the swimming question.
     *
     * @return The calculated score for the swimming question.
     */
    @Override
    protected int scoreCalculator() {
        int questionScore = 5 + 10 + 3;
        return questionScore;
    }

    /**
     * Sets up the mode for the swimming question based on the provided value.
     *
     * @param value The value used for question setup.
     * @return The updated Swimming question.
     */
    @Override
    protected Question modeSetUp(int value) {
        this.correctOption = this.arrayOption.get(0);
        return this;
    }
}
