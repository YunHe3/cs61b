

public class Palindrome {

	public Deque<Character> wordToDeque(String word) {
		// translate a String into a Deque
		Deque<Character> d = new LinkedListDeque<Character>();
		for (int i = 0; i < word.length(); i++) {
			d.addLast(word.charAt(i));
		}
		return d;
	}

	public boolean isPalindrome(String word) {
		if (word.length() < 2) {
			return true;
		}
		Deque<Character> d = wordToDeque(word);
		while (d.size() > 1) {
			if (d.removeFirst() != d.removeLast()) return false;
		}
		return true;
	}

	public boolean isPalindrome(String word, CharacterComparator cc) {
		if (word.length() < 2) {
			return true;
		}
		Deque<Character> d = wordToDeque(word);
		while (d.size() > 1) {
			if (!cc.equalChars(d.removeFirst(), d.removeLast())) return false;
		}
		return true;
	}
}