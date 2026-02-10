public class ComputerGuessesGame {
    private int numGuesses;
    private int lastGuess;

    // upperBound and lowerBound track the computer's knowledge about the correct number
    // They are updated after each guess is made
    private int upperBound; // correct number is <= upperBound
    private int lowerBound; // correct number is >= lowerBound

    public ComputerGuessesGame() {
        resetGame();
    }

    public void resetGame() {
        numGuesses = 0;
        upperBound = 1000;
        lowerBound = 1;
        lastGuess = getLastGuess(lowerBound, upperBound);
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
        return new GameResult(false, lastGuess, numGuesses);
    }

    private int getLastGuess(int lowerBound, int upperBound) {
        return (lowerBound + upperBound + 1) / 2;
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
}
