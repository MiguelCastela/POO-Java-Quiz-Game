import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
/**
 * The {@code UiWindow} class represents the graphical user interface (GUI) for a "PoAO Trivia Game".
 * It extends the {@code JFrame} class and provides methods to create and manage different windows
 * for the game, including the main window, question window, and final results and scoreboard window.
 * The game involves answering multiple-choice or true/false questions with a scoring system.
 *
 * @author Miguel Castela
 * @author Miguel Martins
 * @version 1.0
 */
public class UiWindow extends JFrame {
    private JFrame frame;
    private JPanel panel;
    ArrayList<JButton> buttonList = new ArrayList<JButton>();
    JButton correctButton = new JButton();

    private String makeSeparator() {
        String s = "-";
        for (int i = 0; i < 1000; i++) {
            s += "-";
        }
        return s;
    }

    /**
     * Constructs a new {@code UiWindow} object.
     */
    public UiWindow() {
    }
    /**
     * Creates and displays the main window for the PoAO Trivia Game.
     * The main window includes a welcome message and a "Start Game" button.
     */
    public void mainWindow() {

        frame = new JFrame();
        frame.setTitle("PoAO trivia");
        frame.setSize(2048, 1024);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new FlowLayout());
        JLabel welcomeLabel = new JLabel("<html><center><font size='6' color='orange'><b>Welcome to the PoAO Trivia Game!</b><br/><font size='5' color='black'><b>" + makeSeparator() + "</b></font></font><br/><font size='5' color='black'><b>=> </b>As perguntas serão de escolha multipla ou de verdadeiro e falso<br/><b>=> </b>Cada questão vale uma base de cinco pontos, possuindo uma majoração dependendo do tipo de questão</font></center></html>");
        welcomePanel.add(welcomeLabel);
        welcomePanel.add(Box.createVerticalStrut(600));
        JButton startButton = new JButton("<html><font size='5'><b>Start Game</b></font></html>");
        startButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        startButton.setBackground(Color.LIGHT_GRAY);
        startButton.setPreferredSize(new Dimension(400, 300));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll(); // Remove the welcome panel
                frame.repaint();
                startGame();
            }
        });
        welcomePanel.add(startButton);
        frame.setContentPane(welcomePanel);
        frame.setVisible(true);
    }
    /**
     * Starts the game by loading questions and displaying the first question window.
     * If no questions are available, a message dialog is shown.
     */
    private void startGame() {
        ArrayList<Question> questionList = FileManager.getQuestionsFromFile();
        if (questionList != null && !questionList.isEmpty()) {
            loadNextQuestion(questionList);
            frame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "No questions available.", "Game Over", JOptionPane.PLAIN_MESSAGE);
        }
    }
    /**
     * Loads the next question and updates the question window.
     * If the maximum number of questions is reached, it displays the final results window.
     *
     * @param questionList The list of questions for the game.
     */
    private void loadNextQuestion(ArrayList<Question> questionList) {
        Question currentQuestion = POOaoTrivia.obterPerguntaAleatoria(questionList, POOaoTrivia.questionTypeArray, POOaoTrivia.value);
        POOaoTrivia.gameQuestions.add(currentQuestion);
        POOaoTrivia.addType(POOaoTrivia.questionTypeArray, currentQuestion, POOaoTrivia.value);
        POOaoTrivia.value++;
        ArrayList<Option> optionsList = currentQuestion.getOptions();
        JLabel questionLabel = new JLabel("<html><center><font color='blue' size='6'><b>" + currentQuestion.typeQuestion + "<br/><font color='gray' size='5'>"+ "[Pergunta "+ POOaoTrivia.value+"]" + "</b></font><br/><font color='black' size='5'><b>" + currentQuestion.getQuestion() +
                "<font color='gray' size='5'> ==> [</font><font color='gray'><b>" + currentQuestion.scoreCalculator() + " pontos</b></font><font color='gray' size='5'>]" +
                "<font color='brown'>" + "<center><html>" + makeSeparator(), SwingConstants.CENTER);
        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(questionLabel);
        if (optionsList.size() > 2) {
            panel.add(Box.createVerticalStrut(55));
            for (int i = 0; i < Math.min(5, optionsList.size()); i++) { //Nem todas as perguntas têm 5 opcoes, arte pode ter 3
                char optionLetter = (char) ('A' + i);
                JButton button = new JButton("<html><font size='5'><b><font color='darkblue'>[" + optionLetter + "]</font> " + optionsList.get(i).getOpString() + "</font></html>");
                button.setPreferredSize(new Dimension(550, 100));
                button.setBackground(Color.LIGHT_GRAY);
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                panel.add(Box.createHorizontalStrut(2048));
                panel.add(button);
                button.addActionListener(new OptionButtonListener(questionList, button)); // Pass the button reference
                this.buttonList.add(button);
                if (button.getText().contains(POOaoTrivia.gameQuestions.get(POOaoTrivia.value - 1).correctOption.opString)) {
                    correctButton = button;
                }
            }
        } else {
            JButton buttonTrue = new JButton("<html><font size='5'><b>Verdadeiro</b></html>");
            buttonTrue.addActionListener(new OptionButtonListener(questionList, buttonTrue));
            panel.add(Box.createVerticalStrut(600));
            buttonTrue.setPreferredSize(new Dimension(400, 300));
            buttonTrue.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            buttonTrue.setBackground(Color.LIGHT_GRAY);
            panel.add(buttonTrue);
            panel.add(Box.createHorizontalStrut(60));

            JButton buttonFalse = new JButton("<html><font size='5'><b>Falso</b></font></html>");
            buttonFalse.setBackground(Color.LIGHT_GRAY);
            buttonFalse.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            buttonFalse.addActionListener(new OptionButtonListener(questionList, buttonFalse));
            buttonFalse.setPreferredSize(new Dimension(400, 300));
            panel.add(buttonFalse);
            this.buttonList.add(buttonTrue);
            this.buttonList.add(buttonFalse);
            if (buttonTrue.getText().contains(POOaoTrivia.gameQuestions.get(POOaoTrivia.value - 1).correctOption.opString)) {
                correctButton = buttonTrue;
            } else {
                correctButton = buttonFalse;
            }
        }
        // Set the content pane of the JFrame
        frame.setContentPane(panel);
        // Update the UI
        frame.revalidate();
        frame.repaint();
    }
    /**
     * ActionListener implementation for option buttons in the question window.
     * Handles user responses (seeing if the button hit is the correct option), updates the UI, and loads the next question.
     */
    private class OptionButtonListener implements ActionListener {
        private ArrayList<Question> questionList;
        private JButton button;
        public OptionButtonListener(ArrayList<Question> questionList, JButton button) {
            this.questionList = questionList;
            this.button = button;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            // Handle the button click event
            // Load the next random question
            //METODO DUBÍO
            for (JButton but : buttonList) {
                but.setEnabled(false);
            }
            buttonList.clear();
            JButton sourceButton = (JButton) e.getSource();
            correctButton.setBackground(Color.GREEN);
            if (sourceButton == correctButton) {
                POOaoTrivia.questionResult[POOaoTrivia.value - 1] = true;
            } else {
                POOaoTrivia.questionResult[POOaoTrivia.value - 1] = false;
                button.setBackground(Color.RED);
            }
            buttonList.clear();
            Timer timer = new Timer(3000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    // Check if 5 questions have been answered
                    if (POOaoTrivia.value == 5) {
                        finalWindow();
                        return;
                    }
                    loadNextQuestion(questionList);
                }
            });
            timer.setRepeats(false); // Execute the ActionListener only once
            timer.start();
        }
    }
    /**
     * Displays the final results window with the player's score and leaderboard.
     * Allows the player to enter their name and saves the game information.
     */
    public void finalWindow() {
        JPanel finalPanel = new JPanel();
        finalPanel.setLayout(new FlowLayout());

        JLabel goodbyeLabel = new JLabel("<html><center><b style='color: orange;'>Completaste o Jogo :) !</b><br/>" + makeSeparator());
        goodbyeLabel.setFont(new Font("Arial", Font.PLAIN, 24));

        JLabel nameLabel = new JLabel("<html><center><b>Nome: </b>");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        finalPanel.add(goodbyeLabel);
        finalPanel.add(Box.createVerticalStrut(300));

        JTextField text = new JTextField(15);
        text.setPreferredSize(new Dimension(text.getPreferredSize().width, 50));
        text.setFont(new Font("Arial", Font.PLAIN, 16));
        finalPanel.add(nameLabel);
        finalPanel.add(text);

        JButton submitButton = new JButton("Confirmar");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 18));
        submitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        submitButton.setPreferredSize(new Dimension(150,50));
        submitButton.setBackground(Color.LIGHT_GRAY);
        finalPanel.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Save gameInfo
                String playerName = text.getText();
                Person player = new Person(POOaoTrivia.gameQuestions, POOaoTrivia.questionResult, playerName);
                FileManager.saveGameFile(player);
                // Get the top3 games
                ArrayList<Person> games = FileManager.readGames();
                ArrayList<Person> topGames = POOaoTrivia.top3(games);
                JLabel scoreLabel = new JLabel("<html><br/><center><font size='6' color='blue'>" + player.getName() +
                        ", <font color='black'>a tua pontuação foi </font><font color='red'><b>" + POOaoTrivia.calcScore(player) +
                        " pontos!</b><br/>" +"    " +"<br/></font><br/><font color='orange'><b>Poao Trivia Leaderboard:</b></font></center></html>");
                finalPanel.add(scoreLabel);
                for (Person topPlayer : topGames) {
                    JLabel topPlayerLabel = new JLabel("<html><font size='6' color='gray'>" + topPlayer.getName() +
                            " ===> </font><font size='6'>" + POOaoTrivia.calcScore(topPlayer) + " pontos</font><br/></html>");
                    finalPanel.add(Box.createHorizontalStrut(2048));
                    finalPanel.add(topPlayerLabel);
                }
                finalPanel.remove(nameLabel);
                finalPanel.remove(text);
                finalPanel.remove(submitButton);
                finalPanel.revalidate();
                finalPanel.repaint();
            }
        });
        frame.setContentPane(finalPanel);
        frame.revalidate();
        frame.repaint();
    }
}