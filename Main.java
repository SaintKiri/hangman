package hangman;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		String word = ("The New York City");
		int attempts = 6;
		int remaindis = word.length();
		word = word.toLowerCase();
		ArrayList<String> correct = new ArrayList<String>();
		ArrayList<String> wrong = new ArrayList<String>();
		ArrayList<Character> underscore = new ArrayList<Character>(remaindis - 1);
		for (int i = 0; i < remaindis; i++) {
			if (String.valueOf(word.charAt(i)).equals(" ")) {
				underscore.add(' ');
			} else {
				underscore.add('_');
			}
		}

		System.out.println("Number of remaining attempts: " + attempts + "\n" + underscore
				+ "\nPlease enter your letter in lowercase: ");
		Scanner input = new Scanner(System.in);
		String enter = input.nextLine();

		while (attempts >= 1 && remaindis > 0) {
			if (enter.length() == 1 && enter.matches("[a-zA-Z]")) {
				if (correct.contains(enter)) {
					System.out.println("*****The letter is correct, and has been guessed.*****" + "\nWrong letters: ");
					for (int i = 0; i < wrong.size(); i++) {
						System.out.print(wrong.get(i) + " ");
					}

					System.out.println("\nNumber of remaining attempts: " + attempts + "\n" + underscore
							+ "\nPlease enter your letter: ");
					enter = input.nextLine();
					enter = enter.toLowerCase();
				} else if (wrong.contains(enter)) {
					System.out.println("*****The letter is wrong, and has been guessed.*****" + "\nWrong letters: ");
					for (int i = 0; i < wrong.size(); i++) {
						System.out.print(wrong.get(i) + " ");
					}

					System.out.println("\nNumber of remaining attempts: " + attempts + "\n" + underscore
							+ "\nPlease enter your letter: ");
					enter = input.nextLine();
					enter = enter.toLowerCase();
				} else if (word.indexOf(enter) < 0) {
					attempts--;
					wrong.add(enter);

					System.out.println("\nWrong letters: ");
					for (int i = 0; i < wrong.size(); i++) {
						System.out.print(wrong.get(i) + " ");
					}
					if (attempts == 0) {
						System.out.println("\n**********" + "\nGame Over" + "\nThe word is: " + word + "\n**********");
						break;
					}

					System.out.println("\nNumber of remaining attempts: " + attempts + "\n" + underscore
							+ "\nPlease enter your letter: ");
					enter = input.nextLine();
					enter = enter.toLowerCase();
				} else {
					remaindis--;
					for (int i = 0; i < word.length(); i++) {
						if (String.valueOf(word.charAt(i)).equals(enter)) {
							correct.add(enter);
							underscore.set(i, word.charAt(i));
						}
					}

					System.out.println("\nWrong letters: ");
					for (int i = 0; i < wrong.size(); i++) {
						System.out.print(wrong.get(i) + " ");
					}
					System.out.println("\nNumber of remaining attempts: " + attempts + "\n" + underscore);
					if (underscore.contains('_') == false) {
						System.out.println("\n**********" + "\nYou win" + "\nThe word is: " + word + "\n**********");
						break;
					}

					System.out.println("Please enter your letter: ");
					enter = input.nextLine();
					enter = enter.toLowerCase();
				}
			} else {
				System.out.println("*****Input invalid*****" + "Wrong letters: ");
				for (int i = 0; i < wrong.size(); i++) {
					System.out.print(wrong.get(i) + " ");
				}
				System.out.println("\nNumber of remaining attempts: " + attempts + "\n" + underscore
						+ "\nPlease enter your letter: ");
				enter = input.nextLine();
				enter = enter.toLowerCase();
			}
		}
		input.close();
	}
}
