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
        assertFalse(game.getGuessed());
    }

    @Test
    public void testHandleHigherGuess() {
        game.handleHigherGuess();

        assertTrue(game.getLowerBound() >= 502);
        assertEquals(751, game.getLastGuess());
        assertEquals(1, game.getNumGuesses());
    }

    @Test
    public void testHandleLowerGuess() {
        game.handleLowerGuess();

        assertTrue(game.getUpperBound() <= 500);
        assertEquals(251, game.getLastGuess());
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
        assertFalse(game.getGuessed());
    }

    @Test
    public void testMultipleGuessesGuessCount1() {
        game.handleHigherGuess();
        game.handleLowerGuess();

        assertEquals(2, game.getNumGuesses());
    }

    @Test
    public void testMultipleGuessesGuessCount2() {
        game.handleHigherGuess();
        game.handleLowerGuess();
        game.handleLowerGuess();

        assertEquals(3, game.getNumGuesses());
    }

    //guessing a number shouldn't take more than 10 guesses

    @Test
    public void guess1()
    {
        int testNum = 1;

        while(game.getNumGuesses() < 10 && !game.getGuessed())
        {
            if(testNum < game.getLastGuess())
            {
                game.handleLowerGuess();
            }
            else if(testNum > game.getLastGuess())
            {
                game.handleHigherGuess();
            }
            else
            {
                game.handleCorrectGuess();
            }
        }
        assertTrue(game.getGuessed());
    }

    @Test
    public void guess2()
    {
        int testNum = 2;

        while(game.getNumGuesses() < 10 && !game.getGuessed())
        {
            if(testNum < game.getLastGuess())
            {
                game.handleLowerGuess();
            }
            else if(testNum > game.getLastGuess())
            {
                game.handleHigherGuess();
            }
            else
            {
                game.handleCorrectGuess();
            }
        }
        assertTrue(game.getGuessed());
    }

    @Test
    public void guess3()
    {
        int testNum = 3;

        while(game.getNumGuesses() < 10 && !game.getGuessed())
        {
            if(testNum < game.getLastGuess())
            {
                game.handleLowerGuess();
            }
            else if(testNum > game.getLastGuess())
            {
                game.handleHigherGuess();
            }
            else
            {
                game.handleCorrectGuess();
            }
        }
        assertTrue(game.getGuessed());
    }

    @Test
    public void guess4()
    {
        int testNum = 4;

        while(game.getNumGuesses() < 10 && !game.getGuessed())
        {
            if(testNum < game.getLastGuess())
            {
                game.handleLowerGuess();
            }
            else if(testNum > game.getLastGuess())
            {
                game.handleHigherGuess();
            }
            else
            {
                game.handleCorrectGuess();
            }
        }
        assertTrue(game.getGuessed());
    }

    @Test
    public void guess5()
    {
        int testNum = 5;

        while(game.getNumGuesses() < 10 && !game.getGuessed())
        {
            if(testNum < game.getLastGuess())
            {
                game.handleLowerGuess();
            }
            else if(testNum > game.getLastGuess())
            {
                game.handleHigherGuess();
            }
            else
            {
                game.handleCorrectGuess();
            }
        }
        assertTrue(game.getGuessed());
    }

    @Test
    public void guess1001()
    {
        IndexOutOfBoundsException e = assertThrows(IndexOutOfBoundsException.class,
            () -> {
                int testNum = 1001;

                while(game.getNumGuesses() < 10 && !game.getGuessed())
                {
                    if(testNum < game.getLastGuess())
                    {
                        game.handleLowerGuess();
                    }
                    else if(testNum > game.getLastGuess())
                    {
                        game.handleHigherGuess();
                    }
                    else
                    {
                        game.handleCorrectGuess();
                    }
                }
            });

        assertEquals(IndexOutOfBoundsException.class, e.getClass());
    }

    @Test
    public void guess0()
    {
        IndexOutOfBoundsException e = assertThrows(IndexOutOfBoundsException.class,
                () -> {
                    int testNum = 0;

                    while(game.getNumGuesses() < 10 && !game.getGuessed())
                    {
                        if(testNum < game.getLastGuess())
                        {
                            game.handleLowerGuess();
                        }
                        else if(testNum > game.getLastGuess())
                        {
                            game.handleHigherGuess();
                        }
                        else
                        {
                            game.handleCorrectGuess();
                        }
                    }
                });

        assertEquals(IndexOutOfBoundsException.class, e.getClass());
    }
}