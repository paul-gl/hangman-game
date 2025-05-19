package games.hangman.console;

import games.hangman.Language;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizedMessageProvider {

    private ResourceBundle languageBundle;
    private static final String BASE_LANGUAGE_BUNDLE_NAME = "games.hangman.resources.language";

    public LocalizedMessageProvider(Language language) {
        try {
            languageBundle = ResourceBundle.getBundle(BASE_LANGUAGE_BUNDLE_NAME
                    , Locale.of(language.getCode()));
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }

    public String getMessage(String key) {
        return languageBundle.getString(key);
    }
}
