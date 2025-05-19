package games.hangman;

import games.hangman.console.*;
import games.hangman.utils.WordProvider;
import games.hangman.validation.*;

import java.util.LinkedHashSet;
import java.util.Set;

public class GameLoop {

    private final ConsolePrinter printer;
    private GameLoopState loopState;
    private final InputValidator<Character> letterInputValidator;

    private final WordProvider wordProvider;
    private Word wordOfRound;
    private Set<Character> guessedLetters;

    private int remainedLives;
    private int remainedHints;

    private static final char USE_HINT_CHAR = '1';
    private static final int INITIAL_LIVES = GallowsStage.getInitialLives();
    private static final int INITIAL_HINTS = 2;

    public GameLoop(ConsolePrinter printer, ConsoleReader reader, Language language) {
        this.printer = printer;
        letterInputValidator = new LetterInputValidator(reader, printer, language, USE_HINT_CHAR);
        wordProvider = new WordProvider(language);
        loopState = GameLoopState.NOT_STARTED;
    }

    public void start() {
        printer.printlnLocalized("NewGameBegins");
        loopState = GameLoopState.IN_PROGRESS;
        guessedLetters = new LinkedHashSet<>();
        remainedLives = INITIAL_LIVES;
        remainedHints = INITIAL_HINTS;
        wordOfRound = wordProvider.getRandomWord();

        while (loopState.equals(GameLoopState.IN_PROGRESS)) {
            printProgress();
            char input = letterInputValidator.getValid();

            if (input == USE_HINT_CHAR) {
                useHint();
                if (wordOfRound.isRevealed()) {
                    loopState = GameLoopState.PLAYER_WON;
                    break;
                }
                continue;
            }

            if (guessedLetters.contains(input)) {
                printer.printfLocalized("AlreadyGuessed", input);
                continue;
            }

            guessedLetters.add(input);
            if (wordOfRound.hasLetter(input)) {
                printer.printfLocalized("GuessedRight", input);
                wordOfRound.revealLetter(input);
            } else {
                printer.printfLocalized("GuessedWrong", input);
                remainedLives--;
            }

            loopState = updateLoopState();
        }

//        GallowsStage.printCurrentStage(remainedLives);
        printProgress();
        if (loopState.equals(GameLoopState.PLAYER_WON)) {
            printer.printfLocalized("PlayerWon", wordOfRound.getActualWord());
        } else {
            printer.printfLocalized("PlayerLost", wordOfRound.getActualWord());
        }
    }

    private void printProgress() {
        GallowsStage.printCurrentStage(remainedLives);
        printer.printfLocalized("WordInfo", wordOfRound.length(), wordOfRound.getHiddenWord());
        printer.printfLocalized("Category", wordOfRound.getCategory());
        printer.printfLocalized("GuessedLetters", guessedLetters);
        printer.printfLocalized("LivesAndHintsLeft", remainedLives, remainedHints);
    }

    private void useHint() {
        if (remainedHints > 0) {
            char letter = wordOfRound.getRandomHiddenLetter();
            wordOfRound.revealLetter(letter);
            guessedLetters.add(letter);
            printer.printlnLocalized("UsedHint");
            remainedHints--;
        } else {
            printer.printlnLocalized("NoHintsLeft");
        }
    }

    private GameLoopState updateLoopState() {
        if (wordOfRound.isRevealed()) {
            return GameLoopState.PLAYER_WON;
        } else if (remainedLives <= 0) {
            return GameLoopState.PLAYER_LOST;
        }
        return GameLoopState.IN_PROGRESS;
    }
}
