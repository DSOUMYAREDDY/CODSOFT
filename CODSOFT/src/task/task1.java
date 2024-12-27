package task;

import java.util.Scanner;

public class task1{

	public static void main(String[] args) {
     Scanner scanner = new Scanner(System.in);
		int numberToGuess = (int) (Math.random()*100) + 1;
        int guess = 0;
        int attempts =0;
        int maxAttempts = 5; // Set the maximum number of attempts
		System.out.println("Welcome to the Number Guessing Game! ");
		System.out.println("You have " + maxAttempts + " attempts to guess the number." );
		while (attempts < maxAttempts){
			System.out.println("Guess a number between 1 and 100: ");
			guess = scanner.nextInt();
			attempts++;
			
			if (guess == numberToGuess) {
			System.out.println("Congratulations! You guessed the number in "+attempts + " attempts.");
			break;
			}else {
				System.out.println(guess < numberToGuess ? "Too low. Try again." : "Too high. Try again.");
			}
		}
		if (attempts == maxAttempts && guess != numberToGuess) {
			System.out.println("Sorry! You've used all " + maxAttempts + " attempts. The correct number was " + numberToGuess + ".");
		}
		scanner.close();

	}

}



