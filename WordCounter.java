import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordCounter {
	private static final Set<String> STOP_WORDS = new HashSet<>(Arrays.asList("the", "and", "or", "a", "an", "in", "on", "of"));

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("--------------------------------------------------------------------");
		System.out.println("                           Word Counter");
		System.out.println("--------------------------------------------------------------------");
		System.out.println("Enter '1' to input text manually");
		System.out.println("Enter '2' to input a file");

		int choice = scanner.nextInt();
		scanner.nextLine(); // Consume the newline character

		String text = "";
		System.out.println("--------------------------------------------------------------------");
		switch (choice) {
			case 1:

				System.out.println("Enter the text: ");
				text = scanner.nextLine();
				break;
			case 2:
				System.out.println("Enter the file path: ");
				String filePath = scanner.nextLine();
				try {
					text = new Scanner(new File(filePath)).useDelimiter("\\Z").next();
				} catch (FileNotFoundException e) {
					System.out.println("File not found. Please try again.");
					return;
				}
				break;
			default:
				System.out.println("Invalid choice.");
				return;
		}

		String[] words = text.split("[\\s.,:;!?()]+");
		Map<String, Integer> wordFrequency = new HashMap<>();
		int totalWords = 0;

		for (String word : words) {
			if (word.isEmpty() || STOP_WORDS.contains(word.toLowerCase())) {
				continue;
			}

			wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
			totalWords++;
		}
		System.out.println("--------------------------------------------------------------------");
		System.out.println("Total number of words: " + totalWords);
		System.out.println("--------------------------------------------------------------------");
		System.out.println("Number of unique words: " + wordFrequency.size());
		System.out.println("--------------------------------------------------------------------");
		System.out.println("Word frequency:");
		for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}

		scanner.close();
	}
}
