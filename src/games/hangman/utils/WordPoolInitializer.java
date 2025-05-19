package games.hangman.utils;

import games.hangman.Language;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordPoolInitializer implements Initializer<WordPool>{

    private final Language language;
    private static final String WORDS_PATH_PATTERN = "src/games/hangman/resources/words_%s.txt";
    private static final String DELIMITER_SYMBOL = ":";
    private static final int PARTITION_SIZE = 2;
    private static final String WORDS_DELIMITER_REGEX = ",\\s*";

    public WordPoolInitializer(Language language) {
        this.language = language;
    }

    public WordPool initialize() {
        Map<String, List<String>> wordStorage = getWordsStorageFromFile();
        if (wordStorage.isEmpty()) {
            System.out.println("Loading default storage");
            wordStorage = getDefaultWordStorage();
        }
        return new WordPool(wordStorage);
    }

    private Map<String, List<String>> getWordsStorageFromFile() {
        String filePath = String.format(WORDS_PATH_PATTERN, language.getCode());

        Map<String, List<String>> wordStorage = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] wordsData = line.split(DELIMITER_SYMBOL);
                if (wordsData.length != PARTITION_SIZE) {
                    continue;
                }
                String category = wordsData[0], categoryWords = wordsData[1];
                List<String> wordsList = new ArrayList<>(Arrays.asList(categoryWords.split(WORDS_DELIMITER_REGEX)));
                wordStorage.put(category, wordsList);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not locate file \"" + filePath + "\"");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return wordStorage;
    }

    private Map<String, List<String>> getDefaultWordStorage() {
        if (language.equals(Language.ENGLISH)) {
            return Stream.of(new Object[][] {
                    { "Animals", new ArrayList<>(List.of("hamster","kitten","parrot","chicken")) },
                    { "Countries", new ArrayList<>(List.of("Sweden","Ecuador","Russia","Denmark")) },
                    { "Sports", new ArrayList<>(List.of("hockey","gymnastics","swimming","trampolining")) }
            }).collect(Collectors.toMap(data -> (String) data[0], data -> (List<String>) data[1]));
        }
        return Stream.of(new Object[][]{
                {"Животные", new ArrayList<>(List.of("хомяк", "попугай", "черепаха", "черепаха"))},
                {"Страны", new ArrayList<>(List.of("Швеция", "Эквадор", "Россия", "Швейцария"))},
                {"Спорт", new ArrayList<>(List.of("хоккей", "гимнастика", "плавание", "пауэрлифтинг"))}
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (List<String>) data[1]));
    }
}
