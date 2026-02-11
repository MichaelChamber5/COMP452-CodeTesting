public class ComputerGuessesGame {
    private static final int LOWEST_VALUE = 1;
    private static final int HIGHEST_VALUE = 1000;

    private int numGuesses;
    private int lastGuess;

    // upperBound and lowerBound track the computer's knowledge about the correct number
    // They are updated after each guess is made
    private int upperBound; // correct number is <= upperBound
    private int lowerBound; // correct number is >= lowerBound

    private boolean guessed;

    public ComputerGuessesGame() {
        resetGame();
    }

    public void resetGame() {
        numGuesses = 0;
        upperBound = HIGHEST_VALUE;
        lowerBound = LOWEST_VALUE;
        lastGuess = getLastGuess(lowerBound, upperBound);
        guessed = false;
    }

    public void handleHigherGuess() {
        lowerBound = Math.max(lowerBound, lastGuess + 1);
        makeNextGuess();
    }

    public void handleLowerGuess() {
        upperBound = Math.min(upperBound, lastGuess);
        makeNextGuess();
    }

    public GameResult handleCorrectGuess() {
        guessed = true;
        return new GameResult(false, lastGuess, numGuesses);
    }

    private int getLastGuess(int lowerBound, int upperBound) {
        int guess = (lowerBound + upperBound + 1) / 2;

        if(guess > HIGHEST_VALUE || guess < LOWEST_VALUE)
        {
            throw new IndexOutOfBoundsException();
        }

        return guess;
    }

    private void makeNextGuess() {
        lastGuess = getLastGuess(lowerBound, upperBound);
        numGuesses++;
    }

    // these are for testin

    public int getLastGuess() {
        return lastGuess;
    }

    public int getNumGuesses() {
        return numGuesses;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public boolean getGuessed() {
        return guessed;
    }
}
