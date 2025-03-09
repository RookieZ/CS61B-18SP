public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<>();

        char[] chars = word.toCharArray();
        for (char c : chars) {
            deque.addLast(c);
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);
        return isPalindromeHelper(deque);
    }

    private boolean isPalindromeHelper(Deque<Character> chars) {
        if (chars.isEmpty() || chars.size() == 1) {
            return true;
        }

        if (chars.removeFirst() != chars.removeLast()) {
            return false;
        } else {
            return isPalindromeHelper(chars);
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        return isPalindromeHelper(deque, cc);
    }

    private boolean isPalindromeHelper(Deque<Character> chars, CharacterComparator cc) {
        if (chars.isEmpty() || chars.size() == 1) {
            return true;
        }

        if (cc.equalChars(chars.removeFirst(), chars.removeLast())) {
            return isPalindromeHelper(chars, cc);
        } else {
            return false;
        }
    }
}
