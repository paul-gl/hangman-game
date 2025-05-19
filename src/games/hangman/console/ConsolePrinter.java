package games.hangman.console;

import games.hangman.Language;

public class ConsolePrinter implements MessagePrinter, LocalizedMessagePrinter {

    private final LocalizedMessageProvider provider;

    public ConsolePrinter(Language gameLanguage) {
        provider = new LocalizedMessageProvider(gameLanguage);
    }

    @Override
    public void print(String message) {
        System.out.print(message);
    }

    @Override
    public void println(String message) {
        System.out.println(message);
    }

    @Override
    public void printf(String message,  Object... args) {
        System.out.printf(message, args);
    }

    @Override
    public void printLocalized(String key) {
        System.out.print(provider.getMessage(key));
    }

    @Override
    public void printlnLocalized(String key) {
        System.out.println(provider.getMessage(key));
    }

    @Override
    public void printfLocalized(String key, Object... args) {
        System.out.printf(provider.getMessage(key), args);
    }

}
