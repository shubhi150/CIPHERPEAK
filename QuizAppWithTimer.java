import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizAppWithTimer {
    private static boolean timeUp = false;

    public static void main(String[] args) {
        // Array of questions
        String[] questions = {
            "What is the capital of France?",
            "Who wrote 'To Kill a Mockingbird'?",
            "What is the largest planet in our solar system?",
            "What is the chemical symbol for water?",
            "Who painted the Mona Lisa?"
        };

        // Array of options for each question
        String[][] options = {
            {"A. Berlin", "B. Madrid", "C. Paris", "D. Rome"},
            {"A. Harper Lee", "B. Mark Twain", "C. J.K. Rowling", "D. Ernest Hemingway"},
            {"A. Earth", "B. Mars", "C. Jupiter", "D. Saturn"},
            {"A. H2O", "B. O2", "C. CO2", "D. HO"},
            {"A. Vincent van Gogh", "B. Pablo Picasso", "C. Leonardo da Vinci", "D. Claude Monet"}
        };

        // Array of correct answers
        char[] answers = {'C', 'A', 'C', 'A', 'C'};

        // Initialize the scanner
        Scanner scanner = new Scanner(System.in);

        // Prompt user to start the quiz
        System.out.println("Welcome to the quiz! Are you ready to start? (yes/no)");
        String ready = scanner.nextLine().trim().toLowerCase();

        if (!ready.equals("yes")) {
            System.out.println("Quiz aborted. Come back when you're ready!");
            scanner.close();
            return;
        }

        // Variable to keep track of the score
        int score = 0;

        // Timer setup
        int timeLimitPerQuestion = 30; // seconds
        Timer timer = new Timer();

        // Loop through each question
        for (int i = 0; i < questions.length; i++) {
            // Reset the time-up flag
            timeUp = false;

            // Schedule a task to set timeUp to true after the time limit
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    timeUp = true;
                }
            }, timeLimitPerQuestion * 1000);

            // Display the question
            System.out.println(questions[i]);

            // Display the options
            for (String option : options[i]) {
                System.out.println(option);
            }

            // Prompt user for answer
            System.out.print("Your answer (you have " + timeLimitPerQuestion + " seconds): ");
            long startTime = System.currentTimeMillis();

            // Read the user's answer with a time limit
            String userAnswer = null;
            while ((System.currentTimeMillis() - startTime) < (timeLimitPerQuestion * 1000) && !timeUp) {
                if (scanner.hasNext()) {
                    userAnswer = scanner.next();
                    break;
                }
            }

            // Check if time is up
            if (timeUp) {
                System.out.println("\nTime's up! The correct answer was " + answers[i]);
            } else {
                // Check if the answer is correct
                if (userAnswer != null && userAnswer.length() == 1 && userAnswer.charAt(0) == answers[i]) {
                    System.out.println("Correct!");
                    score++;
                } else {
                    System.out.println("Wrong! The correct answer is " + answers[i]);
                }
            }
            System.out.println(); // Add a new line for better readability
        }

        // Display the final score
        System.out.println("You scored " + score + " out of " + questions.length);

        // Close the scanner and cancel the timer
        scanner.close();
        timer.cancel();
    }
}


