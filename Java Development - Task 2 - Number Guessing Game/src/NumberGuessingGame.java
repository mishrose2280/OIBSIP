
import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    private static int totalRounds = 0;
    private static int totalWins = 0;
    private static int totalLosses = 0;

    public static void main(String[] args) {

        System.out.println("==================================");
        System.out.println("       NUMBER GUESSING GAME");
        System.out.println("==================================");

        boolean playAgain = true;

        while (playAgain) {

            int[] difficulty = chooseDifficulty();

            int maxNumber = difficulty[0];
            int maxAttempts = difficulty[1];

            playRound(maxNumber, maxAttempts);

            System.out.println("\n========== SCOREBOARD ==========");
            System.out.println("Total Rounds : " + totalRounds);
            System.out.println("Wins         : " + totalWins);
            System.out.println("Losses       : " + totalLosses);
            System.out.println("================================");

            playAgain = askToPlayAgain();
        }

        System.out.println("\nThanks for playing!");
        System.out.println("Final Score: " + totalWins + " win(s) out of "
                + totalRounds + " round(s).");

        scanner.close();
    }

    // Select difficulty level
    private static int[] chooseDifficulty() {

        System.out.println("\nSelect Difficulty:");
        System.out.println("1. Easy   (1-50, 10 attempts)");
        System.out.println("2. Medium (1-100, 7 attempts)");
        System.out.println("3. Hard   (1-200, 5 attempts)");

        while (true) {

            System.out.print("Enter your choice (1-3): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {

                case "1":
                    System.out.println("Easy mode selected!");
                    return new int[]{50, 10};

                case "2":
                    System.out.println("Medium mode selected!");
                    return new int[]{100, 7};

                case "3":
                    System.out.println("Hard mode selected!");
                    return new int[]{200, 5};

                default:
                    System.out.println("Invalid choice. Please enter 1, 2 or 3.");
            }
        }
    }

    // Run one round of the game
    private static void playRound(int maxNumber, int maxAttempts) {

        int secretNumber = random.nextInt(maxNumber) + 1;
        int attempts = 0;
        boolean won = false;

        totalRounds++;

        System.out.println("\nGuess a number between 1 and " + maxNumber);
        System.out.println("You have " + maxAttempts + " attempts.");

        while (attempts < maxAttempts) {

            System.out.print("\nEnter your guess: ");
            String input = scanner.nextLine().trim();

            int guess;

            // Validate numeric input
            try {
                guess = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a whole number.");
                continue;
            }

            // Validate the guessing range
            if (guess < 1 || guess > maxNumber) {
                System.out.println("Please enter a number between 1 and "
                        + maxNumber + ".");
                continue;
            }

            attempts++;

            if (guess > secretNumber) {
                System.out.println("Too High!");
            } else if (guess < secretNumber) {
                System.out.println("Too Low!");
            } else {
                System.out.println("\nCorrect! You won!");
                System.out.println("You guessed the number in "
                        + attempts + " attempt(s).");
                won = true;
                break;
            }

            System.out.println("Attempts remaining: "
                    + (maxAttempts - attempts));
        }

        // Update score after the round
        if (won) {
            totalWins++;
        } else {
            totalLosses++;
            System.out.println("\nYou Lost!");
            System.out.println("The correct number was: " + secretNumber);
        }
    }

    // Ask whether the player wants another round
    private static boolean askToPlayAgain() {

        while (true) {

            System.out.print("\nDo you want to play again? (yes/no): ");
            String answer = scanner.nextLine().trim().toLowerCase();

            if (answer.equals("yes") || answer.equals("y")) {
                return true;
            } else if (answer.equals("no") || answer.equals("n")) {
                return false;
            } else {
                System.out.println("Please enter yes or no.");
            }
        }
    }
}