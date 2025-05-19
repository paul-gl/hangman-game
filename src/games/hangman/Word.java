package games.hangman;

import java.util.*;

public class Word {

    private static final Random RANDOM = new Random();
    private final String category;
    private final String actualWord;
    private final StringBuilder hiddenWord;
    private static final String MASK_SYMBOL = "_";
    private final Map<Character, List<Integer>> hiddenLetters;
    private List<Character> uniqueHiddenLetters;

    public Word(String actualWord, String category) {
        this.actualWord = actualWord;
        this.category = category;
        hiddenWord = new StringBuilder(MASK_SYMBOL.repeat(this.actualWord.length()));
        hiddenLetters = getLettersMap();
        uniqueHiddenLetters = getUniqueHiddenLetters();
    }

    public char getRandomHiddenLetter() {
        int index = RANDOM.nextInt(uniqueHiddenLetters.size());
        return uniqueHiddenLetters.get(index);
    }

    public void revealLetter(char letter) {
        List<Integer> indexes = hiddenLetters.get(letter);
        if (indexes != null) {
            for (int idx : indexes) {
                hiddenWord.setCharAt(idx, letter);
            }
            hiddenLetters.remove(letter);
            uniqueHiddenLetters = getUniqueHiddenLetters();
        }
    }

    public boolean isRevealed() {
        return actualWord.contentEquals(hiddenWord);
    }

    public boolean hasLetter(char letter) {
        return actualWord.indexOf(letter) >= 0;
    }

    private Map<Character, List<Integer>> getLettersMap() {
        List<Character> letters = actualWord.chars().mapToObj(c -> (char) c).toList();
        Set<Character> uniqueLetters = new HashSet<>(letters);
        Map<Character, List<Integer>> lettersMap = new HashMap<>();

        for (var letter : uniqueLetters) {
            List<Integer> indexes = new ArrayList<>();
            for (int i = 0; i < actualWord.length(); i++) {
                if (actualWord.charAt(i) == letter) {
                    indexes.add(i);
                }
            }
            lettersMap.put(letter, indexes);
        }

        return lettersMap;
    }

    private List<Character> getUniqueHiddenLetters() {
        return new ArrayList<>(hiddenLetters.keySet());
    }

    public String getHiddenWord() {
        return hiddenWord.toString().replaceAll("\\B|\\b", " ");
    }

    public int length() {
        return actualWord.length();
    }

    public String getActualWord() {
        return actualWord;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return actualWord;
    }
}
