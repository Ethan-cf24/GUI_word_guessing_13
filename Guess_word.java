import javax.swing.*;
import java.util.Random;
import java.util.Scanner;

public class Guess_word {
    private JPanel contentPanel;
    private JLabel label;
    private JPanel panel1;
    public static void main(String[] args) {
        Guess_word gword = new Guess_word();
        gword.game();
    }

    public void game() {
        String[][] Word_Categories = {
                { "apple", "banana", "orange", "grape", "pear" },
                { "dog", "cat", "bird", "lion", "tiger" },
                { "india", "egypt", "france", "portugal", "canada" },
                { "football", "cricket", "badminton", "basketball", "boxing" }
        };
        String[] categories = { "Fruit", "Animal", "Country", "Sports" };

        // Use JOptionPane for GUI interaction
        String categoryChoices = String.join("\n", "1. Fruit", "2. Animal", "3. Country", "4. Sports");
        String input = JOptionPane.showInputDialog(null, "Choose a category:\n" + categoryChoices);
        int categoryChoice;

        try {
            categoryChoice = Integer.parseInt(input);
            if (categoryChoice < 1 || categoryChoice > 4) {
                JOptionPane.showMessageDialog(null, "Invalid choice. Exiting game.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Exiting game.");
            return;
        }

        String[] words = Word_Categories[categoryChoice - 1];
        Random random = new Random();
        int randomIndex = random.nextInt(words.length);
        String wordToGuess = words[randomIndex];
        int maxAttempts = 6;
        int attempts = 0;
        char[] guessedWord = new char[wordToGuess.length()];
        for (int i = 0; i < wordToGuess.length(); i++) {
            guessedWord[i] = '_';
        }

        while (attempts < maxAttempts) {
            String guessedWordString = String.valueOf(guessedWord);
            input = JOptionPane.showInputDialog(null, "Guess the word: " + guessedWordString + "\nEnter a letter:");
            if (input == null || input.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No input provided. Exiting game.");
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
                JOptionPane.showMessageDialog(null, "Incorrect guess. Attempts remaining: " + (maxAttempts - attempts));
            }

            if (String.valueOf(guessedWord).equals(wordToGuess)) {
                JOptionPane.showMessageDialog(null, "Congratulations! You guessed the word: " + wordToGuess);
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "You ran out of attempts. The word was: " + wordToGuess);
    }
}