package games.hangman.validation;

import games.hangman.Language;
import games.hangman.console.ConsolePrinter;
import games.hangman.console.ConsoleReader;

public class LetterInputValidator extends InputValidator<Character> {

    private final Language gameLanguage;
    private final char hintChar;

    public LetterInputValidator(ConsoleReader reader, ConsolePrinter printer, Language gameLanguage, char hintChar) {
        super(reader, printer);
        this.gameLanguage = gameLanguage;
        this.hintChar = hintChar;
    }

    public Character getValid() {
        while (true) {
            printer.printfLocalized("GuessOffer", hintChar);
            String input = reader.read().trim();

            if (input.length() != 1) {
                printer.printlnLocalized("IncorrectLengthInput");
                continue;
            }

            char inputChar = Character.toUpperCase(input.charAt(0));

            if (inputChar == hintChar) {
                return hintChar;
            }

            if (Character.isLetter(inputChar) && gameLanguage.hasLetter(inputChar)) {
                return inputChar;
            } else {
                printer.printlnLocalized("NotALetterOrWrongAlphabet");
            }
        }
    }
}