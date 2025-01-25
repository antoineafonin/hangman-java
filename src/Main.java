import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Main {
    private static final int ATTEMPTS = 7;
    private static final String[] hangmanStages = {
            """
        +---+
            |
            |
            |
           ===
        """,
            """
        +---+
        O   |
            |
            |
           ===
        """,
            """
        +---+
        O   |
        |   |
            |
           ===
        """,
            """
        +---+
        O   |
       /|   |
            |
           ===
       """,
            """
        +---+
        O   |
       /|\\  |
            |
           ===
       """,
            """
        +---+
        O   |
       /|\\  |
       /    |
           ===
       """,
            """
        +---+
        O   |
       /|\\  |
       / \\  |
           ===
       """
    };

    public static void main(String[] args) {
        String secret = getRandomWord();

        hangman(secret);
    }

    public static void hangman(String secret) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the game, Hangman!");
        System.out.printf("I am thinking of a word that is %d letters long.\n", secret.length());

        int guesses = ATTEMPTS;
        int stages = 0;
        StringBuilder guessedWord = new StringBuilder();
        String availableLetters;
        String lettersGuessed = "";

        while (true) {
            availableLetters = getAvailableLetters(lettersGuessed);
            System.out.println("-------------");
            System.out.printf("You have %d guesses left.\n", guesses);
            System.out.printf("Available letters: %s\n", availableLetters);

            System.out.print("Please guess a letter: ");
            String letter = sc.nextLine().toLowerCase();

            lettersGuessed += letter;

            if (!Character.isLetter(letter.charAt(0)) || letter.length() > 1) {
                System.out.printf("Oops! '%s' is not a valid letter: \n", letter);
                continue;
            }

            if (guessedWord.toString().contains(letter)) {
                System.out.printf("Oops! You've already guessed that letter: %s\n", guessedWord);
                continue;
            }

            getGuessedWord(secret, lettersGuessed, guessedWord);

            if (secret.contains(letter)) {
                System.out.printf("Good guess: %s\n", guessedWord);
                if (isWordGuessed(secret, lettersGuessed)) {
                    System.out.println("Congratulations, you won");
                    break;
                }
            } else {
                System.out.printf("Oops! That letter is not in my word: %s\n", guessedWord);
                printStage(stages);
                stages++;
                guesses--;

                if (guesses == 0) {
                    System.out.println("-------------");
                    System.out.printf("Sorry, you ran out of guesses. The word was %s.\n", secret);
                    break;
                }
            }
        }
    }

    public static boolean isWordGuessed(String secret, String lettersGuessed) {
        for (int i = 0; i < secret.length(); i++) {
            if (lettersGuessed.indexOf(secret.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }

    public static void getGuessedWord(String secret, String lettersGuessed, StringBuilder guessedLetters) {
        guessedLetters.setLength(0);

        guessedLetters.append("_".repeat(secret.length()));

        for (int i = 0; i < secret.length(); i++) {
            char currentChar = secret.charAt(i);

            if (lettersGuessed.indexOf(currentChar) != -1) {
                guessedLetters.setCharAt(i, currentChar);
            }
        }
    }

    public static String getAvailableLetters(String lettersGuessed) {
        StringBuilder alphabet = new StringBuilder("abcdefghijklmnopqrstuvwxyz");
        for (char guessedLetter : lettersGuessed.toCharArray()) {
            int index = alphabet.indexOf(String.valueOf(guessedLetter));
            if (index != -1) {
                alphabet.deleteCharAt(index);
            }
        }
        return alphabet.toString();
    }

    public static String getRandomWord() {
        Random rand = new Random();
        ArrayList<String> wordsList = new ArrayList<>();
        try {
            File words = new File("../hangman/src/words.txt");
            Scanner reader = new Scanner(words);
            while (reader.hasNextLine()) {
                wordsList.add(reader.nextLine().trim());
            }

            reader.close();

            if (!wordsList.isEmpty()) {
                int randomIndex = rand.nextInt(100);
                return wordsList.get(randomIndex);
            } else {
                System.out.println("The file is empty");
                return "";
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
            return "";
        }
    }


    public static void printStage(int stage) {
        System.out.println(hangmanStages[stage]);
    }
}
