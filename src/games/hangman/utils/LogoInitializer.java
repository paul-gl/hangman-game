package games.hangman.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LogoInitializer implements Initializer<String> {

    private static final String LOGO_PATH = "src/games/hangman/resources/hangmanlogo.txt";

    public String initialize() {
        String logo = getLogoFromFile();
        if (logo.isEmpty()) {
            System.out.println("Loading default logo");
            logo = getDefaultLogo();
        }
        return logo;
    }

    private String getLogoFromFile() {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(LOGO_PATH))){
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not locate file \"" + LOGO_PATH + "\"");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sb.toString().trim();
    }

    private String getDefaultLogo() {
        return """
            ██╗░░██╗░█████╗░███╗░░██╗░██████╗░███╗░░░███╗░█████╗░███╗░░██╗
            ██║░░██║██╔══██╗████╗░██║██╔════╝░████╗░████║██╔══██╗████╗░██║
            ███████║███████║██╔██╗██║██║░░██╗░██╔████╔██║███████║██╔██╗██║
            ██╔══██║██╔══██║██║╚████║██║░░╚██╗██║╚██╔╝██║██╔══██║██║╚████║
            ██║░░██║██║░░██║██║░╚███║╚██████╔╝██║░╚═╝░██║██║░░██║██║░╚███║
            ╚═╝░░╚═╝╚═╝░░╚═╝╚═╝░░╚══╝░╚═════╝░╚═╝░░░░░╚═╝╚═╝░░╚═╝╚═╝░░╚══╝
            """;
    }
}
