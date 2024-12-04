import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DayTwentyOne extends JFrame {
    private final ArrayList<JButton> questionButtons = new ArrayList<>();
    private final ArrayList<String> questions = new ArrayList<>();
    private final ArrayList<String> answers = new ArrayList<>();

    private final JLabel statusLabel = new JLabel("Hi my love! Today's surprise is a " +
            "20 Questions game. Pick a question to get started!", SwingConstants.CENTER);
    private final JTextField guessField = new JTextField();
    private final JButton submitButton = new JButton("Submit Guess");
    private int questionsAsked = 0;
    private final String correctAnswer = "Hog"; // The correct answer the user must guess
    private boolean gameOver = false;

    public DayTwentyOne() {
        setTitle("Day 21 Surprise!");
        setSize(1200, 900);
        setLocationRelativeTo(null);
        setBackground(new Color(86, 120, 95));
        setLayout(new BorderLayout());
        populateQuestions();
        populateAnswers();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Top Panel: Intro and status
        statusLabel.setFont(new Font("Cooper Black", Font.BOLD, 16));
        statusLabel.setForeground(new Color(44, 54, 45));
        add(statusLabel, BorderLayout.NORTH);

        // Center Panel: Question buttons
        JPanel questionPanel = new JPanel(new GridLayout(4, 5, 10, 10));
        questionPanel.setBackground(new Color(86, 120, 95)); // Use custom background color for grid
        for (String question : questions) {
            JButton questionButton = new JButton(question);
            questionButton.setFont(new Font("Cooper Black", Font.BOLD, 12));
            questionButton.setBackground(new Color(190, 230, 201));
            questionButton.addActionListener(new QuestionButtonListener());
            questionButtons.add(questionButton);
            questionPanel.add(questionButton);
        }
        add(questionPanel, BorderLayout.CENTER);

        // Bottom Panel: Guess input and submit button
        JPanel bottomPanel = new JPanel(new BorderLayout());
        guessField.setFont(new Font("Cooper Black", Font.BOLD, 14));
        guessField.setEnabled(true); // Allow guessing from the start
        bottomPanel.add(guessField, BorderLayout.CENTER);

        submitButton.setFont(new Font("Cooper Black", Font.BOLD, 14));
        submitButton.setEnabled(true); // Allow submitting a guess from the start
        submitButton.addActionListener(new SubmitButtonListener());
        bottomPanel.add(submitButton, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Populate questions
    private void populateQuestions() {
        questions.add("Is it an animal?");
        questions.add("Is it a machine?");
        questions.add("Does it live in the ocean?");
        questions.add("Does it live on land?");
        questions.add("Is it a mammal?");
        questions.add("Is it an amphibian?");
        questions.add("Do humans make it?");
        questions.add("Do humans eat it?");
        questions.add("Do humans use it for emails?");
        questions.add("Is it alive?");
        questions.add("Does it make noise?");
        questions.add("Does it have 2 legs?");
        questions.add("Does it have flippers?");
        questions.add("Does it dance?");
        questions.add("Does it sing?");
        questions.add("Does it swim?");
        questions.add("Can you roast it?");
        questions.add("Is it made of metal?");
        questions.add("Is it in a movie title?");
        questions.add("Is it in a song title?");
    }

    // Populate answers (Yes/No)
    private void populateAnswers() {
        answers.add("Yes");
        answers.add("No");
        answers.add("No");
        answers.add("Yes");
        answers.add("Yes");
        answers.add("No");
        answers.add("No");
        answers.add("Yes");
        answers.add("No");
        answers.add("Yes");
        answers.add("Yes");
        answers.add("No");
        answers.add("No");
        answers.add("No");
        answers.add("No");
        answers.add("No");
        answers.add("Yes");
        answers.add("No");
        answers.add("No");
        answers.add("Yes");
    }

    // ActionListener for question buttons
    private class QuestionButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (gameOver) {
                statusLabel.setText("Submit your guess below.");
                return;
            }

            JButton sourceButton = (JButton) e.getSource();
            int index = questionButtons.indexOf(sourceButton);

            if (index != -1) {
                // Display the answer
                String answer = answers.get(index);
                statusLabel.setText("Answer: " + answer);

                // Disable the clicked button
                sourceButton.setEnabled(false);
                questionsAsked++;
            }
        }
    }

    // ActionListener for submit button
    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String guess = guessField.getText().trim();
            if (guess.isEmpty()) {
                statusLabel.setText("Please type a guess!");
                return;
            }

            // Compare guess with the correct answer
            if (guess.equalsIgnoreCase(correctAnswer)) {
                statusLabel.setText("Congratulations! You guessed it: " + correctAnswer);
            } else {
                statusLabel.setText("Wrong guess. The correct answer was: " + correctAnswer);
            }

            // Disable further interaction
            guessField.setEnabled(false);
            submitButton.setEnabled(false);
            gameOver = true;
        }
    }

    // Main method to run the game
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            Color customGridColor = new Color(237, 199, 237);
            DayTwentyOne game = new DayTwentyOne();
            game.setVisible(true);
        });
    }
}
