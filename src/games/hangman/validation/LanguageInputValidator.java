package games.hangman.validation;

import games.hangman.Language;
import games.hangman.console.ConsolePrinter;
import games.hangman.console.ConsoleReader;

public class LanguageInputValidator extends InputValidator<Language> {

    private static final String OPTION_RUSSIAN = "1";
    private static final String OPTION_ENGLISH = "2";

    public LanguageInputValidator(ConsoleReader reader, ConsolePrinter printer) {
        super(reader, printer);
    }

    public Language getValid() {
        printer.printf("Choose language/Выберите язык (%s -> rus/рус, %s -> eng/англ)%n"
                , OPTION_RUSSIAN, OPTION_ENGLISH);
        Language input = null;

        while (input == null) {
            switch (reader.read().trim()) {
                case OPTION_RUSSIAN -> input = Language.RUSSIAN;
                case OPTION_ENGLISH -> input = Language.ENGLISH;
                default -> printer.printf("Incorrect input! Enter %1$s or %2$s / Некорректный ввод! Введите %1$s или %2$s%n"
                        , OPTION_RUSSIAN, OPTION_ENGLISH);
            }
        }
        return input;
    }
}
