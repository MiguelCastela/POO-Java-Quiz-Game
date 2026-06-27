import java.util.*;

/**
 * This class represents a trivia game with questions and scoring.
 */
public class POOaoTrivia {


    /**
     * An array representing the types of questions already used
     */
    static String[] questionTypeArray = new String[5];

    /**
     * An ArrayList containing the game questions.
     */
    static ArrayList<Question> gameQuestions = new ArrayList<Question>();

    /**
     * An array indicating the results of each question.
     */
    static boolean[] questionResult = new boolean[5];

    /**
     * A integer value representing the number of the current question.
     */
    static int value = 0;

    /**
     * Adds a question type to the list.
     *
     * @param list     The array to which the question type is added.
     * @param question The question object.
     * @param value    The value to set in the array.
     */
    static void addType(String[] list, Question question, int value) {
        list[value] = question.typeQuestion;
    }

    /**
     * Retrieves a random question from the provided question list based on the
     * specified question types.
     *
     * @param questionList      The list of available questions.
     * @param questionTypeArray The array of question types already used.
     * @param value             A value that tells how many questions have already been asked.
     * @return A new question from the given list.
     */
    public static Question obterPerguntaAleatoria(ArrayList<Question> questionList,String[] questionTypeArray, int value) {

        if (questionList != null && !questionList.isEmpty()) {
            Random random = new Random();
            Question randomQuestion = null;

            // Loop until a compatible question with options is found

            while (randomQuestion == null || randomQuestion.getOptions().isEmpty()) {
                int indiceAleatorio = random.nextInt(questionList.size());
                int flag = 0;

                //checks if the randomly selected question's type has already been used
                if (questionTypeArray[0] != null) { //if type[0] != null, means no question has been asked yet and every type is available
                    for (int i = 0; i < questionTypeArray.length; i++) {
                        if (questionList.get(indiceAleatorio).typeQuestion.equals(questionTypeArray[i])) {
                            flag = 1; //question is not compatible
                            break;
                        }

                    }
                }
                //Builds the question
                if (flag == 0) {
                    randomQuestion = questionList.get(indiceAleatorio);
                    randomQuestion = randomQuestion.modeSetUp(value); 

                }
                
            }

            //Shuffles que order of the answers, and because only 5 options at max will be displayed, shuffles until the correct option is not left out (index > 4)
            do {
                Collections.shuffle(randomQuestion.arrayOption);
            }
            while (getCorrectOptionIndex(randomQuestion) > 4);

            return randomQuestion;
        } else {
            return null;
        }
    }

    /**
     * Retrieves the index of the correct option in a given question's option array.
     *
     * @param question The Question object.
     * @return The index of the correct option, or -1 if not found.
     */
    
    public static int getCorrectOptionIndex(Question question) {
        for (int i = 0; i < question.arrayOption.size(); i++) {
            if (question.arrayOption.get(i).opString.equals(question.correctOption.opString))
                return i;
        }
        return -1;
    }

    /**
     * Calculates the score for a given person based on their answers to questions.
     *
     * @param person The Person object representing the player.
     * @return The calculated score.
     */
    public static int calcScore(Person person) {
        int temp = 0;

        if (person.questionList.size() == 0) return -1; // if empty

        for (int i = 0; i < person.questionList.size(); i++) {
            if (person.answers[i] == true)
                temp += person.questionList.get(i).scoreCalculator();
        }
        return temp;
    }

    /**
     * Identifies the top three players based on their scores.
     *
     * @param games The list of Person objects representing players.
     * @return An ArrayList containing the top three players.
     */
    public static ArrayList<Person> top3(ArrayList<Person> games) {
        int totalGames = games.size(); // Total Number of games
        int[] indexes = new int[totalGames + 1]; // Create an array with all possible indexes + 1, the latter being the baseline for comparing the scores (indexes[totalGames] = -1)

        indexes[totalGames] = -1;

        Person first = new Person(),  second = new Person(),  third = new Person(); // These will be be indexes for the first, second and third best games
        
        // They start as the last index of the array (value -1), so as to have every gamescore into account (minimum score for a game is 0)

        ArrayList<Person> tempArray = new ArrayList<>();
        
        
        for (int i = 0; i < totalGames; i++) {

            if (calcScore(games.get(i)) >= calcScore(first)) { // If the score is bigger or the same as current first place, it will move the scoreboard down, and replace i as the index for first place
                third = second;
                second = first;
                first = games.get(i);
            } else if (calcScore(games.get(i)) >= calcScore(second)) { // If the score is bigger or the same as current second place, it will move the scoreboard down, and replace i as the index for second place
                third = second;
                second = games.get(i);
            } else if (calcScore(games.get(i)) >= calcScore(third)) {// If the score is bigger or the same as current third place, it will replace i as the index for third place
                third = games.get(i);
            }

        }

        //Create the array with only the 3 best games, using the indexes obtained from before
        tempArray.add(first);
        tempArray.add(second);
        tempArray.add(third);
        ArrayList<Person> top3 = new ArrayList<>(); 
        for (Person i : tempArray) {
            if (calcScore(i) != -1)
                top3.add(i);
        }
        return top3;
    }

    /**
     * The main method to start the trivia game.
     *
     * @param args The command line arguments.
     */

    public static void main(String[] args) {
        UiWindow janela = new UiWindow();
        janela.mainWindow();
    }
}
