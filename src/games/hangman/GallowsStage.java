package games.hangman;

enum GallowsStage {
    STAGE0("""
                  +---+
                  |   |
                      |
                      |
                      |
                      |
                ========="""),
    STAGE1("""
                  +---+
                  |   |
                  O   |
                      |
                      |
                      |
                ========="""),
    STAGE2("""
                  +---+
                  |   |
                  O   |
                  |   |
                      |
                      |
                ========="""),
    STAGE3("""
                  +---+
                  |   |
                  O   |
                 /|   |
                      |
                      |
                ========="""),
    STAGE4("""
                  +---+
                  |   |
                  O   |
                 /|\\  |
                      |
                      |
                ========='"""),
    STAGE5("""
                  +---+
                  |   |
                  O   |
                 /|\\  |
                 /    |
                      |
                ========="""),
    STAGE6("""
                  +---+
                  |   |
                  O   |
                 /|\\  |
                 / \\  |
                      |
                =========""");


    final String representation;

    GallowsStage(String rep) {
        this.representation = rep;
    }

    public static int getInitialLives() {
        return GallowsStage.values().length - 1;
    }

    public static void printCurrentStage(int remainedLives) {
        System.out.println(GallowsStage.valueOf("STAGE"
                + (getInitialLives() - remainedLives)
        ));
    }

    @Override
    public String toString() {
        return representation;
    }
}
