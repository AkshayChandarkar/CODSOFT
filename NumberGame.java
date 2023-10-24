import java.util.Random;
import java.util.Scanner;

public class NumberGame {
	public static void main(String[] args) {
		Scanner inputScanner = new Scanner(System.in);
		Random random = new Random();
		int minRange = 1;
		int maxRange = 100;
		int attempts = 0;
		int playerScore = 0;

		boolean playAgain = true;
		while (playAgain) {
			int randomNumber = random.nextInt(maxRange) + minRange;
			System.out.println("--------------------------------------------------------------------");
			System.out.println("                 Welcome to the Number Guessing Game!");
			System.out.println("--------------------------------------------------------------------");
			System.out.println(" ");
			System.out.println("I have selected a number between " + minRange + " and " + maxRange + ". Try to guess it!");

			int userGuess;
			boolean hasGuessedCorrectly = false;
			while (!hasGuessedCorrectly && attempts < 5) {
				System.out.print("Enter your guess: ");
				userGuess = inputScanner.nextInt();
				attempts++;

				if (userGuess == randomNumber) {
					System.out.println("--------------------------------------------------------------------");
					System.out.println("Congratulations! You've guessed the correct number.");
					hasGuessedCorrectly = true;
					playerScore++;
				} else if (userGuess < randomNumber) {
					System.out.println("Your guess is too low. Try again.");
				} else {
					System.out.println("Your guess is too high. Try again.");
				}
			}
			System.out.println("--------------------------------------------------------------------");
			System.out.println("You took " + attempts + " attempts to guess the number.");
			System.out.println("--------------------------------------------------------------------");
			System.out.println("Your current score is: " + playerScore);

			if (attempts >= 5) {
				System.out.println("--------------------------------------------------------------------");
				System.out.println("You have reached the maximum number of attempts.");
			}
			System.out.println("--------------------------------------------------------------------");
			System.out.print("Do you want to play again? (yes/no): ");
			String playAgainResponse = inputScanner.next();
			if (!playAgainResponse.equalsIgnoreCase("yes")) {
				playAgain = false;
				System.out.println("--------------------------------------------------------------------");
				System.out.println("Thank you for playing. Goodbye!");
			}
			attempts = 0;
		}
		inputScanner.close();
	}
}
