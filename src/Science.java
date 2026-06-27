import java.util.ArrayList;

/**
 * The Science class represents a science-related question, extending the Question class.
 * It includes functionality for setting up the question mode and scoring.
 */
public class Science extends Question {

    /**
     * An ArrayList containing easy options associated with the science question.
     */
    ArrayList<Option> arrayOptionEasy = new ArrayList<Option>();

    /**
     * Default constructor for the Science class.
     * Initializes the Science question with default values.
     */
    public Science() {
        super();
    }

    /**
     * Parameterized constructor for the Science class.
     *
     * @param arrayOption      The list of options for the science question.
     * @param question         The text of the science question.
     * @param typeQuestion     The type of question.
     * @param arrayOptionEasy  The list of easy options associated with the science question.
     */
    public Science(ArrayList<Option> arrayOption, String question, String typeQuestion, ArrayList<Option> arrayOptionEasy) {
        super(arrayOption, question, typeQuestion);
        this.arrayOptionEasy = arrayOptionEasy;
    }

    /**
     * Calculates the score for the science question.
     *
     * @return The calculated score for the science question.
     */
    @Override
    protected int scoreCalculator() {
        int questionScore = 5 + 5;
        return questionScore;
    }

    /**
     * Sets up the mode for the science question based on the provided value.
     *
     * @param value The value used for question setup.
     * @return The updated Science question.
     */
    @Override
    protected Question modeSetUp(int value) {
        if (value < 2) {
            for (int i = 0; i < this.arrayOption.size(); i++) {
                this.arrayOption.set(i, this.arrayOptionEasy.get(i));
            }
            this.correctOption = this.arrayOption.get(0);
        } else
            this.correctOption = this.arrayOption.get(0);
        return this;
    }
}
