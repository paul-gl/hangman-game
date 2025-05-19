package games.hangman.console;

public interface MessagePrinter {
    void print(String message);
    void println(String message);
    void printf(String message, Object... args);
}
