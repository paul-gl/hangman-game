package games.hangman.console;

import java.util.Scanner;

public class ConsoleReader implements Reader {

    private final Scanner scanner;

    public ConsoleReader() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }

    public void close() {
        this.scanner.close();
    }
}
