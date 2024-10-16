import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

class GuessTheWordGUI extends JFrame {
    private String[][] wordCategories = {
            { "apple", "banana", "orange", "grape", "pear" },
            { "dog", "cat", "bird", "lion", "tiger" },
            { "india", "egypt", "france", "portugal", "canada" },
            { "football", "cricket", "badminton", "basketball", "boxing" }
    };
    private String[] categories = { "Fruit", "Animal", "Country", "Sports" };

    private JComboBox<String> categoryComboBox;
    private JLabel wordLabel;
    private JTextField guessField;
    private JButton guessButton;
    private JLabel messageLabel;
    private int maxAttempts = 6;
    private int attempts = 0;
    private String wordToGuess;
    private char[] guessedWord;

    public GuessTheWordGUI() {
        setTitle("Guess the Word Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        categoryComboBox = new JComboBox<>(categories);
        wordLabel = new JLabel();
        guessField = new JTextField(10);
        guessButton = new JButton("Guess");
        messageLabel = new JLabel("Choose a category to start");

        add(new JLabel("Select Category:"));
        add(categoryComboBox);
        add(new JLabel("Word:"));
        add(wordLabel);
        add(new JLabel("Enter a letter:"));
        add(guessField);
        add(guessButton);
        add(messageLabel);

        categoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeGuess();
            }
        });

        setVisible(true);
    }

    private void startGame() {
        int categoryChoice = categoryComboBox.getSelectedIndex();
        String[] words = wordCategories[categoryChoice];
        Random random = new Random();
        wordToGuess = words[random.nextInt(words.length)];
        guessedWord = new char[wordToGuess.length()];
        for (int i = 0; i < wordToGuess.length(); i++) {
            guessedWord[i] = '_';
        }
        attempts = 0;
        wordLabel.setText(String.valueOf(guessedWord));
        messageLabel.setText("You have " + maxAttempts + " attempts remaining.");
    }

    private void makeGuess() {
        String input = guessField.getText().trim().toLowerCase();
        if (input.length() != 1) {
            messageLabel.setText("Please enter a single letter.");
            return;
        }

        char guess = input.charAt(0);
        boolean correctGuess = false;

        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == guess) {
                guessedWord[i] = guess;
                correctGuess = true;
            }
        }

        if (!correctGuess) {
            attempts++;
            messageLabel.setText("Incorrect guess. Attempts remaining: " + (maxAttempts - attempts));
        } else {
            messageLabel.setText("Correct! Keep going.");
        }

        wordLabel.setText(String.valueOf(guessedWord));
        guessField.setText("");

        if (String.valueOf(guessedWord).equals(wordToGuess)) {
            messageLabel.setText("Congratulations! You guessed the word: " + wordToGuess);
            guessButton.setEnabled(false);
        }

        if (attempts == maxAttempts) {
            messageLabel.setText("You ran out of attempts. The word was: " + wordToGuess);
            guessButton.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GuessTheWordGUI();
            }
        });
    }
}
