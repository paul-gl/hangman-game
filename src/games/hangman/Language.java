package games.hangman;

public enum Language {
    RUSSIAN("RU"), ENGLISH("EN");

    private final String code;

    Language(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public boolean hasLetter(char letter) {
        return switch (this) {
            case ENGLISH -> Character.UnicodeBlock.of(letter).equals(Character.UnicodeBlock.BASIC_LATIN);
            case RUSSIAN -> Character.UnicodeBlock.of(letter).equals(Character.UnicodeBlock.CYRILLIC);
        };
    }
}
