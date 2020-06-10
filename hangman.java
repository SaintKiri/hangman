import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

public class hangman {
	public static void main(String[] args) {
		// Variables
		final String error = new String("Error");
		// Prompt
		String word = JOptionPane.showInputDialog(null, "Enter your word to guess: ", JOptionPane.QUESTION_MESSAGE).toLowerCase();
		if (word == null) { // Cancel was selected
			System.exit(0);
		} else if (word.length() == 0) {
			JOptionPane.showMessageDialog(null, "You need to enter something", error, JOptionPane.ERROR_MESSAGE);
		}
		String chance = JOptionPane.showInputDialog(null, "How many chances? (Default: 6)", JOptionPane.QUESTION_MESSAGE);
		if (chance == null) { // Cancel was selected
			System.exit(0);
		}
		int chances = 6;
		try {
			chances = Integer.parseInt(chance);
			if (chances < 1) {
				throw new Exception();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Your input was invalid", error, JOptionPane.ERROR_MESSAGE);
		}
		int selection = JOptionPane.showConfirmDialog(null, "Do you want a hint? ");
		String hint = new String();
		if (selection == JOptionPane.CANCEL_OPTION) {
			System.exit(0);
		} else if (selection == JOptionPane.YES_OPTION) {
			hint = "Hint: " + JOptionPane.showInputDialog(null, "Enter a hint: ", "Hint", JOptionPane.QUESTION_MESSAGE) + "\n";
		}

		// The actual game
		ArrayList<Character> wordLetters = new ArrayList<Character>();
		char[] wordArray = word.toCharArray();
		for (int i = 0; i < word.length(); i++) {
			if (!wordLetters.contains(wordArray[i])) {
				wordLetters.add(wordArray[i]);
			}
		}
		Collections.sort(wordLetters);
		ArrayList<Character> correct = new ArrayList<Character>();
		ArrayList<Character> wrong = new ArrayList<Character>();
		boolean win = true;

		while (chances > wrong.size()) {
			String guess = JOptionPane.showInputDialog(null, currentSolution(correct, wordArray) + "\n"
															+ hint 
															+ "Wrong characters: " + wrong.toString()
															+ "\nTake a guess: ", "Guess", JOptionPane.QUESTION_MESSAGE);
			if (guess == null) { // Cancel was selected
				System.exit(0);
			} else {
				char[] guessLetters = guess.toCharArray();
				for (int i = 0; i < guessLetters.length; i++) {
					if (wordLetters.contains(guessLetters[i]) && !correct.contains(guessLetters[i])) {
						correct.add(guessLetters[i]);
						Collections.sort(correct);
					} else if (!wrong.contains(guessLetters[i])) {
						wrong.add(guessLetters[i]);
						Collections.sort(wrong);
					}
				}
			}

			// Game win/lose conditions
			if (correct.equals(wordLetters)) {
				// Win
				break;
			} else if (wrong.size() == chances) {
				// Lose
				win = false;
				break;
			}
		}

		// Print game over message
		final String message = new String("\nThe word is " + word);
		if (win) {
			final String youWin = new String("You win!");
			JOptionPane.showMessageDialog(null, youWin + message, youWin, JOptionPane.PLAIN_MESSAGE);
		} else {
			final String youLose = new String("You Lose!");
			JOptionPane.showMessageDialog(null, youLose + message, youLose, JOptionPane.PLAIN_MESSAGE);
		}
		System.exit(0);
	}

	private static String currentSolution(ArrayList<Character> partial, char[] full) {
		char[] result = new char[full.length];
		for (int i = 0; i < full.length; i++) {
			if (partial.contains(full[i])) {
				result[i] = full[i];
			} else if (full[i] == ' ') {
				result[i] = ' ';
			} else {
				result[i] = '_';
			}
		}
		return new String(result);
	}
}