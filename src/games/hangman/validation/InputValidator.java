package games.hangman.validation;

import games.hangman.console.ConsolePrinter;
import games.hangman.console.ConsoleReader;

public abstract class InputValidator<T> {

    protected ConsoleReader reader;
    protected ConsolePrinter printer;

    public InputValidator(ConsoleReader reader, ConsolePrinter printer) {
        this.reader = reader;
        this.printer = printer;
    }

    public abstract T getValid();
}
