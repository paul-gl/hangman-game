package games.hangman;

import games.hangman.console.*;
import games.hangman.utils.*;
import games.hangman.validation.*;

public class HangmanGame {

    private ConsolePrinter printer;
    private final ConsoleReader reader;
    private final InputValidator<Language> languageInputValidator;
    private final Initializer<String> logoInitializer;
    private Language gameLanguage;
    private GameState gameState;
    private String logo;

    private static final String OPTION_START_GAME_ROUND = "1";
    private static final String OPTION_SWITCH_LANGUAGE = "2";
    private static final String OPTION_QUIT = "3";


    public HangmanGame() {
        gameLanguage = Language.ENGLISH;
        printer = new ConsolePrinter(gameLanguage);
        reader = new ConsoleReader();
        logoInitializer = new LogoInitializer();
        languageInputValidator = new LanguageInputValidator(reader,printer);
        gameState = GameState.NOT_STARTED;
    }

    public void start() {
        gameState = GameState.STARTED;
        setLocalization(languageInputValidator.getValid());
        logo = logoInitializer.initialize();
        printLogo();
        printer.printlnLocalized("Welcome");

        while (gameState.equals(GameState.STARTED)) {
            printer.printlnLocalized("ChooseOption");
            switch (reader.read().trim()) {
                case OPTION_START_GAME_ROUND -> new GameLoop(printer, reader, gameLanguage).start();
                case OPTION_SWITCH_LANGUAGE -> setLocalization(languageInputValidator.getValid());
                case OPTION_QUIT -> gameState = GameState.FINISHED;
                default -> printer.printlnLocalized("IncorrectOptionInput");
            }
        }

        reader.close();
    }

    private void setLocalization(Language language) {
        if (language != gameLanguage) {
            gameLanguage = language;
            printer = new ConsolePrinter(gameLanguage);
        }
    }

    private void printLogo() {
        printer.println(logo);
    }
}
