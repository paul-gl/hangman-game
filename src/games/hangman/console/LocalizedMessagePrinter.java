package games.hangman.console;

public interface LocalizedMessagePrinter {
    void printLocalized(String key);
    void printlnLocalized(String key);
    void printfLocalized(String key, Object... args);
}
