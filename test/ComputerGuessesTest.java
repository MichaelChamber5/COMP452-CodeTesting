import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for ComputerGuessesGame
 */
public class ComputerGuessesTest {

    private ComputerGuessesGame game;

    @BeforeEach
    public void setUp() {
        game = new ComputerGuessesGame();
    }

    @Test
    public void testInitialGameState() {
        assertEquals(501, game.getLastGuess());
        assertEquals(0, game.getNumGuesses());
        assertEquals(1000, game.getUpperBound());
        assertEquals(1, game.getLowerBound());
    }

    @Test
    public void testHandleHigherGuess() {
        game.handleHigherGuess();

        assertTrue(game.getLowerBound() >= 502);
        assertEquals(1, game.getNumGuesses());
    }

    @Test
    public void testHandleLowerGuess() {
        game.handleLowerGuess();

        assertTrue(game.getUpperBound() <= 501);
        assertEquals(1, game.getNumGuesses());
    }

    @Test
    public void testResetGame() {
        game.handleHigherGuess();
        game.handleLowerGuess();

        game.resetGame();

        assertEquals(501, game.getLastGuess());
        assertEquals(0, game.getNumGuesses());
        assertEquals(1000, game.getUpperBound());
        assertEquals(1, game.getLowerBound());
    }

    @Test
    public void testMultipleGuesses() {
        game.handleHigherGuess(); // Narrows to [501, 1000]
        game.handleLowerGuess();  // Narrows based on new guess

        assertEquals(2, game.getNumGuesses());
    }
}