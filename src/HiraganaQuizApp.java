import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * HiraganaQuizApp is a console-based quiz application that tests the user's knowledge of Hiragana characters.
 * The quiz consists of 10 questions, each asking the user to identify the correct Hiragana character for a given romaji.
 * If the user answers incorrectly, the correct answer is marked with a '*'.
 * At the end of the quiz, the user is prompted to restart the quiz or exit the application.
 */
public class HiraganaQuizApp {
    private static final Map<String, String> hiragana = new HashMap<>();
    private static final Random random = new Random();
    private int score = 0;
    private int questionCount = 0;
    private String correctAnswer;
    private boolean quizFinished = false;
    private final Scanner scanner = new Scanner(System.in);

    // Static block to initialize the Hiragana characters and their romaji
    static {
        hiragana.put("あ", "a");
        hiragana.put("い", "i");
        hiragana.put("う", "u");
        hiragana.put("え", "e");
        hiragana.put("お", "o");
        hiragana.put("か", "ka");
        hiragana.put("き", "ki");
        hiragana.put("く", "ku");
        hiragana.put("け", "ke");
        hiragana.put("こ", "ko");
        hiragana.put("さ", "sa");
        hiragana.put("し", "shi");
        hiragana.put("す", "su");
        hiragana.put("せ", "se");
        hiragana.put("そ", "so");
        hiragana.put("た", "ta");
        hiragana.put("ち", "chi");
        hiragana.put("つ", "tsu");
        hiragana.put("て", "te");
        hiragana.put("と", "to");
        hiragana.put("な", "na");
        hiragana.put("に", "ni");
        hiragana.put("ぬ", "nu");
        hiragana.put("ね", "ne");
        hiragana.put("の", "no");
        hiragana.put("は", "ha");
        hiragana.put("ひ", "hi");
        hiragana.put("ふ", "fu");
        hiragana.put("へ", "he");
        hiragana.put("ほ", "ho");
        hiragana.put("ま", "ma");
        hiragana.put("み", "mi");
        hiragana.put("む", "mu");
        hiragana.put("め", "me");
        hiragana.put("も", "mo");
        hiragana.put("や", "ya");
        hiragana.put("ゆ", "yu");
        hiragana.put("よ", "yo");
        hiragana.put("ら", "ra");
        hiragana.put("り", "ri");
        hiragana.put("る", "ru");
        hiragana.put("れ", "re");
        hiragana.put("ろ", "ro");
        hiragana.put("わ", "wa");
        hiragana.put("を", "wo");
        hiragana.put("ん", "n");
    }

    // Method to start the quiz
    public void start() {
        while (!quizFinished) {
            nextQuestion();
        }
        System.out.println("Quiz Completed! Your final score is " + score + "/10");
        promptRestart();
    }

    // Method to display the next question
    private void nextQuestion() {
        if (questionCount < 10) {
            String romaji = getRandomRomaji();
            correctAnswer = getKeyByValue(romaji);
            ArrayList<String> options = generateOptions(correctAnswer);

            System.out.println("\nWhat is the correct Hiragana character for the romaji '" + romaji + "'?");
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }

            int userAnswer = getUserInput(options.size());

            if (options.get(userAnswer - 1).equals(correctAnswer)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong! The correct answer was '" + correctAnswer + "'");
                markCorrectAnswer(options);
            }

            displayScore();
            questionCount++;
        } else {
            quizFinished = true;
        }
    }

    // Method to get and validate user input
    private int getUserInput(int numOptions) {
        int userAnswer = -1;
        while (userAnswer < 1 || userAnswer > numOptions) {
            System.out.print("Enter the number of your choice: ");
            try {
                userAnswer = Integer.parseInt(scanner.nextLine().trim());
                if (userAnswer < 1 || userAnswer > numOptions) {
                    System.out.println("Invalid choice. Please select a number between 1 and " + numOptions + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return userAnswer;
    }

    // Method to display the current score
    private void displayScore() {
        System.out.println("Score: " + score + "/10");
    }

    // Method to reset the quiz
    private void resetQuiz() {
        score = 0;
        questionCount = 0;
        quizFinished = false;
    }

    // Method to prompt the user to restart the quiz
    private void promptRestart() {
        System.out.print("Do you want to restart the quiz? (yes/no): ");
        String restart = scanner.nextLine().trim().toLowerCase();
        if (restart.equals("yes")) {
            resetQuiz();
            start();
        } else {
            System.out.println("Thank you for playing!");
        }
    }

    // Method to mark the correct answer in the options
    private void markCorrectAnswer(ArrayList<String> options) {
        for (int i = 0; i < options.size(); i++) {
            if (options.get(i).equals(correctAnswer)) {
                System.out.println((i + 1) + ". " + options.get(i) + " *");
            }
        }
    }

    // Method to get a random romaji
    private static String getRandomRomaji() {
        ArrayList<String> values = new ArrayList<>(hiragana.values());
        return values.get(random.nextInt(values.size()));
    }

    // Method to get the key (Hiragana) for a given value (romaji)
    private static String getKeyByValue(String value) {
        for (Map.Entry<String, String> entry : HiraganaQuizApp.hiragana.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    // Method to generate options including the correct answer and three incorrect answers
    private static ArrayList<String> generateOptions(String correctAnswer) {
        ArrayList<String> allAnswers = new ArrayList<>(hiragana.keySet());
        allAnswers.remove(correctAnswer);
        Collections.shuffle(allAnswers);

        ArrayList<String> options = new ArrayList<>();
        options.add(correctAnswer);
        options.add(allAnswers.get(0));
        options.add(allAnswers.get(1));
        options.add(allAnswers.get(2));
        Collections.shuffle(options);

        return options;
    }

    // Main method to run the application
    public static void main(String[] args) {
        HiraganaQuizApp app = new HiraganaQuizApp();
        app.start();
    }
}