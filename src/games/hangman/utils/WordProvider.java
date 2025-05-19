package games.hangman.utils;

import games.hangman.Language;
import games.hangman.Word;

import java.util.List;
import java.util.Random;

public class WordProvider {

    private static final Random RANDOM = new Random();
    private final WordPool wordPool;

    public WordProvider(Language language) {
        wordPool = new WordPoolInitializer(language).initialize();
    }

    public Word getRandomWord() {
        List<String> wordCategories = wordPool.getWordCategories();
        String category = wordCategories.get(RANDOM.nextInt(wordCategories.size()));
        List<String> categorizedWords = wordPool.getWordStorage().get(category);
        String randomWord = categorizedWords.get(RANDOM.nextInt(categorizedWords.size()));
        return new Word(randomWord.toUpperCase(), category);
    }

}
