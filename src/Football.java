import java.util.ArrayList;

/**
 * The Football class represents a football-related question, extending the Question class.
 * It includes functionality for setting up the question mode, scoring, and managing football players.
 */
public class Football extends Question {

    /**
     * Default constructor for the Football class.
     * Initializes the array of football players.
     */
    public Football() {
        super();
    }

    /**
     * An ArrayList containing football players associated with the question.
     */
    ArrayList<FootballPlayer> arrayPlayers = new ArrayList<FootballPlayer>();

    /**
     * Parameterized constructor for the Football class.
     *
     * @param arrayOption    The list of options for the football question.
     * @param question       The text of the football question.
     * @param arrayPlayers   The list of football players associated with the question.
     * @param typeQuestion   The type of question.
     */

    public Football(ArrayList<Option> arrayOption, String question, ArrayList<FootballPlayer> arrayPlayers, String typeQuestion) {
        super(arrayOption, question, typeQuestion);
        this.arrayPlayers = arrayPlayers;
    }

    /**
     * Calculates the score for the football question.
     *
     * @return The calculated score for the football question.
     */
    @Override
    protected int scoreCalculator() {
        int questionScore = 5 + 3 + 1;
        return questionScore;
    }

    /**
     * Sets up the mode for the football question based on the provided value.
     *
     * @param value The value used for question setup.
     * @return The updated Football question.
     */
    @Override
    protected Question modeSetUp(int value) {

        if (value < 2) {
            for (int i = 0; i < this.arrayOption.size(); i++) {
                arrayOption.get(i).opString = arrayPlayers.get(i).playerName;
            }

            this.correctOption = this.arrayOption.get(0);
        } else {
            this.correctOption = this.arrayOption.get(0);
            this.question += " Selecione o número da camisola desse jogador:";
        }
        return this;
    }
}
