import java.util.ArrayList;

/**
 * The Art class represents an art-related question, extending the Question class.
 * It includes functionality for setting up the question mode and scoring.
 */
public class Art extends Question {

    /**
     * Default constructor for the Art class.
     * Initializes the Art question with default values.
     */
    public Art() {
        super();
    }

    /**
     * Parameterized constructor for the Art class.
     *
     * @param arrayOption  The list of options for the art question.
     * @param question     The text of the art question.
     * @param typeQuestion The type of question.
     */
    public Art(ArrayList<Option> arrayOption, String question, String typeQuestion) {
        super(arrayOption, question, typeQuestion);
    }

    /**
     * Calculates the score for the art question.
     *
     * @return The calculated score for the art question.
     */
    @Override
    protected int scoreCalculator() {
        int questionScore = 5 * 10;
        return questionScore;
    }

    /**
     * Sets up the mode for the art question based on the provided value.
     *
     * @param value The value used for question setup.
     * @return The updated Art question.
     */

    @Override
    protected Question modeSetUp(int value) {
        int maxSize = this.arrayOption.size();
        if (value < 2) {
            for (int i = 0; i < maxSize - 3; i++) {
                this.arrayOption.remove(maxSize - 1 - i);
            }
            this.correctOption = this.arrayOption.get(0);
        } else this.correctOption = this.arrayOption.get(0);
        return this;
    }
}
