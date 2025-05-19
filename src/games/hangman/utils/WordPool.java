package games.hangman.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WordPool {

    private final Map<String, List<String>> wordStorage;
    private final List<String> wordCategories;

    public WordPool(Map<String, List<String>> wordStorage) {
        this.wordStorage = wordStorage;
        this.wordCategories = new ArrayList<>(wordStorage.keySet());
    }

    public Map<String, List<String>> getWordStorage() {
        return wordStorage;
    }

    public List<String> getWordCategories() {
        return wordCategories;
    }
}
